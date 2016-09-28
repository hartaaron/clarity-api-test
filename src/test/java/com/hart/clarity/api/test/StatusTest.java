package com.hart.clarity.api.test;

import com.hart.util.JSON;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest extends ClarityApiTest
{
	@Test
	public void should_get_api_management_info()
	{
		RequestSpecification statusRequest = clarityRequest.basePath("/api/management/info");

		response = statusRequest.when().get().then().extract().response();

		String jsonResult = printResponse(response);

		assertThat(JSON.isValid(jsonResult));

		String app_name = from(jsonResult).get("app.name");
		String app_description = from(jsonResult).get("app.description");
		String build_artifact = from(jsonResult).get("build.artifact");

		check.assertThat(app_name).as("app.name").isEqualTo("Beanstalk");
		check.assertThat(app_description).as("app description").startsWith("HartOS API");
		check.assertThat(build_artifact).as("build artifact").isEqualTo("beanstalk");
		check.assertAll();
	}
}
