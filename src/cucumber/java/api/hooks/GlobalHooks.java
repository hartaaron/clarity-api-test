package api.hooks;

import clarity.api.driver.ClarityDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Setup data for all steps
 *
 * ClarityDriver is injected by see: ClarityDriverPicoFactory
 */
public class GlobalHooks
{
	public static Logger log = LogManager.getLogger();

	public ClarityDriver clarity;
	
	
	public GlobalHooks(ClarityDriver clarity) throws IOException
	{
		this.clarity = clarity;
	}

	@Before
	public void before(Scenario scenario) throws IOException
	{
		log.info("\n======================\n");
		log.info("BEGIN SCENARIO: " + scenario.getName() + "\n");
	}

	@After
	public void after(Scenario scenario) throws IOException
	{
		log.info("\n" + "END SCENARIO: " + scenario.getName() + " STATUS: " + scenario.getStatus());
		log.info("\n======================\n");
	}
}
