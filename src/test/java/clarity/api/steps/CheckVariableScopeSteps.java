package clarity.api.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sun.security.util.PendingException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CheckVariableScopeSteps
{
	int foo;
	
	@Given("^a variable with value (\\d+)$")
	public void a_variable_with_value(int value) throws Throwable {
		foo = value;
	}
	
	@When("^I read the variable$")
	public void i_read_the_variable() throws Throwable {
		System.out.println("foo: " + foo);
	}
	
	@Then("^I should get (\\d+)$")
	public void i_should_get(int value) throws Throwable {
		assertThat(foo).isEqualTo(value);
	}
	
}
