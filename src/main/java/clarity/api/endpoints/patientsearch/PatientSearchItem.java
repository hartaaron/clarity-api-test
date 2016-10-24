package clarity.api.endpoints.patientsearch;

public class PatientSearchItem
{
	public String uid;
	public String name;

	public String first_name;

	public String last_name;
	public String dob;
	public String sex;
	public String ssn;
	public String marital_status;
	public String marital_status_displayable;

	public Address address;

	public class Address {
		public String address1;
		public String city;
		public String state;
		public String zip;
	}
}
