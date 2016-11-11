package clarity.api;

import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class ClarityTestData_Tests
{
	@Test
	public void should_load_users() throws Exception
	{
		ClarityTestData data = new ClarityTestData();
		
		try {
			List<ClarityUser> users = data.loadUsers("clarity.users.json");
			assertThat(users.size()).isEqualTo(2);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void should_load_patients() throws Exception
	{
		ClarityTestData data = new ClarityTestData();
		
		try {
			List<ClarityPatient> patients = data.loadPatients("clarity.patients.json");
			assertThat(patients.size()).isEqualTo(10);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
