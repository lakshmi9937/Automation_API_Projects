import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;
public class Basic {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// Add Place
		
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.addPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId=js.getString("place_id");		
		System.out.println(placeId);
		
		//Update Place
		
		String newAddress ="70 winter walk, USA";
		given().log().all().queryParam("key", "qaclick").header("COntent-Type", "application/json")
		.body("{\r\n"
				+ "			\"place_id\":\""+placeId+"\",\r\n"
				+ "			\"address\":\""+newAddress+"\",\r\n"
				+ "			\"key\":\"qaclick123\"\r\n"
				+ "			}")
		.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json").then().log().all().assertThat().body(matchesJsonSchemaInClasspath("scheema.json"))
		.statusCode(200).body("location.latitude", equalTo("-38.383494"))
		.extract().response().asString();
		
		//JsonPath js1 = new JsonPath(getPlaceResponse);
		JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println("Actual Address is----" + actualAddress);
		
		Assert.assertEquals(actualAddress,newAddress);
		
		
		
	}

}
