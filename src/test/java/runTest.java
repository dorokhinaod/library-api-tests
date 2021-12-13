//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//@CucumberOptions(
//        plugin = {"json:target/jsonReports/CucumberTestReport.json"},
//        features = {"features"},
//        glue = {"TestObjects"}
//)
//
//public class runTest extends AbstractTestNGCucumberTests {
//
//}


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/jsonReports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"features"},
        glue = {"TestObjects"},
        stepNotifications = true)

public class runTest {

}
