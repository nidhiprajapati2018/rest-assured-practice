package newRest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import newRestPOJO.LocationPojo;
import newRestPOJO.MapPOJO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import File.Payload;

public class serializeTest {

	public static void main(String[] args) {
		
		MapPOJO m = new MapPOJO();
	   
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
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(m)
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(response);
	

	}

}
