package day1;



import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;




import static org.hamcrest.Matchers.containsString;

import io.restassured.RestAssured;
import  io.restassured.path.json.JsonPath;


	
public class Validations {
		String url = "http://postalpincode.in/api/pincode/560100";
		
	    @Test	
		public void responseTimeValidation() {

		 Response response = given().
				           header("content-type", "Application.josn").
				           when().
				           get(url).
				           then().
				           assertThat().
				           time(lessThan(5000L)).
				           statusCode(200).
				           body("Status",equalTo("Success")).
				           body(containsString("Message")).
				           body("Message",equalTo("Number of Post office(s) found: 2")).
				           body("PostOffice[0].Country",equalTo("India")).
				           body("PostOffice[0].Description", is("")).
				           extract().
				           response();
		 System.out.println(response.body().asPrettyString());
	}
	    @Test	
		public int getNoofPostOffice_Found() {
	    	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		 String responseBody = given().
				           when().
				           get(url).
				           then().
				           assertThat().
				           statusCode(200).
				           extract().
				           response().getBody().asString();
		   JsonPath jsonPath = new JsonPath(responseBody);
		   String message = jsonPath.getString("Message");
		   int noOfPostOffices = Integer.parseInt((message).replaceAll("\\D+", ""));
		   assertThat(noOfPostOffices,greaterThan(0));
		   
		   System.out.println("No of postoffice Found"+ noOfPostOffices);
		   assertThat(jsonPath.getList("PostOffice.DeliveryStatus"),hasItems("Delivery","Non-Delivery"));
		   
			  String resonseMessage = given().
			           when().
			           get(url).
			           then().
			           assertThat().
			           statusCode(200).
			           extract().
			           path("Message");
			   System.out.println("Message"+ resonseMessage);

			  
			   return noOfPostOffices;


		                 
	}
	    @Test
	    public void responseValidation() {
	    	int noOfPostOffices = getNoofPostOffice_Found();
	    	 given()
	    	.when()
	    	.get(url)
	    	.then().assertThat().statusCode(200)
	    	.body("PostOffice.size()",is(noOfPostOffices),
	    	"PostOffice.findAll {it.District.startsWith('Ban')}.size()", is(noOfPostOffices)
	    	);
	    	
	    }
	   
	

}
