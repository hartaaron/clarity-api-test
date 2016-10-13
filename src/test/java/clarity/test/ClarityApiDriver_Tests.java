package clarity.test;

import clarity.api.ClarityApiDriver;
import clarity.api.api.util.Logger;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.ClarityUser;
import com.google.gson.Gson;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class ClarityApiDriver_Tests
{
	@Rule
	public TestName testName = new TestName();

	SoftAssertions check = new SoftAssertions();

	Logger log = new Logger(ClarityApiDriver_Tests.class);

	ClarityEnvironment env;
	ClarityApiDriver clarity;
	ClarityUser user;

	Gson gson = new Gson();

	@Before
	public void setUp()
	{
		env = ClarityEnvironment.TEST;
		clarity = new ClarityApiDriver(env);
		user = new ClarityUser()
		{{
			email = "clarity-external-testing@hart.com";
			password = "Cl@rity1";
		}};
	}

	@Test
	public void test_login() throws Exception
	{
		clarity.login(user);
	}
}
