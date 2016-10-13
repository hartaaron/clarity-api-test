package clarity.api.steps;

import clarity.api.api.util.Logger;
import clarity.api.ClarityApiDriver;
import clarity.api.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Properties;

abstract class ClarityTestSteps
{
	Properties settings;
	Gson gson;
	GsonBuilder gsonBuilder;
	Logger log;
	ClarityTestData testData;
	ClarityApiDriver clarity;
	ClarityUser user;

	public ClarityTestSteps()
	{
		gson = new Gson();
		gsonBuilder = new GsonBuilder();

		log = new Logger();

		clarity = new ClarityApiDriver(ClarityEnvironment.TEST);

		loadTestData(null);
	}

	public void loadTestData(Properties settings)
	{
		this.settings = settings;
		testData = new ClarityTestData(settings).load();
	}

	public ClarityUser getUser(String username, String password)
	{
		ClarityUser user = testData.getUser(username, password);

		if (user == null) {
			user = new ClarityUser();
			user.password = username;
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
