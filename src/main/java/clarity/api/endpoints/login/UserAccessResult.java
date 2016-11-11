package clarity.api.endpoints.login;

import clarity.api.model.JsonEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAccessResult extends JsonEntity<UserAccessResult>
{
	@Expose
	@SerializedName("user_id")
	public String user_id;

	@Expose
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
