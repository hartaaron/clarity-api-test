package clarity.api.model;

import java.util.HashMap;

public class ClarityEnvironment
{
	public static ClarityEnvironment DEV = new ClarityEnvironment("STAGE")
	{{
		CLARITY_BASE_URL = "https://clarity-dev.hart.com";
	}};

	public static ClarityEnvironment TEST = new ClarityEnvironment("TEST")
	{{
		CLARITY_BASE_URL = "https://clarity-tst.hart.com";
	}};

	public static ClarityEnvironment STAGE = new ClarityEnvironment("STAGE")
	{{
		CLARITY_BASE_URL = "https://clarity-stg.hart.com";
	}};

	public static HashMap<String, ClarityEnvironment> environments = new HashMap<String, ClarityEnvironment>()
	{{
		put("DEV", ClarityEnvironment.DEV);
		put("TEST", ClarityEnvironment.TEST);
		put("STAGE", ClarityEnvironment.STAGE);
	}};

	public String CLARITY_BASE_URL;

	ClarityEnvironment(String name)
	{
		this.name = name;
	}

	public static ClarityEnvironment get(String name)
	{
		if (environments.keySet().contains(name)) {
			return environments.get(name);
		}

		throw new RuntimeException("Unknown ClarityEnvironment: " + name);
	}

	public String name;
}
