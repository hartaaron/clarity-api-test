package clarity.api.endpoints.patientsearch;

import clarity.ClarityApiTestCase;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import clarity.api.unirest.UnirestClarityDriver;
import org.junit.Before;
import org.junit.Test;

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
	public void should_get_response() throws Exception
	{
		endpoint = new PatientSearchEndpoint(env);

		clarity = new UnirestClarityDriver(env);
		user = clarity.login(validUser);

		endpoint.setAccessToken(user.x_access_token);
		endpoint.setQueryString("size=100&q=ZZITESTSJM");
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
	}
}
