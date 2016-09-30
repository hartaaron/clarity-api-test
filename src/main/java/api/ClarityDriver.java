package api;

public interface ClarityDriver
{
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
