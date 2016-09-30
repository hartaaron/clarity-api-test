package clarity.api.test;

import com.google.gson.Gson;
import clarity.api.model.Environment;
import clarity.api.model.LoginCredentials;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import static io.restassured.RestAssured.given;

public abstract class ClarityApiTest
{
	@Rule
	public TestName testName = new TestName();

	/* use AssertJ instead of Hamcrest for checking multiple assertions */
//	@Rule
//	public ErrorCollector check = new ErrorCollector();
	SoftAssertions check = new SoftAssertions();

	Environment env;
	LoginCredentials validLoginCredentials;

	RequestSpecification jsonRequest;
	RequestSpecification clarityRequest;
	Response response;

	Gson gson = new Gson();

	@Before
	public void setUp()
	{
		// get the base URL based on the environment or system property

		//TODO: make environment setting configurable
		env = Environment.TEST;
		if (env.CLARITY_URL.isEmpty()) {
			String url = System.getProperty("CLARITY_TEST_URL");
		}

		// get Clarity user login credentials from a system property

		System.out.println("\n========== TEST STARTING: " + getTestName() + " ==========\n");

		String email = System.getProperty("CLARITY_USER_EMAIL");
		String password = System.getProperty("CLARITY_USER_PASSWORD");

		if (email == null) {
			System.out.println("--- need to set system property: CLARITY_USER_EMAIL");
		}
		if (password == null) {
			System.out.println("--- need to set system property: CLARITY_USER_EMAIL");
		}

		validLoginCredentials = new LoginCredentials();
		validLoginCredentials.email = email;
		validLoginCredentials.password = password;

		// create base request and set default headers for using JSON
		jsonRequest = given().log().all()
				.contentType("application/json")
				.accept("application/json");

		// create clarity base request and set Clarity base URL
		clarityRequest = jsonRequest.with().baseUri(env.CLARITY_URL);
	}



	@After
	public void tearDown()
	{
		System.out.println("\n========== TEST COMPLETE: " + getTestName() + " ==========\n");
	}

	String getTestName() {
		return this.getClass().getSimpleName() + " " + testName.getMethodName();
	}

	String printResponse(Response response)
	{
		System.out.println("\n----- RESPONSE -----\n");
		System.out.println(response.statusLine());
		System.out.println(response.headers());
		System.out.println();
//		System.out.println(response.body().asString());

		return response.prettyPrint();
	}
}
