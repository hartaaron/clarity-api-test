package clarity.api.util;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.ObjectMapper;

import java.lang.reflect.Modifier;

public class GsonObjectMapper implements ObjectMapper
{
	public Gson gson;
	Gson gsonFormatted;
	Gson gsonUnformatted;

	public GsonObjectMapper()
	{
		GsonBuilder gsonBuilder = new GsonBuilder()
		.excludeFieldsWithModifiers(
					Modifier.STATIC,
					Modifier.PRIVATE,
					Modifier.PROTECTED,
					Modifier.TRANSIENT
		);
//					Modifier.constructorModifiers(),
//					Modifier.interfaceModifiers(),
//					Modifier.methodModifiers()
//		);

		gsonUnformatted = gsonBuilder.create();
		gsonFormatted = gsonBuilder.setPrettyPrinting().create();

		format(false);
	}

	public GsonObjectMapper format(Boolean formatted)
	{
		if (formatted == true)
		{
			gson = gsonFormatted;
		}
		else
		{
			gson = gsonUnformatted;
		}

		return this;
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
