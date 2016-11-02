package clarity.api.steps;

import clarity.api.model.ClarityEnvironment;
import clarity.api.unirest.UnirestClarityDriver;
import cucumber.api.java.Before;

public class SetupSteps
{
	public static ClarityEnvironment env;
	public static UnirestClarityDriver clarity;
	
	@Before("@existingUser")
	public void setupExistingUser()
	{
		String CLARITY_ENV = System.getProperty("CLARITY_ENV");
		env = ClarityEnvironment.get(CLARITY_ENV);
		
		clarity = new UnirestClarityDriver(env);
	}
	
}
