package clarity.api.model;

import clarity.api.util.GsonObjectMapper;

/**
 * Helper methods to convert domain objects to/from JSON
 *
 * Usage:
 * class ClarityUser extends JsonEntity<ClarityUser> { ... }
 * String json = user.toJson();
 * ClarityUser user = new ClarityUser().fromJson(json);
 *
 * @param <T>
 */
public abstract class JsonEntity<T extends JsonEntity>
{
	protected GsonObjectMapper gson = new GsonObjectMapper();

	public String toJson()
	{
		return gson.toJson(this);
	}
	
	public String toJson(Boolean pretty)
	{
		gson = new GsonObjectMapper().format(true);
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
