package clarity.api.model;

import com.google.gson.annotations.Expose;

public class ClarityUser extends ClarityModelObject
{
	@Expose
	public String email;

	@Expose
	public String password;

	public String user_id;
	public String x_access_token;

	public String getUserId() { return user_id; }
	public void setUserId(String userId) { this.user_id = userId; }

	public String getXAccessToken() { return x_access_token; }
	public void setAccessToken(String x_access_token) { this.x_access_token = x_access_token; }

	public String xAccessToken;

	public String toString()
	{
		return String.format("ClarityUser: { email: %s, password; %s }", email, password);
	}
}
