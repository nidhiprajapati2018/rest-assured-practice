package newRest;

import org.testng.Assert;
import org.testng.annotations.Test;

import File.Payload;
import io.restassured.path.json.JsonPath;


public class SumValidation {
	@Test
	public  void sumOfCourse() {
	
  JsonPath js = new JsonPath(Payload.coursePrice());
	

  int totalcount = js.getInt("courses.size()");
  int purchaseAmount = js.getInt("dashboard.purchaseAmount");

  int sum = 0;
  for(int i=0; i<totalcount; i++) 
  {
	int price = js.getInt("courses["+i+"].price");
	int copies = js.getInt("courses["+i+"].copies");
	int amount = price*copies;
	System.out.println(amount);
	
	sum = sum+ amount;
	}
  System.out.println("sum "+sum);
  Assert.assertEquals(sum,purchaseAmount);
	
  }


}
