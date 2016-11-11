package clarity.api;

import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityPatientData;
import clarity.api.model.ClarityUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ClarityTestData
{
	Properties settings = new Properties() {{
		put("CLARITY_ENVIRONMENTS_JSON", "clarity.environments.json");
		put("CLARITY_USERS_JSON", "clarity.users.json");
		put("CLARITY_PATIENTS_JSON", "clarity.patients.json");
	}};
	
	Map<String, ClarityUser> users;
	Map<String, ClarityPatient> patients;
	Map<ClarityPatient, ClarityPatientData> patientData;
	
	public ClarityTestData() throws IOException
	{
		loadTestData();
	}
	
	public ClarityTestData(Properties settings) throws IOException
	{
		this.settings = settings;
		
		loadTestData();
	}
	
	public ClarityUser getUser(String email)
	{
		if (users.containsKey(email))
		{
			return users.get(email);
		}
	
		return null;
	}
	
	public void loadTestData() throws IOException
	{
		loadUsers();
		loadPatients();
	}
	
	public ClarityUser createUser(String email, String password)
	{
		ClarityUser user = new ClarityUser(email, password);
		
		//TODO: implement setup steps
		
		return user;
	}
	
	public String loadJsonFromResource(String path) throws IOException
	{
		InputStream in = this.getClass().getResourceAsStream("/" + path);
		String json = IOUtils.toString(in, Charset.defaultCharset());
//		System.out.println("json: " + json);
				
		return json;
	}
	
	public void loadUsers() throws IOException
	{
		loadUsers(settings.getProperty("CLARITY_USERS_JSON"));
	}
	
	public List<ClarityUser> loadUsers(String resourcePath) throws IOException
	{
		String json = loadJsonFromResource(resourcePath);

		Gson gson = new Gson();
		Type CLARITY_USERS = new TypeToken<List<ClarityUser>>(){}.getType();
		
		List<ClarityUser> users = gson.fromJson(json, CLARITY_USERS);
		
		this.users = new HashMap<String, ClarityUser>();
		for (ClarityUser user : users)
		{
			this.users.put(user.email, user);
		}
		return users;
	}
	
	
	public ClarityPatient getPatient(String lastName, String firstName, String birthDate)
	{
		ClarityPatient patient = null;
		
		if (patients.containsKey(lastName + firstName))
		{
			patient = patients.get(lastName + firstName);
			//TODO: check birthDate;
		}
		
		return patient;
	}
	
	public void loadPatients() throws IOException
	{
		loadPatients(settings.getProperty("CLARITY_PATIENTS_JSON"));
	}
	
	public List<ClarityPatient> loadPatients(String resourcePath) throws IOException
	{
		String json = loadJsonFromResource(resourcePath);
		
		Gson gson = new Gson();
		Type CLARITY_PATIENTS = new TypeToken<List<ClarityPatient>>(){}.getType();
		List<ClarityPatient> patients = gson.fromJson(json, CLARITY_PATIENTS);
		
		this.patients = new HashMap<>();
		for (ClarityPatient patient : patients) {
			// use "last_name,first_name" as key
			this.patients.put(patient.last_name + "," + patient.first_name, patient);
		}
		
		return patients;
	}
	
	public ClarityPatientData getPatientData(ClarityPatient patient)
	{
		return patientData.get(patient);
	}
	
	public void loadPatientData(ClarityPatient patient, String filename) throws FileNotFoundException
	{
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(filename));
		
		ClarityPatientData clarityPatientData = gson.fromJson(reader, ClarityPatientData.class);
		
		// load data for a single patient
		patientData.put(patient, clarityPatientData);
	}
	
	public void loadEnvironments(String resourcePath)
	{
		InputStream in = this.getClass().getResourceAsStream("/" + resourcePath);
		JsonReader reader = new JsonReader(new InputStreamReader(in));
		
		Gson gson = new Gson();
		
		Type CLARITY_PATIENT = new TypeToken<List<ClarityPatient>>(){}.getType();
		
		List<ClarityPatient> patients = gson.fromJson(reader, CLARITY_PATIENT);
		
		for (ClarityPatient patient : patients) {
			// use last_name + first_name as key
			this.patients.put(patient.last_name + patient.first_name, patient);
		}
	}
}
