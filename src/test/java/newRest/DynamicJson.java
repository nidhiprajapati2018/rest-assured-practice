package newRest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import File.Payload;
import File.ReUsableMethods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson {
	
	@Test(dataProvider="bookdata")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";  ///Library/Addbook.php
		
		String response = given().header("Content-Type", "application/json")
				.body(Payload.addBook(isbn,aisle))
				.when()
				.post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		
		System.out.println("Response"+response);
		
		JsonPath js = ReUsableMethods.rawToJson(response);
				
			String id = js.get("ID");
			System.out.println(id);
			
			//delete the book
			given().body("{\n"
					+ "    \"ID\" : \""+id+"\"\n"
					+ "}")
			.when()
			.delete("Library/DeleteBook.php")
			.then().assertThat().log().all().
			  statusCode(200).body("msg",equalTo("book is successfully deleted"));
			
			
	}
	
	@DataProvider(name="bookdata")
	public Object [][] getData() {
		
		Object [][] data = new Object[][] {{"tfer","2314"},{"rtes","3241"},{"reds","5432"}};
		return data;
	}

}
