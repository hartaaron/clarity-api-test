package clarity.api.driver;

import clarity.api.ClarityEnvironment;
import clarity.api.ClarityTestData;
import clarity.api.ClarityTestSettings;
import clarity.api.endpoints.breakglass.BreakGlassEndpoint;
import clarity.api.endpoints.breakglass.BreakGlassReason;
import clarity.api.endpoints.breakglass.BreakGlassRequestBody;
import clarity.api.endpoints.login.LoginEndpoint;
import clarity.api.endpoints.login.UserAccessCredentials;
import clarity.api.endpoints.patientsearch.PatientSearchEndpoint;
import clarity.api.endpoints.patientsearch.PatientSearchResult;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import clarity.api.model.PatientAccess;
import clarity.api.util.GsonObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class UnirestClarityDriver extends ClarityDriver
{
	public ClarityTestSettings settings;
	
	GsonObjectMapper gson;
	
	public ClarityUser user;
	protected Logger log = LogManager.getLogger();
	
	/**
	 * Use static builder to get an instance with environment set
	 * instead of with constructor parameter
	 * so we can have only a constructor with no arguments
	 * to make PicoContainer happy.
	 *
	 * @param env
	 * @return
	 */
	public static UnirestClarityDriver forEnvironment(ClarityEnvironment env)
	{
		UnirestClarityDriver instance = new UnirestClarityDriver();
		instance.init(env);
		
		return instance;
	}
	
	/**
	 * Default constructor with no args needed for PicoContainer
	 */
	public UnirestClarityDriver()
	{
		init(null);
	}
	
	/**
	 * Initialize with environment.
	 *
	 * If environment is not set, attempt to get it from props.
	 *
	 * @param env
	 */
	public void init(ClarityEnvironment env)
	{
		if (env == null)
			try
			{
				settings = ClarityTestSettings.LoadSettings();
				env = settings.getClarityEnvironment();
				loadTestData();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		
		this.env = env;
		
	}
	
	public ClarityEnvironment getEnv()
	{
		return env;
	}
	
	public ClarityUser getUser()
	{
		return user;
	}
	
	
	public void loadTestData() throws IOException
	{
		data = ClarityTestData.LoadTestData(settings.properties);
	}
	
	
	/***** Login *****/
	
	/**
	 * Login to Clarity by calling the ClarityAccess API endpoint
	 *
	 * Returns the clarity user with x-access-token and user_id set
	 *
	 * @param email
	 * @param password
	 * @return
	 */
	public ClarityUser login(String email, String password) throws Exception
	{
		user = new ClarityUser(email, password);
		return login();
	}
	
	/**
	 * Login to Clarity as user
	 *
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ClarityUser login(ClarityUser user) throws Exception
	{
		this.user = user;
		return login();
	}
	
	/**
	 * Login to Clarity with existing user props
	 * @return
	 * @throws Exception
	 */
	public ClarityUser login() throws Exception
	{
		log.debug("login...");
		
		LoginEndpoint endpoint = new LoginEndpoint(env);
		UserAccessCredentials credentials = new UserAccessCredentials(user.email, user.password);
		
		user.accessToken = endpoint.send(credentials);
		
		log.debug("got accessToken: " + user.accessToken);
		
		return user;
	}
	
	
	
	/***** searchForPatient ******/
	
	
	
	public List<ClarityPatient> searchForPatient(String patientSearchString) throws UnirestException
	{
		if (user.accessToken == null || user.accessToken.isSet() == false)
		{
			throw new RuntimeException("user must first login to set x-acccess-token");
		}
		
		PatientSearchEndpoint endpoint = new PatientSearchEndpoint(env);
		endpoint.setAccessToken(user.accessToken.token);
		endpoint.setQueryString("size=100&q=" + endpoint.urlencode(patientSearchString));
		
		HttpResponse<String> response = endpoint.send();
		String json = response.getBody();
		PatientSearchResult result = gson.fromJson(json, PatientSearchResult.class);
		
		List<ClarityPatient> patients = result.getPatients();
		return patients;
	}
	
	public List<ClarityPatient> searchForPatient(ClarityPatient patient) throws Exception
	{
		if (user.accessToken == null || user.accessToken.isSet() == false)
		{
			throw new RuntimeException("user must first login to set x-acccess-token");
		}
		
		PatientSearchEndpoint endpoint = new PatientSearchEndpoint(env);
		endpoint.setAccessToken(user.accessToken.token);
		
		HttpResponse<String> response = endpoint.send(patient);
		String json = response.getBody();
		PatientSearchResult result = gson.fromJson(json, PatientSearchResult.class);
		
		List<ClarityPatient> patients = result.getPatients();
		return patients;
	}
	
	public List<ClarityPatient> searchForPatient(String lastName, String firstName, String birthDate) throws Exception
	{
		if (user.accessToken == null || user.accessToken.isSet() == false)
		{
			throw new RuntimeException("user must first login to set x-acccess-token");
		}
		
		PatientSearchEndpoint endpoint = new PatientSearchEndpoint(env);
		endpoint.setAccessToken(user.accessToken.token);
		
		HttpResponse<String> response = endpoint.send(lastName, firstName, birthDate);
		String json = response.getBody();
		PatientSearchResult result = gson.fromJson(json, PatientSearchResult.class);
		
		List<ClarityPatient> patients = result.getPatients();
		return patients;
	}
	
	public List<ClarityPatient> searchForPatient(String lastName, String firstName) throws Exception
	{
		return searchForPatient(lastName, firstName, null);
	}
	
	
	/***** breakGlass *****/
	
	public PatientAccess breakGlass(String record_id, String referer, BreakGlassReason reason, String other_reason) throws Exception
	{
		if (user.accessToken == null || user.accessToken.isSet() == false)
		{
			throw new RuntimeException("x_access_access token must be set");
		}
		
		if (referer == null)
		{
			referer = user.accessToken.user_id;
		}
		
		if (reason == null) {
			reason = BreakGlassReason.DIRECT_PAT_CARE;
		}
		
		if (other_reason == null && reason.other_reason_required())
		{
			other_reason = "other reason";
		}
		
		BreakGlassEndpoint endpoint = new BreakGlassEndpoint(env);
		BreakGlassRequestBody body = new BreakGlassRequestBody(record_id, referer, reason, other_reason);
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
	
	public PatientAccess breakGlass(String record_id, BreakGlassReason reason, String other_reason) throws Exception
	{
		return breakGlass(record_id, null, reason, other_reason);
	}
	
	
	public PatientAccess breakGlass(ClarityPatient patient, ClarityUser user, BreakGlassReason reason, String other_reason) throws Exception
	{
		return breakGlass(patient.uid, user.accessToken.user_id, reason, null);
	}
	
	public PatientAccess breakGlass(ClarityPatient patient, BreakGlassReason reason) throws Exception
	{
		return breakGlass(patient.uid, null, reason, null);
	}
	
	public PatientAccess breakGlass(ClarityPatient patient) throws Exception
	{
		return breakGlass(patient.uid, BreakGlassReason.DIRECT_PAT_CARE, null);
	}
	
	public PatientAccess breakGlass(ClarityPatient patient, ClarityUser user) throws Exception
	{
		return breakGlass(patient.uid, user.accessToken.user_id, BreakGlassReason.DIRECT_PAT_CARE, null);
	}
	
	
	
	/***** getPatient *****/
	
	public ClarityPatient getPatient(String patientName) throws Exception
	{
		return null;
	}
	
}
