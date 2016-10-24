package clarity.api.endpoints;

import clarity.api.util.GsonObjectMapper;

//public abstract class JsonEntity<T extends JsonEntity<T>>
public abstract class JsonEntity<T extends JsonEntity>
{
	protected GsonObjectMapper gson = new GsonObjectMapper();

	public String toJson()
	{
		return gson.toJson(this);
	}

	public String format(String json)
	{
		return gson.format(json);
	}

	public T fromJson(String json)
	{
		return (T) gson.fromJson(json, this.getClass());
	}
}
