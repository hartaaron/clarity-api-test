package clarity.api.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps
{
	@Given("^a valid user with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void a_valid_user_with_username_and_password(String arg1, String arg2) throws Throwable {
		System.out.println("I have a valid user");
	}

	@When("^I login to Clarity$")
	public void i_login_to_Clarity() throws Throwable {


		System.out.println("I log in to Clarity");
	}

	@Then("^I should be logged in to Clarity$")
	public void i_should_be_logged_in_to_Clarity() throws Throwable {
		System.out.println("I am logged in");
	}

}
