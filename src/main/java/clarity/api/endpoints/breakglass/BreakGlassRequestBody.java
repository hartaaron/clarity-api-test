package clarity.api.endpoints.breakglass;

import clarity.api.model.JsonEntity;

public class BreakGlassRequestBody extends JsonEntity<BreakGlassRequestBody>
{
	
	public String record_id;
	public String requestor;
	public String reason;
	public String other_reason;
	public String access_data_type;

	public BreakGlassRequestBody()
	{}
	
	public BreakGlassRequestBody(String record_id, String requestor, BreakGlassReason reason, String other_reason)
	{
		this.record_id = record_id;
		this.requestor = requestor;
		
		if (reason == null)
		{
			reason = BreakGlassReason.DIRECT_PAT_CARE;
		}
		
		this.reason = reason.toString();
		
		if (other_reason == null)
		{
			other_reason = "other reason";
		}
		
		if (reason.other_reason_required()) {
			this.other_reason = other_reason;
		}
		
		this.access_data_type = "PATIENT_INFO";
	}
}
