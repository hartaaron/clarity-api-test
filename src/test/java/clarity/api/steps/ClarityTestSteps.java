package clarity.api.steps;

import clarity.api.unirest.UnirestClarityDriver;
import clarity.util.Logger;
import clarity.api.model.*;
import org.junit.Rule;

import java.util.Properties;

abstract class ClarityTestSteps
{
	Properties settings;
	Logger log;
	ClarityTestData testData;
	ClarityUser user;
	UnirestClarityDriver clarity;

	public ClarityTestSteps()
	{
		log = new Logger(this.getClass());
		clarity = new UnirestClarityDriver(ClarityEnvironment.TEST);
		loadTestData(null);
	}

	public void loadTestData(Properties settings)
	{
		this.settings = settings;
		testData = new ClarityTestData(settings).load();
	}

	public ClarityUser getUser(String email, String password)
	{
		ClarityUser user = testData.getUser(email, password);

		if (user == null) {
			user = new ClarityUser();
			user.email = email;
			user.password = password;
		}

		return user;
	}

	public ClarityPatient getPatient(String firstName, String lastName, String birthDate)
	{
		ClarityPatient patient = testData.getPatient(firstName, lastName, birthDate);

		if (patient == null) {
			patient = new ClarityPatient()
			{{
				firstName = firstName;
				lastName = lastName;
				birthDate = birthDate;
			}};
		}

		return patient;
	}

	public ClarityPatientData getPatientData(ClarityPatient patient)
	{
		ClarityPatientData patientData = testData.getPatientData(patient);

		if (patientData == null) {
			patientData = new ClarityPatientData();
		}

		return patientData;
	}
}
