package clarity.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class UnirestPrinter
{
	void printUnirestRequest(HttpRequest request) throws IOException
	{
		System.out.println("\n----- REQUEST -----\n");
		System.out.println(request.getHttpMethod() + " " + request.getUrl());

		printRequestHeaders(request);

		if (request instanceof HttpRequestWithBody) {
			printRequestBody((HttpRequestWithBody) request);
		}
	}

	void printRequestHeaders(HttpRequest request)
	{
		Map<String, List<String>> headers = request.getHeaders();
		for(String key : headers.keySet()){
			List<String> values = headers.get(key);
			for(String value : values) {
				System.out.println(key + " : " + value);
			}
		}
	}

	void printRequestBody(HttpRequestWithBody request)
	{
		try {
			InputStream in = request.getBody().getEntity().getContent();
			String body = IOUtils.toString(in, "UTF-8");
			in.close();
			System.out.println(body);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	void printUnirestResponse(HttpResponse response)
	{
		System.out.println("\n----- RESPONSE -----\n");
		System.out.println(response.getStatus());
		printResponseHeaders(response);
		System.out.println(response.getBody());
	}

	void printResponseHeaders(HttpResponse response)
	{
		Map<String, List<String>> headers = response.getHeaders();
		for(String key : headers.keySet()){
			List<String> values = headers.get(key);
			for(String value : values) {
				System.out.println(key + " : " + value);
			}
		}
	}
}
