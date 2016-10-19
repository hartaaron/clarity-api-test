package clarity.api.unirest;

import clarity.ClarityTestCase;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnirestClarityDriverTest extends ClarityTestCase
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
	}
}
