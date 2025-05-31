package Cucumber.Runners;

import org.testng.annotations.AfterSuite;

import Cucumber.Helper.generateReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources",
    glue = "Cucumber.Definitions",
    plugin = {"pretty", "json:target/cucumber.json"},
    monochrome = true
)
public class cucumberRunner extends AbstractTestNGCucumberTests {
    @AfterSuite
    public void after_suite() {
        generateReport.GenerateReport();
        System.out.println("adjkhiuqehfblqefbeqkfb");
    }
}