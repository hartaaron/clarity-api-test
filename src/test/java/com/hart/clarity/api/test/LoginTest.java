package com.hart.clarity.api.test;

import com.hart.util.HTTP;
import com.hart.util.JSON;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class LoginTest extends ClarityApiTest
{
	public String loginJson = " {\"email\":\"{{email}}\",\"password\":\"{{password}}\"} ";

	@Test
	public void should_login_with_valid_user()
	{
		RequestSpecification loginRequest = clarityRequest.with().basePath("/mm/v2/token");

		loginJson = loginJson
				.replace("{{email}}", validUser.email)
				.replace("{{password}}", validUser.password);

		Response response = loginRequest.with().body(loginJson)
				.when().post().then().extract().response();

		String jsonResult = printResponse(response);

		assertThat(JSON.isValid(jsonResult));

		Boolean success = from(jsonResult).get("success");
		Integer code = from(jsonResult).get("code");
		String userId = from(jsonResult).get("data.user_id");
		String xAccessToken = from(jsonResult).get("data.x-access-token");

		check.assertThat(success).isEqualTo(true);
		check.assertThat(code).isEqualTo(HTTP.Status.OK);
		check.assertThat(userId).isNotEmpty().hasSize(32);
		check.assertThat(xAccessToken).isNotEmpty();
		check.assertAll();

		clarityRequest.header("x-access-token", xAccessToken);
	}
}
