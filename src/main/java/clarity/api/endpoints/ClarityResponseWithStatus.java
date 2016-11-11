package clarity.api.endpoints;

import clarity.api.model.JsonEntity;
import com.google.gson.annotations.Expose;

public class ClarityResponseWithStatus extends JsonEntity<ClarityResponseWithStatus>
{
	@Expose
	public Boolean success;

	@Expose
	public Integer code;

	@Expose
	public Object data;
}
