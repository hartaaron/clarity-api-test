package clarity.api.steps;

import clarity.api.model.ClarityUser;
import clarity.util.ClarityLogger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends ClarityTestBase
{
	String email;
	String password;
	ClarityUser user;

	@Before
	public void setup(Scenario scenario) throws IOException
	{
		log = ClarityLogger.create(this);
		log.debug("STATUS: " + scenario.getStatus());
		clarity = setupEnvironment();
	}
	
	@Given("^a valid user with email \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void a_valid_user_with_email_and_password(String email, String password) throws Throwable
	{
		this.email = email;
		this.password = password;
	}

	@When("^I login to Clarity$")
	public void i_login_to_Clarity() throws Throwable
	{
		user = clarity.login(email, password);
	}

	@Then("^I should be logged in to Clarity$")
	public void i_should_be_logged_in_to_Clarity() throws Throwable
	{
		assertThat(user.access.token).isNotEmpty();
	}
	


}
