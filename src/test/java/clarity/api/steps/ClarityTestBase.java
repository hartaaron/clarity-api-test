package clarity.api.steps;

import clarity.api.ClarityDriver;
import clarity.api.ClarityTestData;
import clarity.api.unirest.UnirestClarityDriver;
import clarity.util.ClarityLogger;
import clarity.api.model.*;
import cucumber.api.Scenario;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public abstract class ClarityTestBase
{
	protected Scenario scenario;
	protected Logger log;
	protected Properties settings;
	
	protected ClarityEnvironment env;
	protected UnirestClarityDriver clarity;
	protected ClarityTestData data;
	
	protected ClarityUser user;
	protected ClarityPatient patient;
	protected List<ClarityPatient> patients;
	protected String patientSearchString;
	
	
	public Properties loadSettings() throws IOException
	{
		// get settings from properties file
		InputStream stream = this.getClass().getResourceAsStream("/test.properties");
		settings = new Properties();
		settings.load(stream);
		
		// get settings from system properties
		String CLARITY_ENV = System.getProperty("CLARITY_ENV");
		String CLARITY_USERS_JSON = System.getProperty("CLARITY_USERS_JSON");
		String CLARITY_PATIENTS_JSON = System.getProperty("CLARITY_PATIENTS_JSON");
		
		// system settings overwrite file settings
		if (CLARITY_ENV != null) { settings.setProperty("CLARITY_ENV", CLARITY_ENV); }
		if (CLARITY_USERS_JSON != null) { settings.setProperty("CLARITY_USERS_JSON", CLARITY_USERS_JSON); }
		if (CLARITY_PATIENTS_JSON != null) { settings.setProperty("CLARITY_PATIENTS", CLARITY_PATIENTS_JSON); }
		
		log.debug("settings: " + settings);

		return settings;
	}
	
	
	public void loadTestData(Properties settings) throws IOException
	{
		data = new ClarityTestData();
		data.loadEnvironments(settings.getProperty("clarity.environments.json"));
		data.loadUsers(settings.getProperty("clarity.users.json"));
		data.loadPatients(settings.getProperty("clarity.patients.json"));
	}
	
	public UnirestClarityDriver setupEnvironment() throws IOException
	{
		log.debug("setupEnvironment");
		
		settings = loadSettings();
		env = getEnv();
		clarity = new UnirestClarityDriver(env);
		data = new ClarityTestData(settings);
		
		return clarity;
	}
	
	public ClarityEnvironment getEnv()
	{
		String CLARITY_ENV = settings.getProperty("CLARITY_ENV");
		if (CLARITY_ENV == null) { throw new RuntimeException("must set property CLARITY_ENV "); }
		
		env = ClarityEnvironment.get(CLARITY_ENV);
		log.info("CLARITY_ENV: " + env.name);
		
		return env;
	}
	
	public ClarityUser getUser(String email, String password)
	{
		log.debug("==============in getUser");
		log.debug("data: " + data);
		
		ClarityUser user = data.getUser(email);
		
		log.debug("user: " + user);
		
		if (user == null) {
			user = new ClarityUser();
			user.email = email;
			user.password = password;
		}

		return user;
	}

	public ClarityPatient getPatient(String firstName, String lastName, String birthDate)
	{
		ClarityPatient patient = data.getPatient(firstName, lastName, birthDate);

		if (patient == null) {
			patient = new ClarityPatient(lastName, firstName, birthDate);
		}

		return patient;
	}

	public ClarityPatientData getPatientData(ClarityPatient patient)
	{
		ClarityPatientData patientData = data.getPatientData(patient);

		if (patientData == null) {
			patientData = new ClarityPatientData();
		}

		return patientData;
	}
}
