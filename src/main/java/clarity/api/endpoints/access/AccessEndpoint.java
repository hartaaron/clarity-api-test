package clarity.api.endpoints.access;

import clarity.api.endpoints.ClarityEndpoint;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import com.mashape.unirest.http.HttpResponse;

public class AccessEndpoint extends ClarityEndpoint
{
	public static String path = "/mm/v3/token";

	public AccessEndpoint(ClarityEnvironment env)
	{
		super(env, path);
	}

	public HttpResponse<String> send(AccessCredentials credentials) throws Exception
	{
		String requestUrl = getRequestUrl();
		String requestBody = credentials.toJson();
		
		log.debug("request URL: " + requestUrl);
		log.debug("request headers: " + getRequestHeaders());
		log.debug("request body: " + requestBody);
		
		response = post(getRequestUrl(), getRequestHeaders(), requestBody);
		
		log.debug("response status: " + response.getStatus() + " " + response.getStatusText());
		log.debug("response json: " + response.getBody());

		return response;
	}

	public HttpResponse<String> send(String email, String password) throws Exception
	{
		AccessCredentials credentials = new AccessCredentials(email, password);
		return send(credentials);
	}

	public HttpResponse<String> send(ClarityUser user) throws Exception
	{
		System.out.println("user: " + user);
		log.debug("*** USER: " + user.toJson());
		return send(user.email, user.password);
	}



}
