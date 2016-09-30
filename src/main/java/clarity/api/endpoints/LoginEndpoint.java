package clarity.api.endpoints;

import com.google.gson.Gson;
import clarity.api.model.LoginCredentials;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginEndpoint
{
	public static String path = "/mm/v2/token";
	public static String loginJsonTemplate = " {\"email\":\"{{email}}\",\"password\":\"{{password}}\"} ";

	public URL baseUrl;
	public String email;
	public String password;

	public Map<String, String> headers;

	protected Gson gson;

	public LoginEndpoint()
	{
		init(null);
	}
	public LoginEndpoint(URL baseUrl)
	{
		init(baseUrl);
	}
	public LoginEndpoint(String baseUrl) throws MalformedURLException
	{
		this.baseUrl = new URL(baseUrl);
	}

	public void init(URL baseUrl)
	{
		this.baseUrl = baseUrl;
		gson = new Gson();
		this.headers = new HashMap<String, String>()
		{{
			put("Content-Type", "application/json");
			put("Accept", "application/json");
		}};
	}

	public String getRequestUrl()
	{
		return baseUrl.toString() + path;
	}

	public Map<String, String> getRequestHeaders() {
		return headers;
	}


	public String getJsonFromTemplate(String email, String password) {
		String loginJson = loginJsonTemplate
				.replace("{{email}}", email)
				.replace("{{password}}", password);

		return loginJson;
	}
	public String getJsonFromTemplate(LoginCredentials credentials) {
		return getJsonFromTemplate(credentials.email, credentials.password);
	}
	public String getJsonFromTemplate() {
		return getJsonFromTemplate(getCredentials());
	}


	public String getRequestBody()
	{
		return getJsonFromTemplate();
//		return gson.toJson(getCredentials());
	}

	public LoginCredentials getCredentials()
	{
		return new LoginCredentials() {{ email = getEmail(); password = getPassword() ; }};
	}
	public void setCredentials(LoginCredentials credentials)
	{
		email = credentials.email;
		password = credentials.password;
	}
	public void setCredentials(String email, String password)
	{
		this.email = email;
		this.password = password;
	}

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}
