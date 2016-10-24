package clarity.api.endpoints;

import com.google.gson.annotations.Expose;

public class ClarityResponseBody extends JsonEntity<ClarityResponseBody>
{
	@Expose
	public Boolean success;

	@Expose
	public Integer code;

	@Expose
	public Object data;
}
