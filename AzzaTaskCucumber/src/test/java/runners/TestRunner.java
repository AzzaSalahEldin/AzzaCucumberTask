package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",tags = "@Regression" ,
monochrome = true ,glue = "steps",plugin = {"pretty","html:target/reports/report.html","json:target/reports/report.json"})
public class TestRunner {	
}
