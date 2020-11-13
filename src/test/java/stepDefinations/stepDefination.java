package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;// Here we are getting place_id in first scenario(AddPLaceAPI) and passing that to 2nd scenario(deletePlaceAPI). So all test cases in that run refer to same variable and not redefine to null again.
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws Exception {
		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);//Like object creation for class. it is object for enum
		System.out.println("Resource: "+resourceAPI.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		if(method.equalsIgnoreCase("POST")) 
			response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200); 
	}
	@Then("{string} in reponse body is {string}")
	public void in_reponse_body_is(String keyValue, String ExpectedValue) {
	    assertEquals(getJsonPath(response,keyValue), ExpectedValue);
	}
	@Then("verify the place_id created maps to {string} using {string}")
	public void verify_the_place_id_created_maps_to_using(String ExpectedName, String resource) throws Exception {
		place_id = getJsonPath(response,"place_id");//Here response object holds AddPlaceAPI response output
		res =  given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");//Re using method created above. Here res is created above step, but in above it was created in 1st method. 
		String name = getJsonPath(response,"name"); //After hitting getPlace API, now response object holds getPlaceResponse output
		assertEquals(ExpectedName,name);
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws Exception {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id)); //converting JSON request into rest assured accepted string//generated from https://freeformatter.com/
	}





}
