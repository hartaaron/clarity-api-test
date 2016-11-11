package clarity.api.endpoints.breakglass;

import clarity.ClarityApiTestCase;
import clarity.api.util.UnirestPrinter;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BreakGlassEndpoint_Test  extends ClarityApiTestCase
{
	ClarityUser user;
	ClarityPatient patient;
	
	@Before
	public void setUp() throws Exception
	{
		user = clarity.login(validUser);
		
		System.out.println(user.toJson());
		
		List<ClarityPatient> patients = clarity.searchForPatient("ZZITESTSJM", "HARTONE");
		patient = patients.get(0);
		
		System.out.println(patient.toJson());
	}
	
	@Test
	public void should_get_access_token() throws Exception
	{
		BreakGlassEndpoint endpoint = new BreakGlassEndpoint(env);
		
		String requestUrl = endpoint.getRequestUrl();
		System.out.println("requestUrl: " + requestUrl);
		
		HashMap<String, String> requestHeaders = endpoint.getRequestHeaders();
		System.out.println("requestHeaders: " + requestHeaders);
		
		BreakGlassRequestBody bg = new BreakGlassRequestBody();
		bg.record_id = patient.uid;
		bg.requestor = user.accessToken.token;
		bg.reason = "DIRECT_PAT_CARE";
		bg.other_reason = "";
		bg.access_data_type = "PATIENT_INFO";
		
		System.out.println(bg);
		
		String requestBody = bg.toJson();
		System.out.println("requestBody: " + requestBody);
		
		HttpRequestWithBody request = Unirest.post(requestUrl).headers(requestHeaders);
		HttpResponse<String> response = request.body(requestBody).asString();
		
		UnirestPrinter.printRequest(request);
		UnirestPrinter.printResponse(response);
		
		BreakGlassResponseBody result = new BreakGlassResponseBody().fromJson(response.getBody());
		assertThat(result.access_token).isNotNull();
		assertThat(result.token_type).isEqualTo("jwt");
		assertThat(result.expires_in).isNotNull();
	}
}
