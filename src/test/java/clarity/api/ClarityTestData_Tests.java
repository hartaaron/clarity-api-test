package clarity.api;

import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class ClarityTestData_Tests
{
	@Test
	public void should_load_users() throws Exception
	{
		HashMap<String, ClarityUser> users = ClarityTestData.LoadUsers("clarity.users.json");
		assertThat(users.size()).isEqualTo(2);
	}
	
	@Test
	public void should_load_patients() throws Exception
	{
		HashMap<String, ClarityPatient> patients = ClarityTestData.LoadPatients("clarity.patients.json");
		assertThat(patients.size()).isEqualTo(10);
	}
}
