package com.hellofresh.stepDefinition;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import com.hellofresh.driverfactory.DriverManager;
import com.hellofresh.driverfactory.DriverManagerFactory;
import com.hellofresh.pages.CheckoutPage;
import com.hellofresh.pages.HomePage;
import com.hellofresh.pages.LoginPage;
import com.hellofresh.pages.ProductPage;
import com.hellofresh.pages.RegistrationPage;
import com.hellofresh.utils.ConfigReader;
import com.hellofresh.utils.ExcelOperations;
import com.hellofresh.utils.GlobalUtils;
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
	RegistrationPage registrationPage;
	LoginPage loginPage;
	HomePage homePage;
	ProductPage productPage;
	CheckoutPage checkoutPage;

	String currScenarioName;
	String userFirstName;
	String userLastName;

	String productName;
	String productPrice;
	String productQty;


	WebDriverWait wait;

	private static final LogUtils LOGGER = new LogUtils(AllStepDefinition.class);
	private static final ConfigReader configReader = new ConfigReader(AllStepDefinition.class);

	@Before
	public void beforeScenario(Scenario scenario){

		currScenarioName = scenario.getName();

		LOGGER.info("Starting Test : " + currScenarioName);

		driverManager = DriverManagerFactory.getManager("chrome");
		driver = driverManager.getDriver("chrome");
		wait = GlobalUtils.waitDefault(driver);

		LOGGER.info("Driver setup is complete");

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

		// initializing LoginPage elements by creating LoginPage class object
		loginPage = new LoginPage(driver);

		GlobalUtils.waitForElementToBeClickable(driver, loginPage.loginLink).click();
		LOGGER.info("Clicked on Login Link");

	}

	@When("^User provides valid emailId for account creation$")
	public void user_initiates_registration_with_valid_email() {

		String timestamp = String.valueOf(new Date().getTime());
		String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";

		loginPage.enterNewUserEmailId(email);

		LOGGER.info("Entered randomly created user emailId");

	}

	@Then("^User clicks on 'Create An Account' button$")
	public void user_clicks_on_create_an_account_link(){

		loginPage.clickCreateAccount();
		LOGGER.info("Clicked on 'Create An Account' button");

	}

	@Then("^Verify user is present on \"Registartion\" page$")
	public void verify_user_is_present_on_registration_page(){

		registrationPage = new RegistrationPage(driver);

		GlobalUtils.waitForElementToBeVisible(driver, registrationPage.pageHeading);

		Assert.assertEquals("CREATE AN ACCOUNT", registrationPage.getPageHeading());

		LOGGER.info("User is navigated to Registration page");
	}


	@When("^User provides valid credentials for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_initiates_login_with_valid_credentials(String userName, String password) {

		loginPage.enterEmailId(userName);
		loginPage.enterPassword(password);

		LOGGER.info("Entered user Credentials");

	}

	@Given("^User clicks on 'SignIn' button$")
	public void user_clicks_on_sign_in(){

		loginPage.clickLoginSubmit();
		LOGGER.info("Clicked on Login Button");

	}

	@When("^User fills Registration form with 'RegistrationTestData' details$")
	public void user_fills_registration_details(){

		String testCaseName = currScenarioName.split("-")[0];
		//String testCaseName = "TC4";

		Dictionary <String, String> userData  = ExcelOperations.readRecordFromExcel("Sheet1", testCaseName);

		registrationPage.selectUserTitle(userData.get("Gender"));

		userFirstName = userData.get("FirstName");
		userLastName = userData.get("LastName");

		registrationPage.enterUserFirstName(userData.get("FirstName"));
		registrationPage.enterUserLastName(userData.get("LastName"));

		registrationPage.enterUserPassword(userData.get("Password"));

		registrationPage.selectUserBirthDay(userData.get("BirthDay"));
		registrationPage.selectUserBirthMonth(userData.get("BirthMonth"));
		registrationPage.selectUserBirthYear(userData.get("BirthYear"));

		registrationPage.enterUserCompanyName(userData.get("Company"));
		registrationPage.enterUserAddress1Name(userData.get("Address1"));
		registrationPage.enterUserAddress2Name(userData.get("Address2"));
		registrationPage.enterUserCityName(userData.get("City"));
		registrationPage.selectUserState(userData.get("State"));

		registrationPage.enterUserPostCode(userData.get("PostalCode"));
		registrationPage.enterUserAdditionalInfo(userData.get("AdditionalInfo"));
		registrationPage.enterUserHomeNo(userData.get("HomePhone"));
		registrationPage.enterUserMobileNo(userData.get("MobilePhone"));

		registrationPage.enterUserAddressAlias(userData.get("AddressAlias"));


		registrationPage.clickRegisterButton();

		LOGGER.info("User entered registration details");

	}

	@Then("^Verify user was created successfully$")
	public void verify_user_created_succefully(){

		// initializing HomePage elements by creating HomePage class object
		homePage = new HomePage(driver);

		WebElement heading = GlobalUtils.waitForElementToBeVisible(driver, homePage.headingText);

		Assert.assertEquals("MY ACCOUNT", heading.getText());
		Assert.assertEquals(homePage.getUserFullname(), userFirstName + " " + userLastName);
		Assert.assertTrue(homePage.getWelcomeText().contains("Welcome to your account"));
		Assert.assertTrue(homePage.logutLink.isDisplayed());
		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));

	}



	@Then("^Verify user is shown error message$")
	public void verify_user_is_shown_error_message(){

		GlobalUtils.waitForElementToBeClickable(driver, loginPage.errorMessageBox);
		LOGGER.info("User is shown error message as :" + loginPage.getErrorMessageText() );

	}

	@Then("^Verify user is loggedIn as \"([^\"]*)\"$")
	public void verify_user_is_logged_in_as(String fullName){

		// initializing HomePage elements by creating HomePage class object
		homePage = new HomePage(driver);


		WebElement heading = GlobalUtils.waitForElementToBeVisible(driver, homePage.headingText);

		Assert.assertEquals("MY ACCOUNT", heading.getText());
		Assert.assertEquals(fullName, homePage.getUserFullname());
		Assert.assertTrue(homePage.getWelcomeText().contains("Welcome to your account"));
		Assert.assertTrue(homePage.logutLink.isDisplayed());

		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));

	}

	@Then("^Verify user is not loggedIn as \"([^\"]*)\"$")
	public void verify_user_is_not_logged_in_as(String fullName){

		// initializing HomePage elements by creating HomePage class object
		homePage = new HomePage(driver);


		WebElement heading = GlobalUtils.waitForElementToBeVisible(driver, homePage.headingText);

		Assert.assertEquals("MY ACCOUNT", heading.getText());
		Assert.assertTrue(homePage.getWelcomeText().contains("Welcome to your account"));
		Assert.assertTrue(homePage.logutLink.isDisplayed());

		//this assertion verifies that username doesnt match the wrong data provided in story file
		Assert.assertNotEquals(fullName, homePage.getUserFullname());

		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));



	}

	/* This method waits for Women's link to clickable and then clicks the link
	 */
	@When("^User clicks on 'Women' section link$")
	public void user_clicks_womens_section_link(){

		GlobalUtils.waitForElementToBeClickable(driver, homePage.womenPageLink);
		homePage.clickWomensSectionLink();
		LOGGER.info("User clicks on Women's section link");

	}

	/* This method selects a women's product and then adds it to cart
	 */
	@When("^User selects a product and adds it to cart$")
	public void user_selects_a_product_and_adds_to_cart(){

		// initializing ProductPage elements by creating HomePage class object
		productPage = new ProductPage(driver);
		
		//verify user is on Women's page
		Assert.assertTrue("Title does-not match", driver.getTitle().contains("Women"));

		GlobalUtils.waitForElementToBeClickable(driver, productPage.tShirtslink);
		productPage.clickWomensTshirtsLink();
		productPage.clickFadedTshirtsLink();

		//Storing product details, which will be verified during checkout process
		productName = productPage.getProductName();
		productPrice = productPage.getProductPrice();
		productQty = productPage.getProductSelectedQty();

		//Adding product to cart
		productPage.clickAddToCart();
		LOGGER.info("User selected a women's tshirt and added to cart") ;

	}

	/* This method verifies that product has been added to cart by verifying successful message on pop-pup
	 */
	@Then("^Verify product has been added to cart$")
	public void verify_product_has_been_added_to_cart(){

		GlobalUtils.waitForElementToBeClickable(driver, productPage.productAddedToCartMessage);
		productPage.getProductAddedToCartText();
		LOGGER.info("Product added to cart - Verification complete") ;

	}

	/* This method clicks on proceed to checkout button on Pop-up
	 */
	@When("^User clicks on 'Proced To Checkout'$")
	public void user_clicks_on_proceed_to_checkout(){

		GlobalUtils.waitForElementToBeClickable(driver, productPage.proceedToCheckout);
		productPage.clickProceedToCheckout();
		LOGGER.info("Proceeded to checkout") ;

	}

	/* This method verifies that product details on checkout page with product page
	 */
	@Then("^Verify cart product details and proceed$")
	public void verify_cart_product_details(){

		// initializing ProductPage elements by creating HomePage class object
		checkoutPage = new CheckoutPage(driver);

		GlobalUtils.waitForElementToBeClickable(driver, checkoutPage.currentStepTitle);

		//verify user is on product details page
		Assert.assertTrue("Title does-not match", checkoutPage.getCurrentCheckoutStepTitle().contains("Summary"));

		//verify selected product details with the actual product
		Assert.assertEquals("Product Name do-not match", checkoutPage.getAddedProductName(), productName);
		Assert.assertEquals("Product Price do-not match", checkoutPage.getAddedProductPrice(), productPrice);
		Assert.assertEquals("Product Qty do-not match", checkoutPage.getAddedProductQty(), productQty);

		LOGGER.info("Product Details in cartpage -  Verification complete") ;

		checkoutPage.clickProceedToCheckout();

	}

	/* This method verifies that Billing and Delivery address are present and proceeds to next section
	 */
	@Then("^Verify address and proceed$")
	public void verify_address_and_proceed(){

		GlobalUtils.waitForElementToBeClickable(driver, checkoutPage.currentStepTitle);

		//verify user is on address selection page
		Assert.assertTrue("Title does-not match", checkoutPage.getCurrentCheckoutStepTitle().contains("Address"));

		//verify address details are present
		Assert.assertTrue("Delivery address not found", checkoutPage.deliveryAddress.isDisplayed());
		Assert.assertTrue("Billing address not found", checkoutPage.billingAddress.isDisplayed());

		LOGGER.info("Address details Verification complete") ;

		checkoutPage.clickProceedToCheckoutAfterAddressSelection();

	}

	/* This method verifies that shipping options are present and proceeds to next section
	 */
	@Then("^Verify shipping and proceed$")
	public void verify_shipping_and_proceed(){

		GlobalUtils.waitForElementToBeClickable(driver, checkoutPage.chooseShippingAddressLabel);

		//verify user is on shipping method selection page
		Assert.assertTrue("Title does-not match", checkoutPage.getCurrentCheckoutStepTitle().contains("Shipping"));

		checkoutPage.clickTnC();

		LOGGER.info("Shipping selection complete") ;

		checkoutPage.clickProceedToCheckoutAfterCarrierSelection();

	}

	/* This method selects user payment method
	 */
	@When("^User selects 'Bankwire' payment method$")
	public void user_selects_payment_method(){

		GlobalUtils.waitForElementToBeClickable(driver, checkoutPage.bankwirePaymentMode);

		//verify selected product details with the actual product
		Assert.assertEquals("Product Name do-not match", checkoutPage.getPaymentPageProductName(), productName);
		Assert.assertEquals("Product Price do-not match", checkoutPage.getPaymentPageProductPrice(), productPrice);
		
		Assert.assertEquals("Product Qty do-not match", checkoutPage.getPaymentPageProductQty(), productQty);
		checkoutPage.clickBankwirePaymentMode();

		LOGGER.info("User selected 'Bankwire' payemnt method") ;

	}

	/* This method selects user confirmation of the order
	 */
	@When("^User confirms the order$")
	public void user_confirms_the_order(){

		GlobalUtils.waitForElementToBeClickable(driver, checkoutPage.confirmOrderBtn);
		checkoutPage.clickConfirmOrder();

		LOGGER.info("User confirms the order") ;

	}

	/* This method verifies order confirmation details
	 */
	@Then("^Verify order completion details$")
	public void verify_order_completion_details(){

		GlobalUtils.waitForElementToBeClickable(driver, checkoutPage.orderConfirmationHeader);

		//verify user is on shipping method selection page
		Assert.assertTrue("Title does-not match", checkoutPage.getCurrentCheckoutStepTitle().contains("Payment"));

		//verify user is on ORDER CONFIRMATION section
		Assert.assertEquals("Text do-not match", checkoutPage.getConfirmOrderHeaderText(), "ORDER CONFIRMATION");

		Assert.assertTrue("Shipping Step is not present as fourth step", checkoutPage.shippingStepTitle.isDisplayed());
		Assert.assertTrue("Payment Step is not present as current step", checkoutPage.paymentStepTitle.isDisplayed());


		//verify user is on ORDER CONFIRMATION message label
		Assert.assertEquals("Order completion confirmation message does-not match", checkoutPage.getOrderCompletionLabelText(), "Your order on My Store is complete.");

		//verify current URL
		Assert.assertTrue("Order confirmation page - URL Addres does-not match", driver.getCurrentUrl().contains("controller=order-confirmation"));



		LOGGER.info("Order completion details verified") ;


	}





	/* This method runs just before the afterScenario() 
	 * and attaches failure screenshot (if any) to extent Report
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

				LOGGER.info("FAILURE!! Screenshot captured");

			} catch (IOException e) {

				LOGGER.warn("Something went wrong capturing screenshot");
				e.printStackTrace();

			} 
		}
	}






}
