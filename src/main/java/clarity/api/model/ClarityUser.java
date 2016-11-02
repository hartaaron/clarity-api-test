package clarity.api.model;

import clarity.api.endpoints.access.UserAccess;
import com.google.gson.annotations.Expose;

public class ClarityUser extends ClarityModelObject
{
	@Expose
	public String email;

	@Expose
	public String password;

	public transient String role;
	public transient UserAccess access;

	public ClarityUser()
	{
		super();
	}

	public ClarityUser(String email, String password)
	{
		super();
		this.email = email;
		this.password = password;
	}

	public Boolean hasAccessToken()
	{
		return  access != null && access.token != null && access.user_id != null;
	}
}
