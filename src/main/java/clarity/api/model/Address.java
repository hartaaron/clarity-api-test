package clarity.api.model;

public class Address
{
	public String street;
	public String city;
	public String state;
	public String zip;

	public AddressType type;

	public enum AddressType {
		PRIMARY, SECONDARY, EMERGENCY_CONTACT
	}
}
