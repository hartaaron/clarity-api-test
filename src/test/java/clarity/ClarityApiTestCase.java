package clarity;

import clarity.api.ClarityEnvironment;
import clarity.api.driver.UnirestClarityDriver;
import clarity.api.model.ClarityUser;
import clarity.api.util.GsonObjectMapper;
import clarity.util.ClarityLogger;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import static org.assertj.core.api.Assertions.assertThat;

public class ClarityApiTestCase
{
	@Rule
	public TestName test = new TestName();

	protected SoftAssertions check = new SoftAssertions();

	protected Logger log;
	protected ClarityEnvironment env;
	protected ClarityUser validUser;
	protected ClarityUser invalidUser;
	protected UnirestClarityDriver clarity;

	protected GsonObjectMapper gson = new GsonObjectMapper();

	@Before
	public void beforeTest()
	{
		log = ClarityLogger.create(this.getClass().getSimpleName() + " " + test.getMethodName());
		log.debug("========== START TEST ==========");

		env = ClarityEnvironment.TEST;
		log.debug("env: " + env.name);
		log.debug("CLARITY_BASE_URL: " + env.baseUrl);

		validUser = new ClarityUser("clarity-external-testing@hart.com", "Cl@rity1");
		log.debug("validUser: " + validUser.toJson());

		invalidUser = new ClarityUser("clarity-invalid-user@hart.com", "Cl@rity1");
		log.debug("invalidUser: " + invalidUser.toJson());
		
		clarity = new UnirestClarityDriver();
	}

	@After
	public void afterTest()
	{
		log.debug("========== END TEST ==========");
	}
}
