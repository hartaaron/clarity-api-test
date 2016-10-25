package clarity.api.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends ClarityTestSteps
{
	Scenario scenario;
	
	@Before
	public void before(Scenario scenario)
	{
		this.scenario = scenario;
		log.write("SCENARIO: " + scenario.getName());
	}
	
	@Given("^a valid user with email \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void a_valid_user_with_email_and_password(String email, String password) throws Throwable
	{
		log.write(String.format("GIVEN: a valid user with email %s and password %s", email, password));
		user = getUser(email, password);
	}

	@When("^I login to Clarity$")
	public void i_login_to_Clarity() throws Throwable
	{
		log.write("WHEN: i_login_to_Clarity");
		log.write("email: " + user.email);
		log.write("password: " + user.password);

		clarity.login(user);
	}

	@Then("^I should be logged in to Clarity$")
	public void i_should_be_logged_in_to_Clarity() throws Throwable
	{
		assertThat(user.x_access_token).isNotEmpty();
	}
	
	@After
	public void after(Scenario scenario)
	{
		log.write("STATUS: " + scenario.getStatus());
	}

}
