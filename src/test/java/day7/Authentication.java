package day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentication {
	
	@Test(priority = 1)
	void testBasicAuthentication() {
		
		given()
			.auth().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority = 2)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}

	
	//@Test(priority = 3)
	void testPreemptiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	
	}
	
	//@Test(priority=4)
	void testBearerTokenAuthentication() {
		
		String bearerToken = "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";
		
		given()
		.headers("Authorization", "Bearer" + bearerToken)
		.when()
			.get("https://api.github.com/user")
		.then()
		   .statusCode(200)
		   .log().all();
	}
	
	//@Test(priority=5)

	void testOAuth1Authentication() {
		
		given()
			.auth().oauth("consumerKey", "consumerSecrat", "accessToken", "tokensecrate")
		.when()
			.get("url")
		.then()
		  .statusCode(200)
		   .log().all();
		  
		
	}
	
	//@Test(priority=6)
	void testOAuth2Authentication() {
		
		given()
		.auth().oauth2("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")
	.when()
		.get("https://api.github.com/user")
	.then()
	  .statusCode(200)
	   .log().all();
		
	}
	
	void testAPIKeyAuthentication() {
		
	}
	
	
	
	
}
