package clarity.api.model;

public class Address extends JsonEntity<Address>
{
	public String address1;
	public String city;
	public String state;
	public String zip;

	public AddressType type;

	public enum AddressType {
		PRIMARY, SECONDARY, EMERGENCY_CONTACT
	}
}
