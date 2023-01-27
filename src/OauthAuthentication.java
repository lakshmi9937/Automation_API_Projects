import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OauthAuthentication {

	public static void main(String[] args) throws InterruptedException {
		
//	  System.setProperty("webdriver.driver.chrome", "C://chromedriver.exe");
//	  WebDriver driver = new ChromeDriver();
//	  driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//    driver.findElement(By.cssSelector("input[type='email']")).sendKeys("lakshmi9937@gmail.com");
//    driver.findElement(By.cssSelector("span.VfPpkd-vQzf8d")).click();
//    Thread.sleep(3000);
//    driver.findElement(By.cssSelector("input[type='password']")).sendKeys("********");
//    driver.findElement(By.cssSelector("span.VfPpkd-vQzf8d")).click();
//    String url1 = driver.getCurrentUrl();
		
	// GET CODE
		
	String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qiz8wUADDspi7xAM4ZvqRtHS5pVAfTiuss7YSohs_qADNamrNz-XEHrMsUbhJNk5Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
	
	String partialCode= url.split("code=")[1];
	String code= partialCode.split("&scope")[0];
	System.out.println(code);
	
	// GET ACCESS TOKEN
	
	String response= given().urlEncodingEnabled(false).log().all()
	.queryParams("code",code)
	.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
	.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
	.queryParams("grant_type","authorization_code")
	.when().post("https://www.googleapis.com/oauth2/v4/token").asString();

	JsonPath js =new JsonPath(response);
	String accessToken= js.getString("access_token");	
	
	
	// GET ACTUAL REQUEST
	
	given().log().all().queryParam("access_token", accessToken)
	.when().get("https://rahulshettyacademy.com/getCourse.php")
	.then().log().all().assertThat().statusCode(200);
	
	}

}



