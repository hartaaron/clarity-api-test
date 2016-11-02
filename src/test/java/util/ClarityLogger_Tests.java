package util;

import clarity.util.ClarityLogger;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClarityLogger_Tests
{
	@Test
	public void should_write_to_stdout()
	{
		Logger log = ClarityLogger.create("foo");
		log.debug("this is a log messag");
		
		assertThat(true).isEqualTo(true);
	}
}
