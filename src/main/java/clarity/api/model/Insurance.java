package clarity.api.model;

public class Insurance
{
	String Provider;
	String GroupId;
	String PlanId;
	String policyNumber;

	InsuranceType type;

	public enum InsuranceType {
		PRIMARY, SUPPLEMENTAL
	}
}
