package clarity.api.endpoints.login;

import clarity.api.model.ClarityUser;
import clarity.api.model.JsonEntity;
import com.google.gson.annotations.Expose;

public class UserAccessCredentials extends JsonEntity<UserAccessCredentials>
{
	@Expose
	public String email;

	@Expose
	public String password;

	public UserAccessCredentials()
	{
		super();
	}

	public UserAccessCredentials(String email, String password)
	{
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserAccessCredentials(ClarityUser user)
	{
		super();
		this.email = user.email;
		this.password = user.password;
	}
}