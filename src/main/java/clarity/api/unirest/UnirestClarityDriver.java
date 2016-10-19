package clarity.api.unirest;

import clarity.api.endpoints.access.AccessCredentials;
import clarity.api.endpoints.access.AccessEndpoint;
import clarity.api.endpoints.access.AccessResult;
import clarity.util.Logger;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import clarity.api.util.GsonObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;

import java.util.HashMap;
import java.util.Map;

public class UnirestClarityDriver
{
	ClarityEnvironment env;
	ClarityUser user;

	GsonObjectMapper gson;

	Logger log;

	static final String APPLICATION_JSON = "application/json";

	static Map<String, String> headers = new HashMap<String, String>()
	{{
		put("Content-Type", APPLICATION_JSON);
		put("Accept", APPLICATION_JSON);
	}};

	public UnirestClarityDriver(ClarityEnvironment env)
	{
		this.env = env;

		gson = new GsonObjectMapper();
		Unirest.setObjectMapper(gson);

		log = new Logger(this.getClass());
		log.write("initialized with baseUrl: " + env.CLARITY_BASE_URL);
	}

	public ClarityUser login(ClarityUser user) throws Exception
	{
		AccessCredentials credentials = new AccessCredentials(user.email, user.password);
		AccessEndpoint request = new AccessEndpoint(env);

		try
		{
			HttpResponse<String> response = request.send(credentials);
			String json = response.getBody();
			log.write("response body: " + json);
			AccessResult result = gson.readValue(json, AccessResult.class);

			if (result.success)
			{
				user.user_id = result.data.user_id;
				user.x_access_token = result.data.x_access_token;
				this.user = user;
			}
		}
		catch (UnirestException e)
		{
			log.write("Unirest error?");
			e.printStackTrace();
			throw e;
		}
		catch (Exception e)
		{
			log.write("Login failed?");
			e.printStackTrace();
		}

		return user;
	}
}
