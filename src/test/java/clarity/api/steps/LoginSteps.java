package clarity.api.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends ClarityTestSteps
{

	@Given("^a valid user with email \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void a_valid_user_with_email_and_password(String email, String password) throws Throwable
	{
		user = getUser(email, password);

	}

	@When("^I login to Clarity$")
	public void i_login_to_Clarity() throws Throwable
	{
		System.out.println("--I login to Clarity-- as user: " + user);
		System.out.println("email: " + user.email);
		System.out.println("password: " + user.password);

		clarity.login(user);
	}

	@Then("^I should be logged in to Clarity$")
	public void i_should_be_logged_in_to_Clarity() throws Throwable
	{
		assertThat(user.x_access_token).isNotEmpty();

	}

}
