package com.hellofresh.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.File;

import org.junit.AfterClass;

import com.cucumber.listener.Reporter;

/*
 * This class provides TestRunner for Failed features
*/
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "@target/rerun.txt",              //Cucumber picks the failed scenarios from this file 
		glue = {"com.hellofresh.stepDefinition"},
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html", "rerun:target/rerun.txt"},
		tags = {"@all"},
		dryRun = false,
		monochrome = true
		
		)

public class FailedTestsRunner {
	
	
	 @AfterClass
	    public static void reporterSetup() {
		 
	        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));

	        Reporter.setSystemInfo("Test User", "Tester1");
	        Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
	        Reporter.setSystemInfo("Web App Name", "AutomationPractice.com Re-Run");
	        Reporter.setSystemInfo("Build version", "v 1.0");
	        Reporter.setTestRunnerOutput("Cucumber reporting using Extent Config");
	    }
	 
}