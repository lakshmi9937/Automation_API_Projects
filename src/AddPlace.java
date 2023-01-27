import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlace {
	
	public static String placeCreation() {
		
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
		
		return placeId;
	}

}
