import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

import files.payload;

public class LakshmiMart {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://qz73e.mocklab.io";
		//SessionFilter session = new SessionFilter();
		//LoginPage
		String response = given().log().all().header("Content-Type","application/json")
		.body(payload.LoginPageDetails()).when().post("/lakshmi/9")
		.then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath js = new JsonPath(response);
		String valueOfToken = js.get("token");
		System.out.println(valueOfToken);
		
		//Dashboard 

		given().log().all().header("Content-Type","application/json").header("token",valueOfToken)
		.body(payload.DashboardDetails()).when().post("/lakshmi/dashboard")
		.then().log().all().assertThat().statusCode(200).body("product", equalTo("Gold Rings"))
		.header("Matched-Stub-Name", "lakshmi-dashboard");
	}	

}
