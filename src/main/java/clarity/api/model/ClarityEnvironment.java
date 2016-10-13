package clarity.api.model;

public class ClarityEnvironment
{
	public static ClarityEnvironment TEST = new ClarityEnvironment("TEST")
	{{
		CLARITY_URL = "https://clarity-tst.hart.com";
	}};

	public static String CLARITY_URL;

	ClarityEnvironment(String name)
	{
		this.name = name;
	}

	public String name;
}
