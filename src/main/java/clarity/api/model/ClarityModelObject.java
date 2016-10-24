package clarity.api.model;

import clarity.api.endpoints.JsonEntity;
import clarity.util.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class ClarityModelObject extends JsonEntity<ClarityModelObject>
{
	public transient Gson gson;
	protected Logger log;

	public ClarityModelObject()
	{
		super();
		log = new Logger(this.getClass());
	}
}
