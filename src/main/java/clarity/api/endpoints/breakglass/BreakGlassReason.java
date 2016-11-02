package clarity.api.endpoints.breakglass;

public enum BreakGlassReason
{
	DIRECT_PAT_CARE("DIRECT_PAT_CARE"),
	AUDITING("AUDITING"),
	INVESTIGATION_COMPLIANCE("INVESTIGATION_COMPLIANCE"),
	RECORD_REVIEW("RECORD_REVIEW"),
	OTHER("OTHER_REASON");
	
	private String name;
	
	private BreakGlassReason(String s)
	{
		this.name = s;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public boolean other_reason_required() {
		switch(this)
		{
			case DIRECT_PAT_CARE:
				return true;
			default:
				return false;
		}
	}
}
