import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = { "pretty", "html:target/cucumber" },
		features = "src/cucumber/features",
		glue = "clarity.api.step_definitions",
		tags = {"@Login"}
)
public class RunCucumber
{

}