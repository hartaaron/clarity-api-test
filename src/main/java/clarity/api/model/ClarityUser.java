package clarity.api.model;

import com.google.gson.annotations.Expose;

public class ClarityUser extends ClarityModelObject
{
	@Expose
	public String username;

	@Expose
	public String password;

	public String toString()
	{
		return String.format("ClarityUser: { username: %s, password; %s }", username, password);
	}
}
