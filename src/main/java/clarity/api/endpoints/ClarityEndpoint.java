package clarity.api.endpoints;

import clarity.api.api.util.Logger;
import clarity.api.model.ClarityEnvironment;
import com.google.gson.Gson;
import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class ClarityEndpoint
{
	Logger log;
	Gson gson;

	RequestSpecification request;


	public HttpResponse<JsonNode> response;

//	HttpMethod requestMethod;
	String requestPath;
	Map<String, String> requestHeaders;
	String requestBody;

	ClarityEnvironment env;
	public String path;

	static final String APPLICATION_JSON = "application/json";

	static Map<String, String> JSON_HEADERS = new HashMap<String, String>()
	{{
		put("Content-Type", APPLICATION_JSON);
		put("Accept", APPLICATION_JSON);
	}};

	public ClarityEndpoint(ClarityEnvironment env, String path)
	{
		init(env);
		this.path = path;
	}

	public void init(ClarityEnvironment env)
	{
		log = new Logger(this.getClass());
		gson = new Gson();
		this.env = env;
		requestPath = getPath();
		requestHeaders = JSON_HEADERS;
		requestBody = null;

		log.write("initialized");
	}

	public String getBaseUrl()
	{
		return env.CLARITY_URL;
	}

	public String getPath()
	{
		return path;
	}

	public String getRequestUrl()
	{
		return getBaseUrl() + getPath();
	}

	public Map<String, String> getRequestHeaders()
	{
		return requestHeaders;
	}



	public Integer getResponseStatusCode()
	{
		if (response == null) { return null; }

		return response.getStatus();
	}

	public String getResponseStatusText()
	{
		if (response == null) { return null; }

		return response.getStatusText();
	}

	public Headers getResponseHeaders()
	{
		if (response == null) { return null; }

		return response.getHeaders();
	}

	public String getResponseBody()
	{
		if (response == null) { return null; }

		return response.getBody().toString();
	}



	public void printUnirestRequest(HttpRequest request) throws IOException
	{
		System.out.println("\n----- REQUEST -----\n");
		System.out.println(request.getHttpMethod() + " " + request.getUrl());

		// print headers
		System.out.println("\n--- HEADERS ---");
		Map<String, List<String>> headers = request.getHeaders();
		for(String key : headers.keySet())
		{
			List<String> values = headers.get(key);
			for(String value : values)
			{
				System.out.println(key + " : " + value);
			}
		}

		if (request instanceof HttpRequestWithBody)
		{
			InputStream in = request.getBody().getEntity().getContent();
			String body = IOUtils.toString(in, "UTF-8");
			in.close();
			System.out.println("\n--- BODY ---");
			System.out.println(body);
		}
	}

	public void printUnirestResponse(HttpResponse response)
	{
		System.out.println("\n----- RESPONSE -----\n");
		System.out.println(response.getStatus());
		System.out.println("\n--- HEADERS ---");
		System.out.println(response.getHeaders());
		System.out.println("\n--- BODY ---");
		System.out.println(response.getBody());
	}
}
