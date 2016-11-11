import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = { "@PatientSearch" })
public class RunPatientSearchCucumberTests extends RunAllCucumberTests
{}
