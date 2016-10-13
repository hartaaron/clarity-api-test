package clarity.api.model.chart;

import clarity.api.endpoints.models.AuthenticationResponse;
import clarity.api.model.ClarityPatient;
import com.google.gson.Gson;
import org.json.JSONObject;

public class ClarityUserSession
{
	public String userId;
	public String accessToken;

	public static ClarityUserSession fromJSON(JSONObject json)
	{
		JSONObject data = (JSONObject) json.get("data");

		ClarityUserSession self = new ClarityUserSession();
		self.userId =  (String) data.get("user_id");
		self.accessToken = (String) data.get("x-access-token");

		return self;
	}

	public static ClarityUserSession fromGSON(String json)
	{
		Gson gson = new Gson();
		AuthenticationResponse response = gson.fromJson(json, AuthenticationResponse.class);

		System.out.println("++++++");
		System.out.println(response.toJson());
		System.out.println("++++++");

		ClarityUserSession self = new ClarityUserSession();
		self.userId = response.data.userId;
		self.accessToken = response.data.accessToken;

		return self;
	}
}
