package clarity.api.endpoints.patientsearch;

import clarity.ClarityApiTestCase;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import clarity.api.unirest.UnirestClarityDriver;
import com.mashape.unirest.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientSearchEndpoint_Test extends ClarityApiTestCase
{
	PatientSearchEndpoint endpoint;

	UnirestClarityDriver driver;
	ClarityUser user;

	@Before
	public void setUp() throws Exception
	{
		env = ClarityEnvironment.TEST;
	}
	
	@Test
	public void should_generate_query_string()
	{
		endpoint = new PatientSearchEndpoint(env);

		endpoint.setPatientDOB("10/11/2012");
		endpoint.setPatientFirstName("Firstname");
		endpoint.setPatientLastName("Last");

		String queryString = endpoint.getQueryString();

		System.out.println(queryString);
		
		assertThat(queryString).isEqualTo("q=Last,Firstname,10%2F11%2F2012");
	}
	
	
	@Test
	public void should_get_response() throws Exception
	{
		endpoint = new PatientSearchEndpoint(env);
		
		clarity = new UnirestClarityDriver(env);
		user = clarity.login(validUser);
		
		endpoint.setAccessToken(user.x_access_token);
//		endpoint.setQueryString("size=100&q=ZZITESTSJM");
		
		HttpResponse<String> response = endpoint.send("size=10&q=ZZITESTSJM,HARTONE");
		
		System.out.println("STATUS: " + response.getStatus());
		assertThat(response.getStatus()).isEqualTo(200);
		
		System.out.println("BODY: " + response.getBody());
		
		PatientSearchResponse r = gson.fromJson(response.getBody(), PatientSearchResponse.class);
		
		Double totalElements = (Double) r.page.get("totalElements");
		System.out.println("totalElements: " + totalElements);
		assertThat(totalElements).isEqualTo(1);
		
		assertThat(r.items.size()).isEqualTo(1);
	}
}
