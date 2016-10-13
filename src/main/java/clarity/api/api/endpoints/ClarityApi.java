package clarity.api.api.endpoints;

import clarity.api.api.util.Logger;
import clarity.api.endpoints.LoginEndpoint;
import clarity.api.model.ClarityEnvironment;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;


public class ClarityApi
{
	Logger log;
	ClarityEnvironment env;

	public LoginEndpoint loginEndpoint;

	public ClarityApi(ClarityEnvironment env)
	{
		init(env);
	}

	public void init(ClarityEnvironment env)
	{
		log = new Logger(this.getClass());
		this.env = env;

		loginEndpoint = new LoginEndpoint(ClarityEnvironment.TEST);

		log.write("initialized environment: " + env.name + " with baseUrl: " + env.CLARITY_URL);
	}

	public void login(String email, String password) throws UnirestException
	{
		log.write("login...");

		loginEndpoint.setEmail(email);
		loginEndpoint.setPassword(password);
		HttpResponse response = loginEndpoint.send();

		// store authToken
		System.out.println(response.getRawBody());
	}

}
