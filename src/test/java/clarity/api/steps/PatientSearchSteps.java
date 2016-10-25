package clarity.api.steps;

import clarity.api.endpoints.patientsearch.PatientSearchEndpoint;
import clarity.api.endpoints.patientsearch.PatientSearchItem;
import clarity.api.model.ClarityEnvironment;
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
	String patientSearchString;
	String patientFirstName;
	String patientLastName;
	String patientDOB;
	
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
		
		this.patientSearchString = patientSearchString;
		patients = clarity.patientSearch(patientSearchString);
	}
	
	
	@When("^I search for patient with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_search_for_patient_with_firstName_lastName_birthDate(String lastName, String firstName, String birthDate) throws Throwable
	{
		
		System.out.println("=========================");
		System.out.println("lastName: " + lastName);
		System.out.println("firstName: " + firstName);
		System.out.println("birthDate: " + birthDate);
		System.out.println("=========================");
		
		this.patientFirstName = firstName;
		this.patientLastName = lastName;
		this.patientDOB = birthDate;
		
		patientSearchString = String.format("%s,%s", lastName, firstName);
		
		log.write("patientSearchString: " + patientSearchString);
		
		patients = clarity.patientSearch(lastName, firstName, birthDate);
		
	}
	
	
	@Then("^I get a patient with name \"([^\"]*)\"$")
	public void i_get_a_patient_with_name(String name) throws Throwable
	{
		log.write("THEN: I get a patient with name " + name);
		
		log.write("patients: " + patients.size() + " " + patients);
		assertThat(patients.size()).isGreaterThan(0);
		
		PatientSearchItem patient = patients.get(0);
		
		// NOTE: this is an existing bug where the first & last name are reversed
		log.write("patient name: " + patient.name);
		assertThat(patient.name).isEqualTo(patientSearchString);
	}

	@Then("^I get that patient in the patient list$")
	public void i_get_the_patient_list() throws Throwable
	{
		log.write("THEN: I get the patient list");
		
		log.write("patients: " + patients.size() + " " + patients);
		assertThat(patients.size()).isGreaterThan(0);
		
		PatientSearchItem patient = patients.get(0);
		
		assertThat(patientSearchString).contains(patient.first_name);
		assertThat(patientSearchString).contains(patient.last_name);
	}
	
	@Then("^I get no patients in the the patient list$")
	public void i_get_no_patients_in_the_patient_list() throws Throwable
	{
		log.write("THEN: I get the patient list");
		
		log.write("patients: " + patients.size() + " " + patients);
		assertThat(patients.size()).isEqualTo(0);
		
	}
}
