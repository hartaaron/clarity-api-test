package com.hart.clarity.api.endpoints;

import java.net.MalformedURLException;
import java.net.URL;

public class ClarityApi
{
	public URL baseUrl;

	public LoginEndpoint login;

	public ClarityApi(URL baseUrl) throws MalformedURLException
	{
		init(baseUrl);
	}
	public ClarityApi(String baseUrl) throws MalformedURLException
	{
		init(new URL(baseUrl));
	}



	public void init(URL baseUrl) throws MalformedURLException
	{
		login = new LoginEndpoint(baseUrl);
	}
}
