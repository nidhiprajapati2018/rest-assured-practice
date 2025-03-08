package newRest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import File.Payload;
import File.ReUsableMethods;

public class Basic {
    

	public static void main(String[] args) {
		
		//given - all input details
		//when - submit the API - resource, http method
		//Then - validate the response
		//Add place - > update place with New address -> Get place to validate if new address is present in response
		//validate if add place API is working as expected
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String response = given().log().all()
				          .queryParam("key", "qaclick123")
				          .header("Content-Type", "application/json")
		                  .body(Payload.addPlace())
		                  .when().post("maps/api/place/add/json")
		                  .then().log().all().assertThat().statusCode(200)
		                 .body("scope", equalTo("APP"))
		                 .header("Server","Apache/2.4.52 (Ubuntu)")
		                 .extract().response().asString();
		  
		  System.out.println("Response"+response); //we get the resopnse in {} braces
		  
		  
		  JsonPath js = new JsonPath(response); //for parsing Json String placeid =
		  String placeid = js.getString("place_id");
		  
		  System.out.println(placeid);  //18f52b0ffc4b1dd26061dcef3d5045a1
		 
		
			
			  // update place with New address
			  
			  String newAddress = "Summer walk, Africa";
			  
			  given().log().all()
			  .queryParam("key", "qaclick123")
			  .header("Content-Type","application/json")
			  .body("{\n" +
			  "\"place_id\":\""+placeid+"\",\n" + "\"address\":\""+newAddress+"\",\n" +
			  "\"key\":\"qaclick123\"\n" + "}")
			  .when().put("maps/api/place/update/json")
			  .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
			  
			  
			  // Get responce for validate the address updated or not
			  
			  String getplaceResponce = given().log().all() 
					  .queryParam("place_id",placeid)
			          .queryParam("key", "qaclick123") 
			          .header("Content-Type", "application/json")
			          .when().get("maps/api/place/get/json")
			          .then().assertThat().log().all().statusCode(200).extract().response().asString();
			  
			  JsonPath js1 = ReUsableMethods.rawToJson(getplaceResponce);
			  String actualAddress = js1.getString("address");
			  
			  
			  System.out.println(actualAddress);
			  
			  Assert.assertEquals(actualAddress, newAddress);
			 
		  
		  
		  
		 		
	
	}

}
