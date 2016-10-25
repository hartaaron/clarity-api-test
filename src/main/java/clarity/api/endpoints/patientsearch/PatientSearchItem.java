package clarity.api.endpoints.patientsearch;

import java.util.List;
import java.util.Map;

public class PatientSearchItem
{
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
	
	public List<Map<String, String>> _links;
	
	public Address address;

	public class Address {
		public String address1;
		public String city;
		public String state;
		public String zip;
	}
}
