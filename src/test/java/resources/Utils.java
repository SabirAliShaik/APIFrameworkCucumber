package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqspec; //Do not create another instance of req spec builder. For second TC it wont over ride if public static
	public RequestSpecification requestSpecification() throws Exception {
		if(reqspec == null) {
			PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));// Creates the file in run time and logs into it
			reqspec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
							.addFilter(RequestLoggingFilter.logRequestTo(stream))
							.addFilter(ResponseLoggingFilter.logResponseTo(stream))
							.addQueryParam("key", "qaclick123")
							.setContentType(ContentType.JSON).build();
		}
		return reqspec;	
	}
	
	public static String getGlobalValue(String key) throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\S K SABIR ALI\\Java\\workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
		
	}
}
