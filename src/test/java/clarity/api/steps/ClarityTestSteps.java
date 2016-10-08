package clarity.api.steps;

import api.endpoints.ClarityApi;
import api.util.Logger;
import clarity.api.ClarityApiDriver.ClarityApiDriver;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityPatientData;
import clarity.api.model.ClarityTestData;
import clarity.api.model.ClarityUser;
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

		clarity = new ClarityApiDriver();

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
			user = new ClarityUser()
			{{
				username = username;
				password = password;
			}};
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
