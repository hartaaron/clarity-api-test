package api.steps;

import clarity.api.driver.ClarityDriver;
import clarity.api.model.ClarityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.rules.TestName;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base for Clarity step-definition classes to extend from.
 */
public abstract class ClarityStepsBase
{
	@Rule
	public TestName test = new TestName();
	
	//TODO: use ClarityTestData to load users from JSON
	public ClarityUser VALID_USER = new ClarityUser("clarity-external-testing@hart.com", "Cl@rity1");
	public ClarityUser VALID_ADMIN = new ClarityUser("bill.leung@hart.com", "Password1!");
	
	protected Logger log;
	protected ClarityDriver clarity;
	
	// use AssertJ SoftAssertions to check multiple assertions and not fail on first one
	protected SoftAssertions check = new SoftAssertions();
	
	public ClarityStepsBase(ClarityDriver clarity)
	{
		System.out.println("in ClarityStepsBase constructor " + clarity);
		this.log = LogManager.getLogger(this);
		this.clarity = clarity;
		log.debug("this.clarity: " + this.clarity);
		log.debug("this.clarity.data: " + this.clarity.data);
		
		assertThat(clarity.env).isNotNull();
	}
}
