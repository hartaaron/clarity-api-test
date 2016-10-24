package clarity.api.endpoints.access;

import clarity.ClarityApiTestCase;
import clarity.api.endpoints.ClarityResponseBody;
import clarity.api.model.ClarityUser;
import com.mashape.unirest.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccessEndpoint_Test extends ClarityApiTestCase
{
	AccessEndpoint endpoint;

	@Before
	public void setUp()
	{
		endpoint = new AccessEndpoint(env);
	}

	@Test
	public void gets_access_result() throws Exception
	{	HttpResponse<String> response = endpoint.send(validUser);

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
		ClarityResponseBody body = new ClarityResponseBody().fromJson(json);

		check.assertThat(body.success).isEqualTo(true);
		check.assertThat(body.code).isEqualTo(200);
		check.assertThat(body.data).isNotNull();

		System.out.println("===" + body.data.getClass().getName());

		System.out.println("===>>" + body.data.toString());

		AccessData data = new AccessData().fromJson(body.data.toString());
		check.assertThat(data.user_id).isNotNull();
		check.assertThat(data.x_access_token).isNotNull();
	}

	@Test
	public void denies_invalid_user() throws Exception
	{
		AccessCredentials credentials = new AccessCredentials(invalidUser.email, invalidUser.password);
		HttpResponse<String> response = endpoint.send(credentials);

		check.assertThat(response.getStatus()).isEqualTo(401);

		String json = response.getBody();
		ClarityResponseBody body = new ClarityResponseBody().fromJson(json);
		check.assertThat(body.success).isEqualTo(false);
		check.assertThat(body.code).isEqualTo(401);
		check.assertThat(body.data).isEqualTo("401 Unauthorized");


	}
}
