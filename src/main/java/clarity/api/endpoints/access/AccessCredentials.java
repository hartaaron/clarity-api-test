package clarity.api.endpoints.access;

import clarity.api.endpoints.JsonEntity;
import com.google.gson.annotations.Expose;

public class AccessCredentials extends JsonEntity<AccessCredentials>
{
	@Expose
	public String email;

	@Expose
	public String password;

	public AccessCredentials()
	{
		super();
	}

	public AccessCredentials(String email, String password)
	{
		super();
		this.email = email;
		this.password = password;
	}
}