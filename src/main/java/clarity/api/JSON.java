package clarity.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import gherkin.formatter.JSONFormatter;

public class JSON
{
	public static Boolean isValid(String jsonString)
	{
		JsonParser parser = new JsonParser();

		try
		{
			parser.parse(jsonString);
			return true;
		}
		catch (JsonSyntaxException e)
		{
			return false;
		}
	}

	public static String format(String json)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(gson.fromJson(json, Object.class));
	}
}
