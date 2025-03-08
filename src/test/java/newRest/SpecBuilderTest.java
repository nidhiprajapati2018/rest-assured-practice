package newRest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import newRestPOJO.LocationPojo;
import newRestPOJO.MapPOJO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import File.Payload;

public class SpecBuilderTest {

	public static void main(String[] args) {
		
		MapPOJO m = new MapPOJO();
	    m.setLocation(null);
	    m.setAcuracy(50);
	    m.setAddress("29, side layout, cohen 09");
	    m.setLanguage("English");
	    m.setName("Testing");
	    m.setPhoneNumber("98435267294");
	    m.setWebsite("https://rahulshettyacademy");
	    
	    List<String>myList = new ArrayList<String>();
	    myList.add("shoepark");
	    myList.add("shop");
	    m.setTypes(myList);
	    
	    LocationPojo lp = new LocationPojo();
	    lp.setLat(-38.38);
	    lp.setLng(33.4254);
	    m.setLocation(lp);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		 ResponseSpecification ressep = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req)
		.body(m);
		
		Response response = res.when().post("maps/api/place/add/json")
		.then().spec(ressep).extract().response();
		
		String responceString = response.asString();
		
		System.out.println(responceString);
	

	}

}
