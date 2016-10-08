package clarity.api.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ClarityTestData
{
	public Properties settings;

	public ClarityTestData(Properties settings)
	{
		this.settings = settings;
		this.load();
	}

	public ClarityTestData load() {
		//TODO: load from file or database

		// load users
		List<ClarityUser> users = new ArrayList<>();

		// load patients
		List<ClarityPatient> patients = new ArrayList<>();

		// load patient data
		HashMap<ClarityPatient, ClarityPatientData> patientData = new HashMap<>();

		return this;
	}

	public ClarityUser getUser(String username, String password)
	{
		//TODO: search users for match
		return null;
	}

	public ClarityPatient getPatient(String firstName, String lastName, String dateOfBirth)
	{
		//TODO: search patients for match
		return null;
	}

	public ClarityPatientData getPatientData(ClarityPatient patient)
	{
		//TODO: search for patient data that matches patient
		return null;
	}
}
