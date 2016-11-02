package clarity.api;

import clarity.api.endpoints.breakglass.BreakGlassReason;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import clarity.api.model.PatientAccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ClarityDriver
{
	static final String APPLICATION_JSON = "application/json";

	static Map<String, String> JSON_HEADERS = new HashMap<String, String>()
	{{
		put("Content-Type", APPLICATION_JSON);
		put("Accept", APPLICATION_JSON);
	}};

//	void getManagementInfo();

	ClarityUser login(String username, String password) throws Exception;
//	void logOut();
//	void resetPassword(String username, String password);
//	void getUserInfo();
//	void getUserRoles(String user_id);

	// search for patient
	List<ClarityPatient> searchForPatients(String lastName, String firstName, String birthDate) throws Exception;
	PatientAccess breakGlass(String uid, BreakGlassReason reason, String otherReason) throws Exception;
	ClarityPatient getPatient(String patientName);
}
