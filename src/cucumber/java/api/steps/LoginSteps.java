package api.steps;

import clarity.api.driver.ClarityDriver;
import clarity.api.model.ClarityUser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends ClarityStepsBase
{
	ClarityUser user;
	
	public LoginSteps(ClarityDriver clarity) throws Exception
	{
		super(clarity);
	}
	
	@Given("^a user with email \"(.*)\" and password \"(.*)\"$")
	public void a_user_with_email_and_password(String email, String password) throws Throwable
	{
		user = new ClarityUser(email, password);
	}
	
	@When("^I login to Clarity$")
	public void i_login_to_Clarity() throws Throwable
	{
		user = clarity.login(user.email, user.password);
		
	}
	
	@Then("^I should have access to Clarity$")
	public void i_should_have_access_to_Clarity() throws Throwable
	{
		log.debug("ACCESS_TOKEN: " + user.accessToken.toJson());
		assertThat(user.accessToken.isSet()).isTrue();
	}
	
	@Then("^I should not have access to Clarity$")
	public void i_should_not_have_access_to_Clarity() throws Throwable
	{
		log.debug("ACCESS_TOKEN: " + clarity.accessToken);
		assertThat(user.accessToken.isSet()).isFalse();
	}
	

}
