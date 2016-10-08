package api.util;

import java.lang.reflect.Type;
import java.text.MessageFormat;

public class Logger<T>
{
	public String name;
	public String level;

	public Logger()
	{}

	public Logger(String name) {
		this.name = name;
		this.level = null;
	}

	public Logger(String name, String level) {
		this.name = name;
		this.level = level;
	}

	public Logger(Class<T> type){
		name = type.getClass().getSimpleName();
	}

	public Logger(Object o) {
		name = o.getClass().getSimpleName();
	}

	public void write(String message)
	{
		System.out.println(format(message));
	}

	public void write(MessageFormat message, Object...params)
	{
		write(message.format(params));
	}

	public String format(String message)
	{
		if (name != null) {
			message = name + " " + message;
		}

		return message;
	}
}
