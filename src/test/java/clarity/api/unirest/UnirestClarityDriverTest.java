package clarity.api.unirest;

import clarity.ClarityApiTestCase;
import clarity.api.endpoints.patientsearch.PatientSearchItem;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
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
		clarity = new UnirestClarityDriver(env);
	}

	@Test
	public void should_login() throws Exception
	{
		clarity.login(validUser);

		check.assertThat(clarity.user).isNotNull();

		String user_id = clarity.user.user_id;
		String x_access_token = clarity.user.x_access_token;

		log.write("user_id: " + user_id);
		log.write("x_access_token: " + x_access_token);

		assertThat(user_id).isNotEmpty();
		assertThat(x_access_token).isNotEmpty();
	}
	
	@Test
	public void should_search_for_patients() throws Exception
	{
		ClarityUser user = clarity.login(validUser);
		clarity.setAccessToken(user.x_access_token);
		
		List<PatientSearchItem> patients = clarity.patientSearch("ZZITESTSJM,HARTONE");
		
		assertThat(patients.size()).isEqualTo(1);
		PatientSearchItem patient = patients.get(0);
		
		assertThat(patient.first_name).isEqualTo("HARTONE");
		assertThat(patient.last_name).isEqualTo("ZZITESTSJM");
		
		
		
	}
}
