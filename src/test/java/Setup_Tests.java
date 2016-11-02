import clarity.api.steps.ClarityTestBase;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.fail;

public class Setup_Tests
{
	
	@Test
	public void should_load_test_properties()
	{
		ClarityTestBase test = new ClarityTestBase() {};
		
		try {
			Properties settings = test.loadSettings();
			String foo = settings.getProperty("foo");
			
			System.out.println("clarity.env: " + settings.getProperty("clarity.env"));
			System.out.println("clarity.users.json: " + settings.getProperty("clarity.users.json"));
			System.out.println("clarity.patients.json: " + settings.getProperty("clarity.patients.json"));
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
