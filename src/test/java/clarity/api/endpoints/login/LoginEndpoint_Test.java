package clarity.api.endpoints.login;

import clarity.ClarityApiTestCase;
import clarity.api.endpoints.ClarityResponseWithStatus;
import org.junit.Before;
import org.junit.Test;

public class LoginEndpoint_Test extends ClarityApiTestCase
{
	LoginEndpoint endpoint;

	@Before
	public void setUp()
	{
		endpoint = new LoginEndpoint(env);
	}

	@Test
	public void gets_access_result() throws Exception
	{
		UserAccessCredentials credentials = new UserAccessCredentials(validUser);
		UserAccessResult access = endpoint.send(credentials);
		
		check.assertThat(endpoint.getResponseStatusCode()).isEqualTo(200);
		check.assertThat(access).isNotNull();
		check.assertThat(access).isNotNull();
	}

	@Test
	public void provides_access_token_to_valid_user() throws Exception
	{
		UserAccessCredentials credentials = new UserAccessCredentials(validUser);
		UserAccessResult access = endpoint.send(credentials);
		
		check.assertThat(endpoint.getResponseStatusCode()).isEqualTo(200);
		check.assertThat(access).isNotNull();
		
		String json = endpoint.getResponseBody();
		
		ClarityResponseWithStatus body = new ClarityResponseWithStatus().fromJson(json);

		check.assertThat(body.success).isEqualTo(true);
		check.assertThat(body.code).isEqualTo(200);
		check.assertThat(body.data).isNotNull();

		System.out.println("===" + body.data.getClass().getName());

		System.out.println("===>>" + body.data.toString());

		UserAccessResult data = new UserAccessResult().fromJson(body.data.toString());
		check.assertThat(data.user_id).isNotNull();
		check.assertThat(data.token).isNotNull();
	}

	@Test
	public void denies_invalid_user() throws Exception
	{
		UserAccessCredentials credentials = new UserAccessCredentials(invalidUser.email, invalidUser.password);
		UserAccessResult responseBody = endpoint.send(credentials.toJson());

		check.assertThat(endpoint.getResponseStatusCode()).isEqualTo(401);

		String json = endpoint.getResponseBody();
		ClarityResponseWithStatus body = new ClarityResponseWithStatus().fromJson(json);
		check.assertThat(body.success).isEqualTo(false);
		check.assertThat(body.code).isEqualTo(401);
		check.assertThat(body.data).isEqualTo("401 Unauthorized");
	}
}
