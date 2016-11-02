package clarity.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


public class ClarityLogger
{
	
	public static Logger create()
	{
		return (Logger) LogManager.getRootLogger();
	}
	
	public static Logger create(String name)
	{
		return (Logger) LogManager.getLogger(name);
	}
	
	public static Logger create(Class c)
	{
		return (Logger) LogManager.getLogger(c);
	}
	
	public static Logger create(Object o)
	{
		return create(o.getClass().getSimpleName());
	}
}
