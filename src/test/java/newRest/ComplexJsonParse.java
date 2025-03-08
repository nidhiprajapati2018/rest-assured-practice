package newRest;

import File.Payload;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(Payload.coursePrice());
		
		//1Print No of courses returned by API
		
		int count = js.getInt("courses.size()");
        System.out.println(count); //3
        
        //2.Print Purchase Amount
       int totalamount = js.getInt("dashboard.purchaseAmount"); //910
       System.out.println(totalamount);
       
      //3. Print Title of the first course 
     
      String titleFirstCourse = js.get("courses[0].title");
      System.out.println("first course "+titleFirstCourse);
      
      //4. Print All course titles and their respective Prices
      for(int i=0; i<count; i++) {
      String courseTitle =  js.get("courses["+i+"].title");
      System.out.println(courseTitle);
      System.out.println(js.get("courses["+i+"].price").toString());
      
      }
      
      //5. Print no of copies sold by RPA Course
      for(int i=0; i<count; i++) {
    	  
    	 String courseTitle = js.get("courses["+i+"].title");
    	 
    	 if(courseTitle.equalsIgnoreCase("RPA")) {
    		System.out.println("RPA"+js.get("courses["+i+"].copies").toString());
    		 break;
    	 }
    	 
      }
       // Verify if sum of all course price matches with purchase Amount
    	 
			
			  int priceSum=0; 
			  for(int i=0; i<count; i++) { 
				  priceSum = priceSum+ (js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies")); }
			  System.out.println("Price "+priceSum);
			  
			  Assert.assertEquals(priceSum, totalamount);
			  
			 
    
      }
      
	

}
