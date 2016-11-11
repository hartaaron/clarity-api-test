package clarity.api.unirest;

import clarity.api.ClarityDriver;
import clarity.api.endpoints.ClarityResponseWithStatus;
import clarity.api.endpoints.access.AccessEndpoint;
import clarity.api.endpoints.access.UserAccess;
import clarity.api.endpoints.breakglass.BreakGlassEndpoint;
import clarity.api.endpoints.breakglass.BreakGlassReason;
import clarity.api.endpoints.breakglass.BreakGlassRequestBody;
import clarity.api.endpoints.patientsearch.PatientSearchEndpoint;
import clarity.api.endpoints.patientsearch.PatientSearchResult;
import clarity.api.model.*;
import clarity.util.ClarityLogger;
import clarity.api.util.GsonObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnirestClarityDriver implements ClarityDriver
{
	ClarityEnvironment env;
	
	ClarityUser user;
	ClarityPatient patient;
	List<ClarityPatient> patients;
	String patientSearch;
	
	UserAccess userAccess;
	PatientAccess patientAccess;
	
	GsonObjectMapper gson;
	Logger log;

	
	static final String APPLICATION_JSON = "application/json";

	static Map<String, String> headers = new HashMap<String, String>()
	{{
		put("Content-Type", APPLICATION_JSON);
		put("Accept", APPLICATION_JSON);
	}};
	
	public Object bgToken;
	
	public UnirestClarityDriver(ClarityEnvironment env)
	{
		this.env = env;
		
		gson = new GsonObjectMapper();
		Unirest.setObjectMapper(gson);

		log = ClarityLogger.create(this);
		log.debug("initialized with baseUrl: " + env.CLARITY_BASE_URL);
	}

	public String baseUrl()
	{
		return env.CLARITY_BASE_URL;
	}
	
	
	/** login **/
	
	public ClarityUser login(ClarityUser user) throws Exception
	{
		AccessEndpoint accessEndpoint = new AccessEndpoint(env);
		HttpResponse<String> response = accessEndpoint.send(user);

		String json = response.getBody();
		ClarityResponseWithStatus result = new ClarityResponseWithStatus().fromJson(json);

		if (! result.success)
		{
			log.debug("response: " + json);
			log.info("login request failed");
			return null;
		}
		
		user.access = new UserAccess().fromJson(result.data.toString());

		this.user = user;
		return user;
	}
	
	public ClarityUser login(String email, String password) throws Exception
	{
		log.debug("login...");
		ClarityUser user = new ClarityUser(email, password);
		return login(user);
	}
	
	
	
	
	/** searchForPatients **/
	
	public List<ClarityPatient> searchForPatients(ClarityPatient patient) throws Exception
	{
		if (user.access == null || user.access.isSet() == false)
		{
			throw new RuntimeException("user must first login to set x-acccess-token");
		}
		
		PatientSearchEndpoint endpoint = new PatientSearchEndpoint(env);
		endpoint.setAccessToken(userAccess.token);
		
		HttpResponse<String> response = endpoint.send(patient);
		String json = response.getBody();
		PatientSearchResult result = gson.fromJson(json, PatientSearchResult.class);
		
		List<ClarityPatient> patients = result.getPatients();
		return patients;
	}
	
	public List<ClarityPatient> searchForPatients(String lastName, String firstName, String birthDate) throws Exception
	{
		if (user.access == null || user.access.isSet() == false)
		{
			throw new RuntimeException("user must first login to set x-acccess-token");
		}
		
		PatientSearchEndpoint endpoint = new PatientSearchEndpoint(env);
		endpoint.setAccessToken(userAccess.token);
		
		HttpResponse<String> response = endpoint.send(lastName, firstName, birthDate);
		String json = response.getBody();
		PatientSearchResult result = gson.fromJson(json, PatientSearchResult.class);
		
		List<ClarityPatient> patients = result.getPatients();
		return patients;
	}
	
	public List<ClarityPatient> searchForPatients(String lastName, String firstName) throws Exception
	{
		return searchForPatients(lastName, firstName, null);
	}
	
	public List<ClarityPatient> searchForPatients(String patientSearchString) throws UnirestException
	{
		if (user.access == null || user.access.isSet() == false)
		{
			throw new RuntimeException("user must first login to set x-acccess-token");
		}
		
		PatientSearchEndpoint endpoint = new PatientSearchEndpoint(env);
		endpoint.setAccessToken(user.access.token);
		endpoint.setQueryString("size=100&q=" + endpoint.urlencode(patientSearchString));
		
		HttpResponse<String> response = endpoint.send();
		String json = response.getBody();
		PatientSearchResult result = gson.fromJson(json, PatientSearchResult.class);
		
		List<ClarityPatient> patients = result.getPatients();
		return patients;
	}
	
	
	/** breakGlass **/
	
	public PatientAccess breakGlass(String uid, BreakGlassReason reason, String other_reason) throws Exception
	{
		if (user.access == null || user.access.isSet() == false)
		{
			throw new RuntimeException("x_access_access token must be set");
		}
		
		BreakGlassEndpoint endpoint = new BreakGlassEndpoint(env);
		
		if (other_reason == null)
		{
			other_reason = "other reason";
		}
		
		BreakGlassRequestBody body = new BreakGlassRequestBody(uid, user.access.user_id, reason, other_reason);
		
		HttpResponse<String> response = endpoint.send(body);
		
		
		if (response.getStatus() != 200)
		{
			log.debug("response: " + response.getStatus() + " " + response.getStatusText());
			log.info("Break glass did not succeed");
			return null;
		}
		
		String json = response.getBody();
		
		PatientAccess access = gson.fromJson(json, PatientAccess.class);
		return access;
	}
	
	public PatientAccess breakGlass(ClarityPatient patient, BreakGlassReason reason, String other_reason) throws Exception
	{
		return breakGlass(patient.uid, reason, null);
	}
	
	public PatientAccess breakGlass(ClarityPatient patient, BreakGlassReason reason) throws Exception
	{
		return breakGlass(patient.uid, reason, null);
	}
	
	public PatientAccess breakGlass(ClarityPatient patient) throws Exception
	{
		return breakGlass(patient.uid, BreakGlassReason.DIRECT_PAT_CARE, null);
	}
	

	/** getPatient **/
	
	@Override
	public ClarityPatient getPatient(String patientName)
	{
		return null;
	}
	
	
}
