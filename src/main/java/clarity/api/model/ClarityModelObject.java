package clarity.api.model;

import clarity.api.api.util.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class ClarityModelObject
{
	Logger log;
	Gson gson;
	GsonBuilder gsonBuilder;

	public ClarityModelObject()
	{
		log = new Logger(this.getClass());
		gson = new Gson();
		gsonBuilder = new GsonBuilder();
	}

	public String toJson()
	{
		return gson.toJson(this);
	}
}
