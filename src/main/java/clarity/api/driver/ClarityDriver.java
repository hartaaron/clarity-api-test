package clarity.api.driver;

import clarity.api.ClarityEnvironment;
import clarity.api.ClarityTestData;
import clarity.api.ClarityTestSettings;
import clarity.api.endpoints.breakglass.BreakGlassReason;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import clarity.api.model.PatientAccess;

import java.io.IOException;
import java.util.List;

/**
 * ClarityDriver is really just an interface
 * but to use accessors (e.g. clarity.user ) I made it an abstract class
 */
public abstract class ClarityDriver
{
	public ClarityTestSettings settings;
	public ClarityEnvironment env;
	public ClarityTestData data;
	public ClarityUser user;
	public ClarityPatient patient;
	public String accessToken;
	public String bgToken;
	
	public abstract ClarityUser login(String email, String password) throws Exception;
	public abstract ClarityUser login(ClarityUser user) throws Exception;
	public abstract ClarityUser login() throws Exception;
	
	public abstract List<ClarityPatient> searchForPatient(String patientSearchString) throws Exception;
	public abstract List<ClarityPatient> searchForPatient(ClarityPatient patient) throws Exception;
	public abstract List<ClarityPatient> searchForPatient(String lastName, String firstName, String birthDate) throws Exception;
	public abstract List<ClarityPatient> searchForPatient(String lastName, String firstName) throws Exception;

	public abstract PatientAccess breakGlass(String uid, String referer, BreakGlassReason reason, String other_reason) throws Exception;
	public abstract PatientAccess breakGlass(String uid, BreakGlassReason reason, String other_reason) throws Exception;
	public abstract PatientAccess breakGlass(ClarityPatient patient, BreakGlassReason reason) throws Exception;
	
	public abstract ClarityPatient getPatient(String patientName) throws Exception;
	
	public abstract void loadTestData() throws IOException;
	
}
