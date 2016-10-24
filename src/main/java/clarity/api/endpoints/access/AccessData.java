package clarity.api.endpoints.access;

import clarity.api.endpoints.JsonEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessData extends JsonEntity<AccessData>
{
	@SerializedName("user_id")
	public String user_id;

	@SerializedName("x-access-token")
	public String x_access_token;
}
