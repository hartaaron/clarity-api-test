package clarity.api;

import java.util.HashMap;
import java.util.Map;

public interface ClarityDriver
{
	static final String APPLICATION_JSON = "application/json";

	static Map<String, String> JSON_HEADERS = new HashMap<String, String>()
	{{
		put("Content-Type", APPLICATION_JSON);
		put("Accept", APPLICATION_JSON);
	}};

	// management
	void getManagementInfo();

	// user
	void logIn(String username, String password);
	void logOut();
	void resetPassword(String username, String password);
	void getUserSettings();
	void getUserRoles(String user_id);

	// search for patient
	void searchForPatients(String lastName, String firstName, String birthDate);
	void getPatient(String patientName);
	void breakGlass(String user_id, String patient_id, String reason, String otherReason);
}
