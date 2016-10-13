package clarity.api.api.util;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JSON
{
	public static Boolean isValid(String jsonString)
	{
		JsonParser parser = new JsonParser();

		try {
			parser.parse(jsonString);
			return true;
		}
		catch (JsonSyntaxException e)
		{
			return false;
		}
	}
}
