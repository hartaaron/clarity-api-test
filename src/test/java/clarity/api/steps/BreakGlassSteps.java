package clarity.api.steps;

import clarity.api.endpoints.breakglass.BreakGlassReason;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import clarity.api.model.PatientAccess;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BreakGlassSteps extends ClarityTestBase
{
	String patientSearchString;
	ClarityEnvironment env;
//	UnirestClarityDriver clarity;
	ClarityUser user;
	ClarityPatient patient;
	PatientAccess access;
	
	@When("I break the glass for patient \"([^\"]*)\"$")
	public void i_break_the_glass_for_patient(String patientSearchString) throws Exception
	{
		this.patientSearchString = patientSearchString;
		List<ClarityPatient> patients = clarity.searchForPatients(patientSearchString);
		
		assertThat(patients.size()).isGreaterThan(0);
		patient = patients.get(0);
		
		access = clarity.breakGlass(patient, BreakGlassReason.DIRECT_PAT_CARE);
		
	}

	@Then("^I should get an access log token$")
	public void i_should_get_an_access_log_token() throws Throwable {
		assertThat(access.token).isNotNull();
	}
	
}
