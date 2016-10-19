package clarity.api.util;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.ObjectMapper;

public class GsonObjectMapper implements ObjectMapper
{
	public Gson gson;
	Gson gsonFormatted;
	Gson gsonUnformatted;

	public GsonObjectMapper()
	{
		gsonFormatted = new GsonBuilder()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.excludeFieldsWithoutExposeAnnotation()
				.create();

		gsonUnformatted = new GsonBuilder()
				.disableHtmlEscaping()
				.excludeFieldsWithoutExposeAnnotation()
				.create();

		format(false);
	}

	public void format(Boolean pretty)
	{
		if (pretty == true)
		{
			gson = gsonFormatted;
		}
		else
		{
			gson = gsonUnformatted;
		}
	}

	public <T> T fromJson(String value, Class<T> type)
	{
		return gson.fromJson(value, type);
	}

	public String toJson(Object value)
	{
		return gson.toJson(value);
	}

	public String format(String json)
	{
		Object o = gsonFormatted.fromJson(json, Object.class);
		String j = gsonFormatted.toJson(json);
		return j;
	}

	@Override
	public <T> T readValue(String value, Class<T> type)
	{
		return fromJson(value, type);
	}

	@Override
	public String writeValue(Object value)
	{
		return gson.toJson(value);
	}
}
