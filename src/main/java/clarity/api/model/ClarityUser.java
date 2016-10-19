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


	public ClarityUser() {}
	public ClarityUser(String email, String password) { this.email = email; this.password = password; }

	public String toString()
	{
		return String.format("ClarityUser: { email: %s, password; %s }", email, password);
	}
}
