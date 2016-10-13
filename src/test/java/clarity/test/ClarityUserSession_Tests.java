package clarity.test;

import clarity.api.api.util.Logger;
import clarity.api.endpoints.LoginEndpoint;
import clarity.api.model.ClarityEnvironment;
import clarity.api.model.chart.ClarityUserSession;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class ClarityUserSession_Tests
{
	@Rule
	public TestName testName = new TestName();

	SoftAssertions check = new SoftAssertions();

	Logger log;

	LoginEndpoint loginEndpoint;



	@Before
	public void setUp() throws Exception
	{
		log = new Logger(testName.getMethodName());
		log.indent = 1;
		log.write("========== STARTING TEST ==========");


	}

	@Test
	public void should_get_from_JSONObject() throws Exception
	{

		LoginEndpoint loginEndpoint = new LoginEndpoint(ClarityEnvironment.TEST);
		loginEndpoint.setEmail("clarity-external-testing@hart.com");
		loginEndpoint.setPassword("Cl@rity1");

		HttpResponse<JsonNode> response = loginEndpoint.send();
		JsonNode json = response.getBody();
		JSONObject object = json.getObject();

		ClarityUserSession session = ClarityUserSession.fromJSON(object);

		log.write("userId: " + session.userId);
		log.write("accessToken: " + session.accessToken);
	}

	@Test
	public void should_get_from_GSON_JsonObject() throws Exception
	{
		LoginEndpoint loginEndpoint = new LoginEndpoint(ClarityEnvironment.TEST);
		loginEndpoint.setEmail("clarity-external-testing@hart.com");
		loginEndpoint.setPassword("Cl@rity1");

		HttpResponse<JsonNode> response = loginEndpoint.send();
		String json = response.getBody().toString();

		log.write("JSON: " + json);

		ClarityUserSession session = ClarityUserSession.fromGSON(json);

		log.write("userId: " + session.userId);
		log.write("accessToken: " + session.accessToken);

	}

}
