package clarity.api.endpoints.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse
{
	public Boolean success;
	public Integer code;

	@SerializedName("data")
	public Data data;

	public class Data {
		@SerializedName("user_id")
		public String userId;

		@SerializedName("x-access-token")
		public String accessToken;
	}

	public String toJson()
	{
		return new Gson().toJson(this);
	}
}
