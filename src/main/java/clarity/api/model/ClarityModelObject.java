package clarity.api.model;

import clarity.util.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class ClarityModelObject
{
	Logger log;
	Gson gson;

	public ClarityModelObject()
	{
		log = new Logger(this.getClass());
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
	}

	public String toJson()
	{
		return gson.toJson(this);
	}
}
