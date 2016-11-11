package clarity.api;


import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClarityEnvironment_Tests
{
	@Test
	public void should_get_clarity_environment_from_system_property()
	{
		System.setProperty("CLARITY_ENV", "test");
		
		String CLARITY_ENV = System.getProperty("CLARITY_ENV");
		System.out.println("CLARITY_ENV: " + CLARITY_ENV);
		
		
		assertThat(CLARITY_ENV).isNotNull();
		
		ClarityEnvironment env = ClarityEnvironment.get(CLARITY_ENV);
		
		assertThat(env).isNotNull();
		assertThat(env.baseUrl).isEqualTo("https://clarity-tst.hart.com");
	}
}
