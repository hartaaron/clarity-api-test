package clarity.api;

import clarity.api.model.JsonEntity;

import java.util.HashMap;

/**
 * Contains the base URL for Clarity Driver.
 *
 * Three environments are currently defined:
 *   DEV
 *   TEST
 *   STAGE
 *
 * Usage:
 *
 * ClarityEnvironment env = ClarityEnvironment.get("TEST");
 */
public class ClarityEnvironment extends JsonEntity<ClarityEnvironment>
{
	public String name;
	public String baseUrl;
	
	// pre-defined environments
	public static ClarityEnvironment DEV = new ClarityEnvironment("DEV")
	{{
		baseUrl = "https://clarity-dev.hart.com";
	}};
	
	public static ClarityEnvironment TEST = new ClarityEnvironment("TEST")
	{{
		baseUrl = "https://clarity-tst.hart.com";
	}};
	
	public static ClarityEnvironment STAGE = new ClarityEnvironment("STAGE")
	{{
		baseUrl = "https://clarity-stg.hart.com";
	}};
	
	// used for fetching by name
	public static HashMap<String, ClarityEnvironment> environments = new HashMap<String, ClarityEnvironment>()
	{{
		put("DEV", ClarityEnvironment.DEV);
		put("TEST", ClarityEnvironment.TEST);
		put("STAGE", ClarityEnvironment.STAGE);
	}};
	
	/**
	 *  Default constructor
	 */
	ClarityEnvironment()
	{}
	
	/**
	 * Constructor that sets name
	 * @param name
	 */
	ClarityEnvironment(String name)
	{
		this.name = name;
	}
	
	/**
	 * Constructor that sets name and baseUrl
	 *
	 * @param name
	 * @param baseUrl
	 */
	ClarityEnvironment(String name, String baseUrl)
	{
		this.name = name;
		this.baseUrl = baseUrl;
	}
	
	
	/**
	 * get an instance of ClarityEnvironment (by name) from the static collection
	 *
	 * @param name
	 * @return
	 */
	public static ClarityEnvironment get(String name)
	{
		return environments.get(name.toUpperCase());
	}
	
	/**
	 * add an instance of ClarityEnvironment (by name) to the static collection
	 * @param name
	 * @param baseUrl
	 */
	public static void add(String name, String baseUrl)
	{
		ClarityEnvironment instance = new ClarityEnvironment(name, baseUrl);
		environments.put(name, instance);
	}
}
