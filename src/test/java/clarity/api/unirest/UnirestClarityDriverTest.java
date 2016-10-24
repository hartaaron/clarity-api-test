package clarity.api.unirest;

import clarity.ClarityApiTestCase;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnirestClarityDriverTest extends ClarityApiTestCase
{
	UnirestClarityDriver clarity;

	@Before
	public void setUp()
	{
		clarity = new UnirestClarityDriver(env);
	}

	@Test
	public void should_login() throws Exception
	{
		clarity.login(validUser);

		check.assertThat(clarity.user).isNotNull();

		String user_id = clarity.user.user_id;
		String x_access_token = clarity.user.x_access_token;

		log.write("user_id: " + user_id);
		log.write("x_access_token: " + x_access_token);

		assertThat(user_id).isNotEmpty();
		assertThat(x_access_token).isNotEmpty();
	}
}
