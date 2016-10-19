package clarity.api.endpoints.access;

import clarity.api.model.JsonEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessResult extends JsonEntity
{
	@Expose
	public Boolean success;

	@Expose
	public Integer code;

	@Expose
	@SerializedName("data")
	public Data data;

	//TODO: need to handle where data is a String (i.e. "401 Unauthorized")

	public class Data {
		@Expose
		@SerializedName("user_id")
		public String user_id;

		@Expose
		@SerializedName("x-access-token")
		public String x_access_token;
	}
}
