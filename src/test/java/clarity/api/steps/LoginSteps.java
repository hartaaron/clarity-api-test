package clarity.api.steps;

import api.endpoints.ClarityApi;
import api.util.Logger;
import clarity.api.model.ClarityUser;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Properties;

public class LoginSteps extends ClarityTestSteps
{

	@Given("^a valid user with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void a_valid_user_with_username_and_password(String username, String password) throws Throwable
	{
		log.write(String.format("Given a valid user with username %s and password %s", username, password));

		user = getUser(username, password);
	}

	@When("^I login to Clarity$")
	public void i_login_to_Clarity() throws Throwable
	{
		clarity.login(user);
		log.write("When I log in to Clarity.");
	}

	@Then("^I should be logged in to Clarity$")
	public void i_should_be_logged_in_to_Clarity() throws Throwable
	{
		System.out.println("Then I am logged in.");
	}

}
