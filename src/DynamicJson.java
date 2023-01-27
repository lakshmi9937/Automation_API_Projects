import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
public class DynamicJson {
	
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type","application/json")
		.body(payload.addBook(isbn, aisle)).when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.getString("ID");
		System.out.println(id);
		
		//DELETE
		/*given().log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " \r\n"
				+ "} ").when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200);*/
	
		
	}	
	@DataProvider(name = "BooksData")
		public Object[][] getData()
		{
		//array = collection of elements
		//multidimensional array= collection of arrays
		return new Object[][] {{"$kkk","111"},{"$hhh","222"},{"$eee","333"}};
		}
		
		
		
	

}
