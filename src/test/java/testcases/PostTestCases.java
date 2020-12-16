package testcases;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.testng.Assert;
import org.testng.annotations.Test;
import auth.BasicAuth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class PostTestCases extends BasicAuth {

	public static void getAuth() {
		String user = "NageshSadashiv";
		System.out.println(new BasicAuth().getAuthString(user));
	}

	String baseURL = "https://reqres.in";

	@Test
	public void simplePostCreateUser() throws FileNotFoundException {
		FileInputStream fis = new FileInputStream("src/test/java/payload/CreateUser.json");
		RestAssured.baseURI = baseURL;

		given()
		.contentType("application/json")
		.body(fis)
		.when()
		.post("/api/users")
		.then()
		.assertThat()
		.statusCode(201)
		.body(matchesJsonSchemaInClasspath("CreateUserSchema.json"));
//		System.out.println("Create User Response " + resp.asString());
//		Assert.assertEquals(resp.getStatusCode(),201);

	}

	
	public void registerUser() throws FileNotFoundException {
		FileInputStream fis = new FileInputStream("src/test/java/payload/RegUser.json");
		RestAssured.baseURI = baseURL;
		Response resp = 
				given()
				.contentType("application/json")
				.body(fis)
				.when()
				.post("/api/register");
		System.out.println("Register user response "+ resp.asString());
		Assert.assertEquals(resp.getStatusCode(),200);
	}
}
