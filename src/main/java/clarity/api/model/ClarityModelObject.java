package clarity.api.model;

import clarity.api.endpoints.JsonEntity;
import clarity.util.ClarityLogger;
import com.google.gson.Gson;
import org.apache.logging.log4j.core.Logger;

public abstract class ClarityModelObject extends JsonEntity<ClarityModelObject>
{
	public transient Gson gson;
	protected Logger log;

	public ClarityModelObject()
	{
		super();
		log = ClarityLogger.create(this);
	}
}
