package clarity.api.endpoint;

import clarity.api.endpoints.ClarityEndpoint;
import clarity.api.endpoints.LoginEndpoint;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginEndpoint_Test
{
	LoginEndpoint loginEndpoint;

	ClarityUser user;

	@Before
	public void setUp()
	{
		loginEndpoint = new LoginEndpoint(ClarityEnvironment.TEST);

		user = new ClarityUser()
		{{
			email = "clarity-external-testing@hart.com";
			password = "Cl@rity1";
		}};
	}

	@Test
	public void should_get_base_url_from_env_setting()
	{
		String expectedBaseUrl = ClarityEnvironment.TEST.CLARITY_URL;
		assertThat(loginEndpoint.getBaseUrl()).isEqualTo(expectedBaseUrl);
	}

	@Test
	public void should_set_path_in_base_class_from_static_property()
	{
		String expectedPath = LoginEndpoint.path;
		assertThat(loginEndpoint.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void should_build_request_url_from_env_and_path()
	{
		String expectedUrl = "https://clarity-tst.hart.com/mm/v2/token";
		assertThat(loginEndpoint.getRequestUrl()).isEqualTo(expectedUrl);
	}

	@Test
	public void should_have_content_type_header_appication_json()
	{
		String expectedContentType = "application/json";
		assertThat(loginEndpoint.getRequestHeaders().get("Content-Type")).isEqualTo(expectedContentType);
	}

	@Test
	public void should_have_accept_header_application_json()
	{
		String expectedAccept = "application/json";
		assertThat(loginEndpoint.getRequestHeaders().get("Accept")).isEqualTo(expectedAccept);
	}

	@Test
	public void should_generate_json_body_when_email_and_password_are_specified()
	{
		String email = "test@example.com";
		String password = "secret123";

		String json = loginEndpoint.getJsonCredentials(email, password);

		String expectedJson = "{\"email\":\"test@example.com\",\"password\":\"secret123\"}";

		assertThat(json).contains(email);
		assertThat(json).contains(password);
		assertThat(json).isEqualTo(expectedJson);
	}

	@Test
	public void should_generate_json_body_when_email_and_password_have_been_set()
	{
		String email = "test@example.com";
		String password = "secret123";

		loginEndpoint.setEmail(email);
		loginEndpoint.setPassword(password);

		String json = loginEndpoint.getJsonCredentials(email, password);

		String expectedJson = "{\"email\":\"test@example.com\",\"password\":\"secret123\"}";

		assertThat(json).contains(email);
		assertThat(json).contains(password);
		assertThat(json).isEqualTo(expectedJson);
	}

	@Test
	public void should_have_request_body()
	{
		String email = "test@example.com";
		String password = "secret123";

		loginEndpoint.setEmail(email);
		loginEndpoint.setPassword(password);

		String expectedJson = "{\"email\":\"test@example.com\",\"password\":\"secret123\"}";

		assertThat(loginEndpoint.getRequestBody()).isEqualTo(expectedJson);
	}

	@Test
	public void should_send_request_and_return_response() throws Exception
	{
		loginEndpoint.setEmail(user.email);
		loginEndpoint.setPassword(user.password);

		HttpResponse response = loginEndpoint.send();

		assertThat(response).isNotNull();
	}

	@Test
	public void should_have_json_response_body() throws Exception
	{
		String email = "clarity-external-testing@hart.com";
		String password = "Cl@rity1";

		loginEndpoint.setEmail(email);
		loginEndpoint.setPassword(password);

		HttpResponse<JsonNode> response = loginEndpoint.send();

		assertThat(response.getHeaders().get("Content-Type")).isEqualTo("application/json");


		JsonNode json = response.getBody();
		System.out.println("---data");

		assertThat(json).isNotNull();
		assertThat(json).isInstanceOf(JsonNode.class);


		String jsonString = response.getBody().toString();
		assertThat(jsonString).startsWith("{");
		assertThat(jsonString).endsWith("}");

		System.out.println(json.getObject().get("data"));

		JSONObject data =  (JSONObject) json.getObject().get("data");

		String user_id = (String) data.get("user_id");
		String x_access_token = (String) data.get("x-access-token");


	}

	@Test
	public void should_have_user_id_and_x_access_token_in_response() throws Exception
	{
		loginEndpoint.setEmail(user.email);
		loginEndpoint.setPassword(user.password);

		HttpResponse<JsonNode> response = loginEndpoint.send();

		JSONObject data = (JSONObject) response.getBody().getObject().get("data");
		String user_id = (String) data.get("user_id");
		String x_access_token = (String) data.get("x-access-token");

		assertThat(user_id).isNotEmpty();
		assertThat(x_access_token).isNotEmpty();
	}

}
