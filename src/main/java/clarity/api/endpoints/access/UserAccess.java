package clarity.api.endpoints.access;

import clarity.api.endpoints.JsonEntity;
import com.google.gson.annotations.SerializedName;

public class UserAccess extends JsonEntity<UserAccess>
{
	@SerializedName("user_id")
	public String user_id;

	@SerializedName("x-access-token")
	public String token;
	
	public boolean hasToken()
	{
		return (token != null);
	}
	
	public boolean hasId()
	{
		return (user_id != null);
	}
	
	public boolean isSet()
	{
		return (hasToken() && hasId());
	}
}
