package com.hart.clarity.api.model;

public class Environment
{
	public static Environment TEST = new Environment(){{
		CLARITY_URL = "https://clarity-tst.hart.com";
	}};

	public static String CLARITY_URL;
}
