package clarity.api.endpoints.access;

import clarity.ClarityTestCase;
import clarity.api.model.ClarityUser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccessEndpoint_Test extends ClarityTestCase
{
	AccessEndpoint endpoint;

	@Before
	public void setUp()
	{
		endpoint = new AccessEndpoint(env);
	}

	@Test
	public void gets_access_result() throws Exception
	{
		AccessCredentials credentials = new AccessCredentials(validUser.email, validUser.password);
		HttpResponse<String> response = endpoint.send(credentials);

		check.assertThat(response).isNotNull();
		check.assertThat(response.getStatus()).isNotNull();
		check.assertThat(response.getBody()).isNotNull();
	}

	@Test
	public void provides_access_token_to_valid_user() throws Exception
	{
		ClarityUser user = validUser;
		log.write("user: " + user);
		log.write("email: " + user.email);
		log.write("password: " + user.password);

		HttpResponse<String> response = endpoint.send(user.email, user.password);

		check.assertThat(response.getStatus()).isEqualTo(200);

		String json = response.getBody();
		AccessResult result = gson.fromJson(json, AccessResult.class);

		check.assertThat(result.success).isEqualTo(true);
		check.assertThat(result.code).isEqualTo(200);
		check.assertThat(result.data).isNotNull();

		check.assertThat(result.data.user_id).isNotNull();
		check.assertThat(result.data.x_access_token).isNotNull();
	}

	@Test
	public void denies_invalid_user() throws Exception
	{
		AccessCredentials credentials = new AccessCredentials(invalidUser.email, invalidUser.password);
		HttpResponse<String> response = endpoint.send(credentials);

		check.assertThat(response.getStatus()).isEqualTo(401);
	}
}
