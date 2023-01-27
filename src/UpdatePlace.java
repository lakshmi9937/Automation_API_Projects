import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdatePlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String placeId = AddPlace.placeCreation();
		System.out.println(placeId);
		String newAddress ="70 winter walk, USA";
		given().log().all().queryParam("key", "qaclick").header("COntent-Type", "application/json")
		.body("{\r\n"
				+ "			\"place_id\":\""+placeId+"\",\r\n"
				+ "			\"address\":\""+newAddress+"\",\r\n"
				+ "			\"key\":\"qaclick123\"\r\n"
				+ "			}")
		.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		

	}

}
