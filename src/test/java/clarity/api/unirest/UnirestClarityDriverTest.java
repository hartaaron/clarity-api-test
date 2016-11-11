package clarity.api.unirest;

import clarity.ClarityApiTestCase;
import clarity.api.driver.UnirestClarityDriver;
import clarity.api.endpoints.breakglass.BreakGlassReason;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import clarity.api.model.PatientAccess;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UnirestClarityDriverTest extends ClarityApiTestCase
{
	UnirestClarityDriver clarity;

	@Before
	public void setUp()
	{
		clarity = new UnirestClarityDriver();
	}

	@Test
	public void should_login() throws Exception
	{
		clarity.login(validUser);

		check.assertThat(clarity.user).isNotNull();

		String user_id = clarity.user.accessToken.user_id;
		String x_access_token = clarity.user.accessToken.token;

		log.debug("user_id: " + user_id);
		log.debug("x_access_token: " + x_access_token);

		assertThat(user_id).isNotEmpty();
		assertThat(x_access_token).isNotEmpty();
	}
	
	@Test
	public void should_search_for_patient() throws Exception
	{
		ClarityUser user = clarity.login(validUser);
		
		List<ClarityPatient> patients = clarity.searchForPatient("ZZITESTSJM,HARTONE");
		
		assertThat(patients.size()).isEqualTo(1);
		ClarityPatient patient = patients.get(0);
		
		assertThat(patient.first_name).isEqualTo("HARTONE");
		assertThat(patient.last_name).isEqualTo("ZZITESTSJM");
	}
	
	@Test
	public void should_break_glass() throws Exception
	{
		ClarityUser user = clarity.login(validUser);
		
		List<ClarityPatient> patients = clarity.searchForPatient("ZZITESTSJM,HARTONE");
		assertThat(patients.size()).isEqualTo(1);
		ClarityPatient patient = patients.get(0);
		
		PatientAccess access = clarity.breakGlass(patient, BreakGlassReason.DIRECT_PAT_CARE);
		
		System.out.println("bgToken: " + access.token);
		assertThat(access.token).isNotEmpty();
	}
}
