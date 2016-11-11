package clarity.api.endpoints.breakglass;

import clarity.api.ClarityEnvironment;
import clarity.api.endpoints.ClarityEndpoint;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public class BreakGlassEndpoint extends ClarityEndpoint
{
		public static String path = "/api/v1/access-logs";
	
	public BreakGlassEndpoint(ClarityEnvironment env)
	{
		super(env, path);
	}
	
	public HttpResponse<String> send(BreakGlassRequestBody body) throws UnirestException
	{
		log.debug("request URL: " + getRequestUrl());
		log.debug("request headers: " + getRequestHeaders());
		log.debug("request body: " + body.toJson());
		
		HttpResponse<String> response = post(getRequestUrl(), getRequestHeaders(), body.toJson());
		
		log.debug("response status: " + response.getStatus() + " " + response.getStatusText());
		log.debug("response body: " + response.getBody());
		
		return response;
	}
}
