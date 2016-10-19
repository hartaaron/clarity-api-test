package clarity.api.endpoints.access;

import clarity.api.model.JsonEntity;
import com.google.gson.annotations.Expose;

public class AccessCredentials extends JsonEntity
{
	@Expose
	public String email;

	@Expose
	public String password;

	public AccessCredentials() {}

	public AccessCredentials(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
}