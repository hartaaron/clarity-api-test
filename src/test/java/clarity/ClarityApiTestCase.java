package clarity;

import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import clarity.api.unirest.UnirestClarityDriver;
import clarity.api.util.GsonObjectMapper;
import clarity.util.Logger;
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
		log = new Logger(this.getClass().getSimpleName() + " " + test.getMethodName());
		log.write("========== START TEST ==========");

		env = ClarityEnvironment.TEST;
		log.write("env: " + env.name);
		log.write("CLARITY_BASE_URL: " + env.CLARITY_BASE_URL);

		validUser = new ClarityUser("clarity-external-testing@hart.com", "Cl@rity1");
		log.write("validUser: " + validUser.toJson());

		invalidUser = new ClarityUser("clarity-invalid-user@hart.com", "Cl@rity1");
		log.write("invalidUser: " + invalidUser.toJson());
	}

	@After
	public void afterTest()
	{
		log.write("========== END TEST ==========");
	}
}
