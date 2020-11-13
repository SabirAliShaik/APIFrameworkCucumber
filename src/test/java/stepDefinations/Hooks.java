package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Exception {
		//Execute this code only when place id is null(if add place scenario doesn't run)
		//write a code that will give you place id
		stepDefination m = new stepDefination();
		if(stepDefination.place_id == null) {//place_id is static so calling from class name
			m.add_place_payload_with("Ali", "Telugu", "Asia");
			m.user_calls_with_http_request("AddPlaceAPI", "Post");
			m.verify_the_place_id_created_maps_to_using("Ali", "getPlaceAPI");	
		}
		
	}
}
