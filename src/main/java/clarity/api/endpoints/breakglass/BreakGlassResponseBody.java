package clarity.api.endpoints.breakglass;

import clarity.api.endpoints.JsonEntity;

public class BreakGlassResponseBody extends JsonEntity<BreakGlassResponseBody>
{
	public String access_token;
	public String token_type;
	public Integer expires_in;
}
