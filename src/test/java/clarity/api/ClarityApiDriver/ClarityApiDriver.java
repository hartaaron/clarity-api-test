package clarity.api.ClarityApiDriver;

import api.util.Logger;
import clarity.api.model.ClarityUser;

public class ClarityApiDriver
{
	Logger log = new Logger(ClarityApiDriver.class);

	public ClarityApiDriver()
	{
	}

	public void login(ClarityUser user)
	{
		log.write("logging in as " + user);
	}
}
