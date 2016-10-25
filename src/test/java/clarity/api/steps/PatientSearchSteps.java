package clarity.api.steps;

import clarity.api.endpoints.patientsearch.PatientSearchItem;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientSearchSteps extends ClarityTestSteps
{
	List<PatientSearchItem> patients;
	
	@Given("^I am logged in to Clarity$")
	public void i_am_logged_in_to_Clarity() throws Throwable
	{
		log.write("GIVEN: I am logged in to clarity");
		
		ClarityUser intendedUser = getUser("clarity-external-testing@hart.com", "Cl@rity1");
		user = clarity.login(intendedUser);

		log.write("user: " + user.toJson());
		assertThat(user.x_access_token).isNotNull();
	}

	@When("^I search for patient \"([^\"]*)\"$")
	public void i_search_for_patient(String patientSearchString) throws Throwable
	{
		log.write("WHEN: I search for patient " + patientSearchString);
		
		patients = clarity.patientSearch(patientSearchString);
	}

	@Then("^I get the patient list$")
	public void i_get_the_patient_list() throws Throwable
	{
		log.write("THEN: I get the patient list");
		
		log.write("patients: " + patients.size() + " " + patients);
		
		assertThat(patients.size()).isGreaterThan(0);
	}
}
