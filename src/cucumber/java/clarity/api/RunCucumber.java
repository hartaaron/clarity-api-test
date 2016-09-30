package clarity.api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = { "pretty", "html:target/cucumber", "json:target/cucumber.json" },
		features = "src/cucumber/resources/features",
		glue = "clarity.api.steps",
		tags = {"@Login"}
)
public class RunCucumber
{

}