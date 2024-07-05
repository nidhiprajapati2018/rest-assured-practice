package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {

	//@Test(priority=1)
	void testJSONResponse() {
		
		//Approch1
		
		/*given()
			.contentType("ContentTypr.JSON")
		.when()
			.get("http://localhost:3000/book")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json")
		.body("[3].title",equalTo("saying of the century")); */
		
		//Approch2
		
		Response res = given()
				.contentType("ContentTypr.JSON")
		.when()
				.get("http://localhost:3000/book");
		
		Assert.assertEquals(res.getStatusCode(),200);  //validation1
		Assert.assertEquals(res.header("Content-Type"),"application/json" );
		
		String bookName = res.jsonPath().get("[3].title").toString();
		
		Assert.assertEquals(bookName, "saying of the century");
		
		
	}
	
	@Test(priority=2)
	void testJSONResponseBodyData() {
		
		
		Response res = given()
				.contentType(ContentType.JSON)
		.when()
				.get("http://localhost:3000/store");
		
//		Assert.assertEquals(res.getStatusCode(),200);  //validation1
//		Assert.assertEquals(res.header("Content-Type"),"application/json" );
//		
//		String bookName = res.jsonPath().get("[3].title").toString();
//		
//		Assert.assertEquals(bookName, "saying of the century");
		
		//JSONObject class we already add dependency in pom.xml
		//Search the title of the book
		boolean status = false;
		
		JSONObject jo = new JSONObject(res.asString());  //Converting response into json object type
		
		for(int i=0; i<jo.getJSONArray("book").length(); i++ ){
			
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
			
			if(bookTitle.equals("saying of the century")) {
				status =true;
				break;
			}
			
		}
		
		Assert.assertEquals(status,true);
		
		//validate the total price of the book 
		
		double totalprice = 0;
		
	for(int i=0; i<jo.getJSONArray("book").length(); i++ ){
			
			String bookPrice = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
		
			totalprice += Double.parseDouble(bookPrice);
			
		}
		System.out.println(totalprice);
		
		Assert.assertEquals(totalprice, 1220.8);
		
		
	}
	
	
}
