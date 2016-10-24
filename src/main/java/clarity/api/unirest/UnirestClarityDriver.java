package clarity.api.unirest;

import clarity.api.endpoints.ClarityResponseBody;
import clarity.api.endpoints.access.AccessEndpoint;
import clarity.api.endpoints.access.AccessData;
import clarity.util.Logger;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import clarity.api.util.GsonObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

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
		AccessEndpoint accessEndpoint = new AccessEndpoint(env);
		HttpResponse<String> response = accessEndpoint.send(user);

		String json = response.getBody();
		ClarityResponseBody result = new ClarityResponseBody().fromJson(json);

		if (! result.success)
		{
			log.write("login request failed");
			log.write("response: " + json);
			return null;
		}

		AccessData data = new AccessData().fromJson(result.data.toString());
		user.user_id = data.user_id;
		user.x_access_token = data.x_access_token;

		this.user = user;
		return user;
	}
}
