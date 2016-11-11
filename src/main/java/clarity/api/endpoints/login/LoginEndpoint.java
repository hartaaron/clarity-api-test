package clarity.api.endpoints.login;

import clarity.api.ClarityEnvironment;
import clarity.api.endpoints.ClarityEndpointBase;
import clarity.api.endpoints.ClarityResponseWithStatus;
import com.mashape.unirest.http.exceptions.UnirestException;

public class LoginEndpoint extends ClarityEndpointBase
{
	public static String path = "/mm/v3/token";
	
	public LoginEndpoint(ClarityEnvironment env)
	{
		super(env, path);
	}
	
	/**
	 * Send a request including the user access credentials (i.e.  email, password)
	 * and get a response including the x-access-token and user_id
	 *
	 * @return
	 */
	public UserAccessResult send(UserAccessCredentials credentials) throws UnirestException
	{
		String requestBody = credentials.toJson();
		response = post(getRequestUrl(), getRequestHeaders(), requestBody);
	    
		String json = response.getBody();
		log.debug("json: " + json);
		
		ClarityResponseWithStatus result = new ClarityResponseWithStatus().fromJson(json);
		log.debug("result: " + result);
		
		String data = result.data.toString();
		
		log.debug("data: " + data);
		
		return new UserAccessResult().fromJson(data);
	}
	
	public UserAccessResult send(String requestBody) throws UnirestException
	{
		response = post(getRequestUrl(), getRequestHeaders(), requestBody);
		
		String json = response.getBody();
		log.debug("json: " + json);
		
		ClarityResponseWithStatus result = new ClarityResponseWithStatus().fromJson(json);
		log.debug("result: " + result);
		
		String data = result.data.toString();
		
		log.debug("data: " + data);
		
		return new UserAccessResult().fromJson(data);
	}
}
