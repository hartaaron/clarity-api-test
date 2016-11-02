package clarity.api.hooks;

import clarity.api.steps.ClarityTestBase;
import clarity.util.ClarityLogger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;

public class Hooks extends ClarityTestBase
{
	@Before
	public void setup(Scenario scenario) throws IOException
	{
		log = ClarityLogger.create(this);
		log.debug("STATUS: " + scenario.getStatus());
		setupEnvironment();
	}
	
	@After
	public void after(Scenario scenario)
	{
		log.debug("STATUS: " + scenario.getStatus());
	}
	
}
