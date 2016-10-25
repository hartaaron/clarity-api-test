package clarity.api.endpoints.access;

import clarity.api.UnirestPrinter;
import clarity.api.endpoints.ClarityEndpoint;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

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
		
		log.write("request URL: " + requestUrl);
		log.write("request headers: " + getRequestHeaders());
		log.write("request body: " + requestBody);
		
		response = post(getRequestUrl(), getRequestHeaders(), requestBody);
		String responseJson = response.getBody();
		
		log.write("response status: " + response.getStatus() + " " + response.getStatusText());
		log.write("response json: " + responseJson);

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
		log.write("*** USER: " + user.toJson());
		return send(user.email, user.password);
	}



}
