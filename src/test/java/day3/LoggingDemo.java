package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class LoggingDemo {
	
	
	@Test(priority=1)
	void testlogs() {
		
		given()
		.when()
			.get("https://reqres.in/api/user?page2")
		.then()
		//.log().all();
		//.log().body();   //only response
		//.log().cookies();  //onli cokkies
		.log().headers();
		
		
	}
	
	
	
	

}
