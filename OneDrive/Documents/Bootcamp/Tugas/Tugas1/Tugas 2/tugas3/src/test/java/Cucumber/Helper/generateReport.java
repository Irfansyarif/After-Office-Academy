package Cucumber.Helper;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.Collections;

public class generateReport {
    public static void GenerateReport() {
        File reportOutputDirectory = new File("target/cucumber-reports");
        File jsonFile = new File("target/cucumber.json");

        Configuration config = new Configuration(reportOutputDirectory, "CucumberProject");
        config.addClassifications("Platform", "Backend");
        config.addClassifications("Type", "Rest");

        ReportBuilder reportBuilder = new ReportBuilder(
                Collections.singletonList(jsonFile.getAbsolutePath()), config);
        reportBuilder.generateReports();
    }
}