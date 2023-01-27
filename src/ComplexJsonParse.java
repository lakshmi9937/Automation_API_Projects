import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		System.out.println("***Print No of courses returned by API***");
		int count = js.getInt("courses.size()");		
		System.out.println(count);
		
		System.out.println("****Print Purchase Amount***");
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		System.out.println("***Print Title of the first course***");
		String title1 = js.get("courses[0].title");
		System.out.println(title1);
		
		System.out.println("***Print All course titles and their respective Prices***");
		
		for(int i=0;i<count;i++) 
		{
			String titles=js.get("courses["+i+"].title");
			System.out.println(titles);
			System.out.println(js.getInt("courses["+i+"].price"));
		}
		
		System.out.println("***Print no of copies sold by RPA Course***");
		for(int i=0;i<count;i++) 
		{
			String titles=js.get("courses["+i+"].title");
			if(titles.equalsIgnoreCase("RPA")) 
			{						
			System.out.println(js.getInt("courses["+i+"].copies"));
			break;
			}
		}
	}

}

	
	
	
	
	
	
