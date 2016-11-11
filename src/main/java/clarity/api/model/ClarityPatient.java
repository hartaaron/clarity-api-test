package clarity.api.model;


import java.util.Map;

public class ClarityPatient extends JsonEntity<ClarityPatient>
{
	public ClarityPatient() {}
	public ClarityPatient(String lastName, String firstName, String dob)
	{
		this.first_name = firstName;
		this.last_name = lastName;
		this.dob = dob;
	}
	
	public String uid;
	public String name;
	public String first_name;
	public String last_name;
	public String dob;
	public String sex;
	public String ssn;
	public String phone_number;
	public String home_phone;
	public String business_phone;
	public String preferred_language;
	public String marital_status;
	public String marital_status_displayable;
	public Boolean hie_opt_out;
	
	public Map<String, Object> _links;
	
	public Address address;

	public transient PatientAccess access;
}
