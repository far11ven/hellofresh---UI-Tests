package com.hellofresh.testRunner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"com.hellofresh.stepDefinition"},
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html", "rerun:target/rerun.txt"},
		tags = {"@all"},
		dryRun = false,
		monochrome = true
)

public class CucumberTestRunnerReporter {
	
	 @AfterClass
	    public static void reporterSetup() {
		 
	        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));

	        Reporter.setSystemInfo("Test User", "Tester1");
	        Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
	        Reporter.setSystemInfo("Web App Name", "AutomationPractice.com");
	        Reporter.setSystemInfo("Build version", "v 1.0");
	        Reporter.setTestRunnerOutput("Cucumber reporting using Extent Config");
	    }
	 
}
