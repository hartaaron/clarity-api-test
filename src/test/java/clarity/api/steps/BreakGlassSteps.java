package clarity.api.steps;

import clarity.api.endpoints.breakglass.BreakGlassReason;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import clarity.api.model.PatientAccess;
import clarity.util.ClarityLogger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BreakGlassSteps extends ClarityTestBase
{
	@Before
	public void setup(Scenario scenario) throws IOException
	{
		log = ClarityLogger.create(this);
		log.debug("SCENARIO: " + scenario.getName());
		clarity = setupEnvironment();
	}
	
	@Given("^I am logged in to Clarity$")
	public void i_am_logged_in_to_Clarity() throws Throwable
	{
		log.debug("GIVEN: I am logged in to clarity");
		
		ClarityUser intendedUser = getUser("clarity-external-testing@hart.com", "Cl@rity1");
		user = clarity.login(intendedUser);
		
		log.debug("user: " + user.toJson());
		assertThat(user.access.token).isNotNull();
	}
	
	@When("I break the glass for patient \"([^\"]*)\"$")
	public void i_break_the_glass_for_patient(String patientSearchString) throws Exception
	{
		this.patientSearchString = patientSearchString;
		System.out.println("====" + patientSearchString);
		System.out.println("====" + clarity);
		
		patients = clarity.searchForPatients(patientSearchString);
		
		assertThat(patients.size()).isGreaterThan(0);
		patient = patients.get(0);
		patient.access = clarity.breakGlass(patient, BreakGlassReason.DIRECT_PAT_CARE);
		
	}

	@Then("^I should get an access log token$")
	public void i_should_get_an_access_log_token() throws Throwable {
		assertThat(patient.access.token).isNotNull();
	}
	
	@After
	public void after(Scenario scenario)
	{
		log.debug("STATUS: " + scenario.getStatus());
	}
	
}
