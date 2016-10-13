package clarity.api.endpoints;

import clarity.api.model.ClarityEnvironment;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;

public class LoginEndpoint extends ClarityEndpoint
{
	public String email;
	public String password;

	public static String path = "/mm/v2/token";
	public static String loginJsonTemplate = "{\"email\":\"{{email}}\",\"password\":\"{{password}}\"}";

	public LoginEndpoint(ClarityEnvironment env)
	{
		super(env, LoginEndpoint.path);
	}

	public String getRequestBody()
	{
		return getJsonCredentials(email, password);
//		return gson.toJson(getCredentials());
	}

	public String getJsonCredentials(String email, String password)
	{
		String loginJson = loginJsonTemplate
				.replace("{{email}}", email)
				.replace("{{password}}", password);

		return loginJson;
	}
	public String getJsonCredentials()
	{
		return getJsonCredentials(email, password);
	}

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public HttpResponse<JsonNode> send() throws UnirestException
	{
		RequestBodyEntity loginRequest = Unirest
				.post(getRequestUrl())
				.headers(getRequestHeaders())
				.body(getRequestBody());

		response = loginRequest.asJson();

		printUnirestResponse(response);

		return response;
	}
}
