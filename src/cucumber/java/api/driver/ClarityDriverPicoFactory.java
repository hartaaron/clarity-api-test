package api.driver;

import clarity.api.driver.UnirestClarityDriver;
import cucumber.runtime.java.picocontainer.PicoFactory;

/**
 * Extends the PicoContainer ObjectFactory
 * to initialize the ClarityDriver
 * and make it available across step-definition classes.
 */
public class ClarityDriverPicoFactory extends PicoFactory
{
	public ClarityDriverPicoFactory()
	{
		// specify ClarityDriver implementation to inject here
		Class clarityDriverType = UnirestClarityDriver.class;
		
		addClass(clarityDriverType);
	}
}
