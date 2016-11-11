package clarity.api.model;

import clarity.api.endpoints.login.UserAccessResult;

public class ClarityUser extends JsonEntity<ClarityUser>
{
	public String email;
	public String password;
	
	public transient UserAccessResult accessToken;
	
	public ClarityUser()
	{}
	
	public ClarityUser(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
}
