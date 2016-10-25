package clarity.api.endpoints.patientsearch;

import clarity.api.UnirestPrinter;
import clarity.api.endpoints.ClarityEndpoint;
import clarity.api.model.ClarityEnvironment;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import java.util.HashMap;

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

	public PatientSearchEndpoint setAccessToken(String x_access_token)
	{
		requestHeaders.put("x-access-token", x_access_token);
		return this;
	}

	public String getQueryString()
	{
		if (queryString.isEmpty())
		{
			buildQueryString();
		}

		return queryString;
	}

	public PatientSearchEndpoint setQueryString(String queryString)
	{
		this.queryString = queryString;
		return this;
	}

	private PatientSearchEndpoint buildQueryString()
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

		return this;
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

	public HttpResponse<String> send(String patientLastName, String patientFirstName, String patientDOB) throws UnirestException
	{
		log.write("send...");

		String url = getRequestUrl() + getQueryString();
		log.write("url: " + url);
		HashMap<String, String> headers = getRequestHeaders();
		
		return Unirest.get(url).asString();
	}
	
	public HttpResponse<String> send(String patientSearchString) throws UnirestException
	{
		setQueryString("?size=1000&q=" + urlencode(patientSearchString));
		String url = getRequestUrl() + getQueryString();
		log.write("url: " + url);
		
		HttpRequest request = Unirest.get(url).headers(getRequestHeaders());
		
		UnirestPrinter.printRequest(request);
		
		HttpResponse<String> response = request.asString();
		UnirestPrinter.printResponse(response);
		
		return response;
	}
}
