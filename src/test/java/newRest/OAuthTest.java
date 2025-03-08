package newRest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuthTest {

	public static void main(String[] args) {
		
		//RestAssured.basePath = "https://rahulshettyacademy.com";
		
		String response = given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
        .formParams("grant_type", "client_credentials")
        .formParams("scope", "trust")
        .when().log().all()
        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		
		System.out.println(response);
		
		
		
		  JsonPath jsonPath = new JsonPath(response); 
		  String access_token = jsonPath.getString("access_token");
		  
		  
		  String responce1 = given() .queryParam("access_token", access_token)
		  .when().log().all()
		  .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		  System.out.println(responce1);
		 
		 
		
		
		 
		
	}

}
