package newRest;


import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import newRestPOJO.Api;
import newRestPOJO.GetCourse;
import newRestPOJO.WebAutomation;

public class UsingPOJOClassforextractresponce extends OAuthTest {

	public static void main(String[] args) {
		
		String[] courseTitle = {"Selenium Webdriver Java","Cypress","Protractor"};

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
		
		       GetCourse gc = given() .queryParam("access_token", access_token)
				  .when().log().all()
				  .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		       
		       System.out.println(gc.getLinkedIn());
		       System.out.println(gc.getInstructor());
		       System.out.println(gc.getCourses().getApi().get(0).getCourseTitle());
		       
		      List<Api> apicourse = gc.getCourses().getApi();
		      
		      for(int i=0; i<apicourse.size();i++) {
		    	  if(apicourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
		    		  System.out.println(apicourse.get(i).getPrice());
		    	  }
		    	  
		      }
		      
		      //Get the course names of Webautomation
		      
		      ArrayList<String>arr = new ArrayList<>();
		      
		     List<WebAutomation> webAutomationcourse = gc.getCourses().getWebAutomation();
		      
		       for(int i=0; i<webAutomationcourse.size();i++) {
		    	  arr.add(webAutomationcourse.get(i).getCourseTitle());
		       }
		       
		      List<String> expectedList =  Arrays.asList(courseTitle);
		       
		      
		       Assert.assertTrue(arr.equals(expectedList));
				 

	}

}
