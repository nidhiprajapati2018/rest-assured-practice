package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	
	//@Test
	void testCookies() {
		
		
		given()
		.when()
			.get("https://google.com")
		.then()
			.cookies("AEC", "AQTF6HyCdewk7y-jbO49LAA4kzc5DbjBmdvM5VEuS8ir6RXFfh5c69fBiSs")
			.log().all();
	}
	
	
	
	@Test(priority=2)
	void getCookiesInfo() {
		
		
		Response res = given()
		.when()
			.get("https://google.com");
		
		//get single cookies info
		
//		String cookies_value = res.getCookie("AEC");
//	    System.out.println("Value of cookies is--> "+cookies_value);
		
		
		//get all cookies
		
		
		Map<String, String> cookies_value = res.getCookies();
		
		//System.out.println(cookies_value.keySet());
		
		for(String k: cookies_value.keySet() ) {
			
			String cookie_value = res.getCookie(k);
			System.out.println(k+"    "+cookie_value);
		}

	}

}