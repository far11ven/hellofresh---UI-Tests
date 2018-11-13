package com.hellofresh.stepDefinition;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import com.hellofresh.driverfactory.DriverManager;
import com.hellofresh.driverfactory.DriverManagerFactory;
import com.hellofresh.pages.LoginPage;
import com.hellofresh.utils.ConfigReader;
import com.hellofresh.utils.GlobalUtils;
import com.hellofresh.utils.LogUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class AllStepDefinition{

	DriverManager driverManager;
	WebDriver driver;
	LoginPage loginPage;

	WebDriverWait wait;

	String fullName = "Joe Black";

	private static final LogUtils LOGGER = new LogUtils(AllStepDefinition.class);
	private static final ConfigReader configReader = new ConfigReader(AllStepDefinition.class);

	@Before
	public void beforeScenario(Scenario scenario){
		
		LOGGER.info("Starting Test : " + scenario.getName());

		driverManager = DriverManagerFactory.getManager("chrome");
		driver = driverManager.getDriver("chrome");
		wait = GlobalUtils.waitFor(driver, 10, 500);
		
		LOGGER.info("Driver setup is complete");

	}	
	
	/* This method runs just before the afterScenario() 
	 * and attaches failure screenshot to extent Report
	*/
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		
		File destinationPath  = null;
		if (scenario.isFailed()) {

			String screenshotName = scenario.getName().replaceAll(" ", "_") ;
			String date = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());			
			screenshotName = screenshotName + "-" + date.replaceAll(":", "_");

			try {
				//This takes a screenshot from the driver at save it to the specified location
				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				//Building up the destination path for the screenshot to save
				boolean screenshotDirectory = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/").mkdirs();

				if(screenshotDirectory){
					
					LOGGER.info("Creating new Screenshot directory..");

				}
				
				destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");

				//Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath);   

				//This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
				
				LOGGER.info("Screenshot captured");

			} catch (IOException e) {

				LOGGER.warn("Something went wrong capturing screenshot");
				e.printStackTrace();

			} 
		}
	}

	/* This method runs at the last of a test case run and closes current driver instance
	 * 
	*/
	@After(order = 0)
	public void afterScenario(){
		LOGGER.info("Closing driver instance \n");
		driverManager.quitDriver();
	}

	@SuppressWarnings("static-access")
	@Given("^User Navigates to 'AutomationPractice' Website URL$")
	public void user_Navigates_to_AutomationPractice_Website_URL(){

		LOGGER.info("Driver navigates to " + configReader.getProperty("URL"));
		driver.get(configReader.getProperty("URL"));

	}

	@Given("^User clicks on 'SignIn' link from header$")
	public void user_clicks_on_sign_in_link(){
		loginPage = PageFactory.initElements(driver, LoginPage.class);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		LOGGER.info("Clicked on Login Link");

	}



	@When("^User provides valid credentials for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void usser_initiates_login_with_valid_credentials(String userName, String password) {
		loginPage.enterEmailId(userName);
		loginPage.enterPassword(password);

		LOGGER.info("Entered user Credentials");

	}

	@Given("^User clicks on 'SignIn' button$")
	public void user_clicks_on_sign_in(){

		loginPage.clickLoginSubmit();
		LOGGER.info("Clicked on Login Button");

	}

	@Given("^Verify user is loggedIn$")
	public void verify_user_is_logged_in(){


		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

		assertEquals("MY ACCOUNT", heading.getText());
		assertEquals(fullName, driver.findElement(By.className("account")).getText());
		assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
		assertTrue(driver.findElement(By.className("logout")).isDisplayed());
		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-accountx"));

	}






}
