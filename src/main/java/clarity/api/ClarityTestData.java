package clarity.api;

import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityPatientData;
import clarity.api.model.ClarityUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.IOUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ClarityTestData
{
	public Properties settings;
	
	public static Properties DefaultSettings = new Properties() {{
		put("CLARITY_ENVIRONMENTS_JSON", "clarity.environments.json");
		put("CLARITY_USERS_JSON", "clarity.users.json");
		put("CLARITY_PATIENTS_JSON", "clarity.patients.json");
	}};
	
	Map<String, ClarityUser> users;
	Map<String, ClarityPatient> patients;
	Map<ClarityPatient, ClarityPatientData> patientData;
	
	public ClarityTestData() throws IOException
	{
		this.settings = DefaultSettings;
		loadTestData();
	}
	
	public ClarityTestData(Properties settings) throws IOException
	{
		this.settings = settings;
		loadTestData();
	}
	
	public void loadTestData() throws IOException
	{
		loadUsers();
		loadPatients();
	}
	
	public void loadUsers() throws IOException
	{
		users = LoadUsers(settings.getProperty("CLARITY_USERS_JSON"));
	}
	
	public void loadPatients() throws IOException
	{
		patients = LoadPatients(settings.getProperty("CLARITY_PATIENTS_JSON"));
	}
	
	public ClarityUser getUser(String key)
	{
		return users.get(key);
	}
	
	public ClarityPatient getPatient(String key)
	{
		return patients.get(key);
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

	public ClarityUser createUser(String email, String password)
	{
		ClarityUser user = new ClarityUser(email, password);
		
		//TODO: implement setup steps
		
		return user;
	}
	
	// STATIC ACCESS
	
	public static ClarityTestData LoadTestData(Properties settings) throws IOException
	{
		return new ClarityTestData(settings);
	}
	
	public static HashMap<String, ClarityEnvironment> LoadEnvironments(String resourcePath)
	{
		//TODO: implement LoadEnvironments
		throw new NotImplementedException();
	}
	
	public static HashMap<String, ClarityUser> LoadUsers(String resourcePath) throws IOException
	{
		String json = LoadJsonFromResource(resourcePath);
		
		Gson gson = new Gson();
		Type CLARITY_USERS = new TypeToken<HashMap<String, ClarityUser>>(){}.getType();
		
		HashMap<String, ClarityUser> users = gson.fromJson(json, CLARITY_USERS);
		
		users = new HashMap<String, ClarityUser>();
		
		for (Map.Entry <String, ClarityUser> entry : users.entrySet())
		{
			users.put(entry.getKey(), entry.getValue());
		}
		return users;
	}
	
	
	public static HashMap<String, ClarityPatient> LoadPatients(String resourcePath) throws IOException
	{
		String json = LoadJsonFromResource(resourcePath);
		
		Gson gson = new Gson();
		Type CLARITY_PATIENTS = new TypeToken<List<ClarityPatient>>(){}.getType();
		List<ClarityPatient> patientList = gson.fromJson(json, CLARITY_PATIENTS);
		
		HashMap<String, ClarityPatient> patients = new HashMap<>();
		for (ClarityPatient patient : patientList) {
			// use "last_name,first_name" as key
			patients.put(patient.last_name + "," + patient.first_name, patient);
		}
		
		return patients;
	}
	
	public static String LoadJsonFromResource(String path) throws IOException
	{
		InputStream in = ClarityTestData.class.getResourceAsStream("/" + path);
		String json = IOUtils.toString(in, Charset.defaultCharset());
		
		return json;
	}
}
