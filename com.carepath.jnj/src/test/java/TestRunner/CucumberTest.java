package TestRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import org.junit.AfterClass;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features/Practice/Verify_text.feature",
		glue={"StepDefinitions"},
		plugin = {"pretty",	
				"junit:target/cucumber-reports/reports.xml",
				"json:target/cucumber-reports/reports.json",
	            "html:target/cucumber-reports/reports.html"	
			},
//		dryRun = true,
		monochrome = true
		)

public class CucumberTest{

	@AfterClass

    public static void generateReport() {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/reports.json");
        Configuration configuration = new Configuration(reportOutputDirectory, "PCx-One Program");
        configuration.setBuildNumber("1");
        configuration.addClassifications("Platform", "Web");
        // configuration.setParallelTesting(false);

        configuration.setTrendsStatsFile(new File("target/test-classes/stats.json"));
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();

    }
	
}