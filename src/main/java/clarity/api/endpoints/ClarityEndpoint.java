package clarity.api.endpoints;

import clarity.api.util.GsonObjectMapper;
import clarity.util.Logger;
import clarity.api.model.ClarityEnvironment;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import com.mashape.unirest.request.body.RequestBodyEntity;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class ClarityEndpoint
{
	public ClarityEnvironment env;
	public String path;

	protected Logger log;
	protected GsonObjectMapper gson;

	protected HttpMethod requestMethod;
	protected String requestPath;
	protected HashMap<String, String> requestHeaders;
	protected String requestBody;

	protected HttpRequest request;
	protected HttpResponse<String> response;

	protected static final String APPLICATION_JSON = "application/json";

	protected static HashMap<String, String> JSON_HEADERS = new HashMap<String, String>()
	{{
		put("Content-Type", APPLICATION_JSON);
		put("Accept", APPLICATION_JSON);
	}};

	public ClarityEndpoint(ClarityEnvironment env, String path)
	{
		init(env, path);
	}

	public void init(ClarityEnvironment env, String path)
	{
		log = new Logger(this.getClass());
		gson = new GsonObjectMapper();

		this.env = env;
		this.path = path;

		requestPath = getPath();
		requestHeaders = JSON_HEADERS;
		requestBody = null;

		log.write("initialized");
	}

	public String getBaseUrl()
	{
		return env.CLARITY_BASE_URL;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getRequestUrl()
	{
		return getBaseUrl() + getPath();
	}

	public HttpMethod getRequestMethod()
	{
		return requestMethod;
	}

	public void setRequestMethod(HttpMethod requestMethod) {
		this.requestMethod = requestMethod;
	}

	public HashMap<String, String> getRequestHeaders()
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

	public HttpResponse<String> post(String url, HashMap<String, String> headers, String body) throws UnirestException
	{
		Unirest.setObjectMapper(gson);
		return Unirest.post(url).headers(headers).body(body).asString();
	}

	public void printUnirestRequest(HttpRequest request)
	{
		System.out.println("\n----- REQUEST -----\n");
//		System.out.println(request.getHttpMethod() + " " + request.getUrl());

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
			try {
				InputStream in = request.getBody().getEntity().getContent();
				String body = IOUtils.toString(in, "UTF-8");
				in.close();

				System.out.println("\n--- BODY ---");
				System.out.println(body);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String urlencode(String s)
	{
		String encoding = "utf-8";
		try {
			return URLEncoder.encode(s, encoding);
		} catch (Exception e)
		{
			log.write("failed to encode string: " + s);
			e.printStackTrace();
			return s;
		}
	}

	public void printUnirestRequest(RequestBodyEntity r)
	{
		printUnirestRequest(r.getHttpRequest());
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
