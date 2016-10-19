package clarity.api.model;

import clarity.api.util.GsonObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//public abstract class JsonEntity<T extends JsonEntity<T>>
public abstract class JsonEntity<T extends JsonEntity>
{
	private GsonObjectMapper gson;
	private GsonObjectMapper gsonFormatted;

	public JsonEntity()
	{
		gson = new GsonObjectMapper();
	}

	public String toJson() {
		return gson.toJson(this);
	}

	public String toJsonFormatted() {
		return gsonFormatted.toJson(this);
	}

	public T fromJson(String json) {
		return (T) gson.fromJson(json, this.getClass());
	}
}
