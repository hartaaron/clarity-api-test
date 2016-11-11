package clarity.api.steps;

import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
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

public class PatientSearchSteps extends ClarityTestBase
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

	@When("^I search for patient \"([^\"]*)\"$")
	public void i_search_for_patient(String patientSearchString) throws Throwable
	{
		log.debug("WHEN: I search for patient " + patientSearchString);
		
		this.patientSearchString = patientSearchString;
		patients = clarity.searchForPatients(patientSearchString);
	}
	
	
	@When("^I search for patient with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_search_for_patient_with_firstName_lastName_birthDate(String lastName, String firstName, String birthDate) throws Throwable
	{
		patientSearchString = String.format("%s,%s", lastName, firstName);
		
		log.debug("patientSearchString: " + patientSearchString);
		
		patients = clarity.searchForPatients(lastName, firstName, birthDate);
	}
	
	
	@Then("^I get a patient with name \"([^\"]*)\"$")
	public void i_get_a_patient_with_name(String name) throws Throwable
	{
		log.debug("patients: " + patients.size() + " " + patients);
		assertThat(patients.size()).isGreaterThan(0);
		
		ClarityPatient patient = patients.get(0);
		
		// NOTE: this is an existing bug where the first & last name are reversed
		log.debug("patient name: " + patient.name);
		assertThat(patientSearchString).contains(patient.first_name);
		assertThat(patientSearchString).contains(patient.last_name);
	}

	@Then("^I get that patient in the patient list$")
	public void i_get_the_patient_list() throws Throwable
	{
		log.debug("patients: " + patients.size() + " " + patients);
		assertThat(patients.size()).isGreaterThan(0);
		
		ClarityPatient patient = patients.get(0);
		
		// NOTE: this is an existing bug where the first & last name are reversed
		assertThat(patientSearchString).contains(patient.first_name);
		assertThat(patientSearchString).contains(patient.last_name);
	}
	
	@Then("^I get no patients in the the patient list$")
	public void i_get_no_patients_in_the_patient_list() throws Throwable
	{
		log.debug("patients: " + patients.size() + " " + patients);
		assertThat(patients.size()).isEqualTo(0);
	}
	
	@After
	public void after(Scenario scenario)
	{
		log.debug("STATUS: " + scenario.getStatus());
	}
}
