import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;

public class AddUser 
{	
	@Test(dataProvider = "UsersData")
	public  void userDetails(String Name, int sal, String Location)
	{
   		RestAssured.baseURI = "https://reqres.in";
		given().log().all().header("Content-Type", "application/json")
		.body(payload.userdeatails(Name, sal, Location))
		.when().post("api/users").then().log().all().assertThat()
		.statusCode(201).body("name",equalTo(""+Name+""))
		.header("Server", "cloudflare");
	}
	
	@DataProvider(name="UsersData")
	public Object[][] getData()
	{
		return new Object[] [] {{"Rama",100000,"Hyderabad"},{"Roja",200000,"Chennai"},{"lakshmi",300000,"Bangalore"}};
	}

}
