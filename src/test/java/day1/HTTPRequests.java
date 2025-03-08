package day1;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*given()
 
 Content type, set cookies, add auth, add param, set headers info etc..

when()

get, post, put, delete.


then()

validate status code, extract response, extract headers cookies & response body...

*/

public class HTTPRequests {
	
	int id; 
	
	//@Test(priority = 1)
     void getUsers() {
		
		given()
		
		.when()
		  .get("https://reqres.in/api/users?page=1")
		
		.then()
		 .statusCode(200)          //Validation status code
		 .body("id", equalTo(2)) // 
		 .log().all(); // Log in console
	
		
		
	}
	
     @Test(priority = 2)
	void createUser() {
		
		HashMap<String, String> hm = new HashMap <String, String>();// prepare the data
		hm.put("name", "Rob");
		hm.put("job","Software");
		
	  id = given()
		     .contentType("application/json")
		     .body(hm)
		.when()
		  .post("https://reqres.in/api/users")
		  .jsonPath().getInt("id ");  // .jsonPath().getInt("id "); Remember this capture the id
	
	System.out.println("generated id "+id);
		  
		//.then()
		//  .statusCode(201)
	  //.log().all();
		  
		
	}
     
     @Test(priority=3, dependsOnMethods = {"createUser"})  // By using dependOnMethos before executing this methods first check create user 
                                                           //is pass or not then it executing otherwise it will skipped
     void updateUser() {
    	 
    	 
    	 
    		HashMap<String, String> hm = new HashMap <String, String>();
    		hm.put("name", "Merry");
    		hm.put("job","Trainer");
    		
    		 given()
    		     .contentType("application/json")
    		     .body(hm)
    		.when()
    		  .put("https://reqres.in/api/users/"+ id)
    	      .then()
   		      .statusCode(200)
   		      .log().all() ;
    	 
     }
     
     @Test(priority =4)
     void deleteUser() {
    	 given()
    	 
    	 .when()
    	    .delete("https://reqres.in/api/users/"+id)
    	 
    	 .then()
    	    .statusCode(204)
    	    .log().all();
     }
     
     


	

}
