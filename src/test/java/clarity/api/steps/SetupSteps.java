package clarity.api.steps;

import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import clarity.api.unirest.UnirestClarityDriver;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SetupSteps extends ClarityTestBase
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
	
//	@Given("^I am logged in to Clarity$")
//	public void i_am_logged_in_to_Clarity() throws Throwable
//	{
//		log.debug("GIVEN: I am logged in to clarity");
//
//		ClarityUser intendedUser = getUser("clarity-external-testing@hart.com", "Cl@rity1");
//		user = clarity.login(intendedUser);
//
//		log.debug("user: " + user.toJson());
//		assertThat(user.access.token).isNotNull();
//	}
	
}
