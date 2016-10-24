package clarity.api.endpoints.patientsearch;

import clarity.api.endpoints.ClarityEndpoint;
import clarity.api.model.ClarityEnvironment;
import com.mashape.unirest.http.Unirest;

import java.net.URLEncoder;

import static org.testng.util.Strings.escapeHtml;


public class PatientSearchEndpoint extends ClarityEndpoint
{
	public static String path = "/api/v1/patients";

	private String patientFirstName;
	private String patientLastName;
	private String patientDOB;

	public int size = 0;

	private String queryString;

	public PatientSearchEndpoint(ClarityEnvironment env)
	{
		super(env, path);
	}

	public void setAccessToken(String x_access_token)
	{
		requestHeaders.put("x-access-token", x_access_token);
	}

	public String getQueryString()
	{
		if (queryString.isEmpty())
		{
			buildQueryString();
		}

		return queryString;
	}

	public void setQueryString(String queryString)
	{
		this.queryString = queryString;
	}

	private String buildQueryString()
	{
		StringBuilder qBuilder = new StringBuilder();

		if (patientLastName != null) {
			qBuilder.append(urlencode(patientLastName));
		}
		if (patientFirstName != null) {
			if (qBuilder.length() > 0) {
				qBuilder.append(",");
			}
			qBuilder.append(urlencode(patientFirstName));
		}

		if (patientDOB != null) {
			if (qBuilder.length() > 0) {
				qBuilder.append(",");
			}
			qBuilder.append(urlencode(patientDOB));
		}

		String q = qBuilder.toString();

		StringBuilder queryStringBuilder = new StringBuilder();

		if (! q.isEmpty()) {
			if (queryStringBuilder.length() > 0) {
				queryStringBuilder.append("&");
			}
			queryStringBuilder.append("q=");
			queryStringBuilder.append(q);
		}

		if (size > 0) {
			if (queryStringBuilder.length() > 0) {
				queryStringBuilder.append("&");
			}
			queryStringBuilder.append("size=");
			queryStringBuilder.append(String.valueOf(size));
		}

		queryString = queryStringBuilder.toString();

		return queryString;
	}

	public String getPatientLastName() { return this.patientLastName; }

	public void setPatientLastName(String patientLastName)
	{
		this.patientLastName = patientLastName;
		buildQueryString();
	}

	public String getPatientFirstName() { return this.patientFirstName; }

	public void setPatientFirstName(String patientFirstName)
	{
		this.patientFirstName = patientFirstName;
		buildQueryString();
	}

	public String getPatientDOB() { return this.patientDOB; }

	public void setPatientDOB(String patientDOB)
	{
		this.patientDOB = patientDOB;
		buildQueryString();
	}

	public void send(String patientLastName, String patientFirstName, String patientDOB)
	{
		log.write("send...");

		String url = getRequestUrl() + getQueryString();
		log.write("url: " + url);

		Unirest.get(getRequestUrl() + getQueryString());
	}
}
