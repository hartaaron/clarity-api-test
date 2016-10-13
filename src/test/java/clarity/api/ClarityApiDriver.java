package clarity.api;

import clarity.api.endpoints.LoginEndpoint;
import clarity.api.model.ClarityEnvironment;
import clarity.api.api.util.Logger;
import clarity.api.model.ClarityPatient;
import clarity.api.model.ClarityUser;
import clarity.api.model.chart.ClarityPatientSession;
import clarity.api.model.chart.ClarityUserSession;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class ClarityApiDriver
{
	static Logger log = new Logger(ClarityApiDriver.class);

	ClarityEnvironment env;
	LoginEndpoint loginEndpoint;

	ClarityUser user;
	ClarityUserSession userSession;

	ClarityPatient currentPatient;
	ClarityPatientSession patientSession;

	public ClarityApiDriver(ClarityEnvironment env)
	{
		log = new Logger(this.getClass());
		loginEndpoint = new LoginEndpoint(env.TEST);
	}

	public void login(ClarityUser user) throws Exception
	{
		this.user = user;

		loginEndpoint.setEmail(user.email);
		loginEndpoint.setPassword(user.password);

		HttpResponse<JsonNode> response = loginEndpoint.send();

		if (response.getStatus() != 200)
		{
			throw new Exception("Clarity Login failure");
		}

		JSONObject json  = response.getBody().getObject();
//		userSession = ClarityUserSession.from(json);
	}
}
