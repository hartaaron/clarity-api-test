package clarity.test;

import clarity.api.api.endpoints.ClarityApi;
import clarity.api.api.util.HTTP;
import clarity.api.api.util.JSON;
import clarity.api.model.ClarityEnvironment;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;


public class LoginTest extends ClarityApiTest
{
	public String loginJson = " {\"email\":\"{{email}}\",\"password\":\"{{password}}\"} ";

	@Test
	public void login_with_rest_assured()
	{
		RequestSpecification loginRequest = clarityRequest.with().basePath("/mm/v2/token");

		loginJson = loginJson
				.replace("{{email}}", validLoginCredentials.email)
				.replace("{{password}}", validLoginCredentials.password);

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

	@Test
	public void login_with_unirest() throws IOException, UnirestException
	{
		ClarityApi clarityApi = new ClarityApi(ClarityEnvironment.TEST);
		clarityApi.loginEndpoint.setEmail(validLoginCredentials.email);
		clarityApi.loginEndpoint.setPassword(validLoginCredentials.password);

		System.out.println("email: " + clarityApi.loginEndpoint.getEmail());
		System.out.println("password: " + clarityApi.loginEndpoint.getPassword());
		System.out.println("JSON body: " + clarityApi.loginEndpoint.getJsonCredentials());

		System.out.println("Request URL: " + clarityApi.loginEndpoint.getRequestUrl());
		System.out.println("path: " + clarityApi.loginEndpoint.getPath());

		HttpRequestWithBody loginRequest = Unirest.post(clarityApi.loginEndpoint.getRequestUrl());
		RequestBodyEntity x  = loginRequest
				.headers(clarityApi.loginEndpoint.getRequestHeaders())
				.body(clarityApi.loginEndpoint.getRequestBody());

		printUnirestRequest(loginRequest);

		HttpResponse<JsonNode> loginResponse = loginRequest.asJson();

		System.out.println("\n----- RESPONSE -----\n");
		System.out.println(loginResponse.getStatus());
		System.out.println(loginResponse.getHeaders());
		System.out.println(loginResponse.getBody());
	}

	void printUnirestRequest(HttpRequestWithBody request) throws IOException
	{
		System.out.println("\n----- REQUEST -----\n");
		System.out.println(request.getHttpMethod() + " " + request.getUrl());

		// print headers
		Map<String, List<String>> headers = request.getHeaders();
		for(String key : headers.keySet()){
			List<String> values = headers.get(key);
			for(String value : values) {
				System.out.println(key + " : " + value);
			}
		}

		InputStream in = request.getBody().getEntity().getContent();
		String body = IOUtils.toString(in, "UTF-8");
		in.close();

		System.out.println(body);
	}
}
