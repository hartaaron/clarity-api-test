package clarity;

import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import clarity.api.util.GsonObjectMapper;
import clarity.util.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import static org.assertj.core.api.Assertions.assertThat;

public class ClarityTestCase
{
	@Rule
	public TestName test = new TestName();

	protected SoftAssertions check = new SoftAssertions();

	protected Logger log;
	protected ClarityEnvironment env;
	protected ClarityUser validUser;
	protected ClarityUser invalidUser;

	protected GsonObjectMapper gson = new GsonObjectMapper();

	@Before
	public void beforeTest()
	{
		log = new Logger(this.getClass().getSimpleName() + " " + test.getMethodName());
		log.write("========== START TEST ==========");

		env = ClarityEnvironment.TEST;
		log.write("env: " + env.name);
		log.write("CLARITY_BASE_URL: " + env.CLARITY_BASE_URL);

		validUser = new ClarityUser() {{
			email = "clarity-external-testing@hart.com";
			password = "Cl@rity1";
		}};
//		log.write("validUser: " + validUser);

		invalidUser = new ClarityUser() {{
			email = "clarity-invalid-user@hart.com";
			password = "Cl@rity1";
		}};
//		log.write("invalidUser: " + invalidUser);
	}

	@After
	public void afterTest()
	{
		log.write("========== END TEST ==========");
	}
}
