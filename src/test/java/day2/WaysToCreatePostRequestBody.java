package day2;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/* Different ways to create POST request body
1. Post rewuest body using Hashmap 
2.Post request body creation using ORG.JSON
3. Post rewuest body creation using POJO class
4. Post request using external json file data
*/



public class WaysToCreatePostRequestBody {
	
	//1.	
	
	//@Test(priority=1)
	void testPostUsingHashMap() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Scott");
		data.put("location", "Canada");
		data.put("phone", 123425);
		
		String courseArr [] = {"c", "java"};
		
		data.put("course", courseArr);
		
		given()
		    .contentType("application/json")
		    .body(data)
		.when()
		    .post("http://localhost:3000/Student")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			
			.body("phone", equalTo(123425))
			.body("location", equalTo("Canada"))
			.body("course[0]", equalTo("c"))
			.body("course[1]", equalTo("java"))
			.header("Content-Type", "application/json")
			.log().all();
	}

//2. Post request body using org.json lib	
	
			//@Test(priority=1)
			void testPostUsingJsonLibrary() {
				
				JSONObject data = new JSONObject();
				
				data.put("name", "Rob");
				
				data.put("phone", 123005);
				data.put("location", "Canada");
				String courseArr [] = {"JavaScript", "Python"};
				
				data.put("course", courseArr);
				
			
			    given()
				    .contentType("application/json")
				    .body(data.toString())
				.when()
				    .post("http://localhost:3000/Student")
				.then()
					.statusCode(201)
					.body("name", equalTo("Rob"))
					
					.body("phone", equalTo(123005))
					.body("location", equalTo("Canada"))
					.body("course[0]", equalTo("JavaScript"))
					.body("course[1]", equalTo("Python"))
					.header("Content-Type", "application/json")
					.log().all();
				
				
			}
			
//3. Post request body creation using POJO class
			
			//@Test(priority=1)
			void testPostUsingPojoClass() {
				Pojo_PostRequest data = new Pojo_PostRequest();
				
				data.setName("merry");
				data.setPhone(123025);
				data.setLocation("NewYark");
				
				String courseArr [] = {"HTML", "CSS"};
			    data.setCourse(courseArr);
				
			
			    given()
				    .contentType("application/json")
				    .body(data)
				.when()
				    .post("http://localhost:3000/Student")
				.then()
					.statusCode(201)
					.body("name", equalTo("merry"))
					
					.body("phone", equalTo(123025))
					.body("location", equalTo("NewYark"))
					.body("course[0]", equalTo("HTML"))
					.body("course[1]", equalTo("CSS"))
					.header("Content-Type", "application/json")
					.log().all();
				
				
			}
			
			
			
//4. Post request body  using external json file
			
			@Test(priority=1)
			void testPostUsingExternalJsonFile() throws FileNotFoundException {
		       
				
				File f = new File("/Users/nidhiprajapati/eclipse-workspace/RestAssured/body.json");
				
				FileReader fr = new FileReader(f);
				
				JSONTokener jt = new JSONTokener(fr);
				
				JSONObject data = new JSONObject(jt);
				
			
			    given()
				    .contentType("application/json")
				    .body(data.toString())
				.when()
				    .post("http://localhost:3000/Student")
				.then()
					.statusCode(201)
					.body("name", equalTo("Ritu"))
					
					.body("phone", equalTo(12341))
					.body("location", equalTo("phoenix"))
					.body("course[0]", equalTo("c"))
					.body("course[1]", equalTo("java"))
					.header("Content-Type", "application/json")
					.log().all();
				
				
			}
			
			
			
			
	
	
		
		@Test(priority=2)
		void testDelete() {
			
		given()
		
		.when()
			.delete("http://localhost:3000/Student/b7a7")
			   
			
		.then()
			.statusCode(200);
			
		}
		
	
		
		
		
}
