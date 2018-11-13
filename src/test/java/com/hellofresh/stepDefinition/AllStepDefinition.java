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
import com.hellofresh.utils.LogUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AllStepDefinition{

	DriverManager driverManager;
	WebDriver driver;
	LoginPage loginPage;

	WebDriverWait wait;

	String fullName = "Joe Black";

	private final LogUtils LOGGER = new LogUtils(AllStepDefinition.class);

	@Before
	public void beforeScenario(){

		driverManager = DriverManagerFactory.getManager("chrome");
		driver = driverManager.getDriver("chrome");
		wait = new WebDriverWait(driver, 10, 500);
		LOGGER.info("Driver setup is complete");

	}	

	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		
		File destinationPath  = null;

		LOGGER.info("Getting screenshot" + scenario.isFailed());
		if (scenario.isFailed()) {

			LOGGER.info("Getting screenshot if ");
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

			} catch (IOException e) {

				LOGGER.warn("Something went wrong capturing screenshot");
				e.printStackTrace();

			} 
		}
	}


	@After(order = 0)
	public void afterScenario(){
		LOGGER.info("This will run after the Scenario");
		driverManager.quitDriver();
	}

	@Given("^User Navigates to 'AutomationPractice' Website URL$")
	public void user_Navigates_to_AutomationPractice_Website_URL(){

		driver.get("http://automationpractice.com/index.php");
		LOGGER.info("Driver navigates to automationpractice.com");

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



	@Given("^Module1 environment setup$")
	public void module1_environment_setup() {
		System.out.println("Step--  module1_environment_setup");
	}

	@Given("^M1U1TC1 User navigates to home screen$")
	public void M1U1TC1_User_navigates_to_home_screen() {
		System.out.println("Step--  M1U1TC1_User_navigates_to_home_screen");
	}

	@When("^M1U1TC1 User initiates login with following credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void M1U1TC1_User_initiates_login_with_following_credentials_and(String userName, String password) {
		System.out.println("Step--  M1U1TC1_User_initiates_login_with_following_credentials_and");
	}

	@Then("^M1U1TC1 Login validation should be successful$")
	public void M1U1TC1_Login_validation_should_be_successful() {
		System.out.println("Step--  M1U1TC1_Login_validation_should_be_successful");
	}

	@Given("^M1U1TC2 Dummy step$")
	public void M1U1TC2_Dummy_step() {
		System.out.println("Step--  M1U1TC2_Dummy_step");
	}

	@Given("^M1U1TC3 Dummy step$")
	public void M1U1TC3_Dummy_step() {
		System.out.println("Step--  M1U1TC3_Dummy_step");
	}

	@Given("^M1U2TC1 Dummy step$")
	public void M1U2TC1_Dummy_step() {
		System.out.println("Step--  M1U2TC1_Dummy_step");
	}

	@Given("^M1U2TC2 Dummy step$")
	public void M1U2TC2_Dummy_step() {
		System.out.println("Step--  M1U2TC2_Dummy_step");
	}

	@Given("^M2U1TC1 Dummy step$")
	public void M2U1TC1_Dummy_step() {
		System.out.println("Step--  M2U1TC1_Dummy_step");
		Assert.fail();
	}

	@Given("^M2U1TC2 Dummy step$")
	public void M2U1TC2_Dummy_step() {
		System.out.println("Step--  M2U1TC2_Dummy_step");
	}

	@Given("^M2U2TC1 Dummy step$")
	public void M2U2TC1_Dummy_step() {
		System.out.println("Step--  M2U2TC1_Dummy_step");
	}

	@Given("^M2U2TC2 Dummy step$")
	public void M2U2TC2_Dummy_step() {
		System.out.println("Step--  M2U2TC2_Dummy_step");
	}

	@Given("^M3U1TC1 Dummy step$")
	public void M3U1TC1_Dummy_step() {
		System.out.println("Step--  M3U1TC1_Dummy_step");
	}

	@Given("^M3U1TC2 Dummy step$")
	public void M3U1TC2_Dummy_step() {
		System.out.println("Step--  M3U1TC2_Dummy_step");
		Assert.fail();
	}

	@Given("^M3U2TC1 Dummy step$")
	public void M3U2TC1_Dummy_step() {
		System.out.println("Step--  M3U2TC1_Dummy_step");
	}

	@Given("^M3U2TC2 Dummy step$")
	public void M3U2TC2_Dummy_step() {
		System.out.println("Step--  M3U2TC2_Dummy_step");
	}

	@Given("^M3U2TC3 Dummy step$")
	public void M3U2TC3_Dummy_step() {
		System.out.println("Step--  M3U2TC3_Dummy_step");
		Assert.fail();
	}






}
