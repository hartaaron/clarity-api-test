package clarity.api.model;

import clarity.api.util.GsonObjectMapper;
import com.google.gson.annotations.Expose;

public class ClarityUser extends ClarityModelObject
{
	@Expose
	public String email;

	@Expose
	public String password;

	public String user_id;
	public String x_access_token;


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

	public Boolean hasToken()
	{
		return x_access_token != null;
	}
}
