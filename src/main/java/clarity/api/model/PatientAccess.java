package clarity.api.model;

import com.google.gson.annotations.SerializedName;

public class PatientAccess
{
	@SerializedName("access_token")
	public String token;
	
	public String token_type;

	public int expires_in;
}
