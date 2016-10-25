package clarity.api.steps;

import clarity.api.model.ClarityPatient;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class PatientSearchSteps extends ClarityTestSteps
{
	List<ClarityPatient> patients;
	
	@Given("^I am logged in to Clarity$")
	public void i_am_logged_in_to_Clarity() throws Throwable
	{
		log.write("GIVEN: I am logged in to clarity");
		
		user = getUser("clarity-external-testing@hart.com", "Cl@rity1");
		clarity.login(user);
		
		log.write("user: " + user.toJson());
	}

	@When("^I search for patient \"([^\"]*)\"$")
	public void i_search_for_patient(String patientSearchString) throws Throwable
	{
		log.write("WHEN: I search for patient " + patientSearchString);
		
		patients = clarity.search(patientSearchString);
	}

	@Then("^I get the patient list$")
	public void i_get_the_patient_list() throws Throwable
	{
		System.out.println("TODO: i_get_the_patient_list");
		throw new PendingException();
	}
}
