package com.hellofresh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/*
 * This class provides instance of RegistrationPage and provides instantiation for all the web elements and their actions
*/
public class RegistrationPage {

	WebDriver driver;

	@FindBy(xpath="//h1[contains(.,'Create an account')]")
	@CacheLookup
	public WebElement pageHeading;

	@FindBy(id="id_gender1")
	@CacheLookup
	public WebElement maleUserTitleRadio;

	@FindBy(id="id_gender2")
	@CacheLookup
	public WebElement femaleUserTitleRadio;

	@FindBy(id="customer_firstname")
	@CacheLookup
	public WebElement userFirstNameTxtbox;

	@FindBy(id="customer_lastname")
	@CacheLookup
	public WebElement userLastNameTxtbox;

	@FindBy(id="passwd")
	@CacheLookup
	public WebElement userPasswordTxtbox;

	@FindBy(id="days")
	@CacheLookup
	public WebElement userBirthDayDDN;

	@FindBy(id="months")
	@CacheLookup
	public WebElement userBirthMonthDDN;

	@FindBy(id="years")
	@CacheLookup
	public WebElement userBirthYearDDN;

	@FindBy(id="company")
	@CacheLookup
	public WebElement userCompanyTxtbox;

	@FindBy(id="address1")
	@CacheLookup
	public WebElement userAddress1Txtbox;

	@FindBy(id="address2")
	@CacheLookup
	public WebElement userAddress2Txtbox;

	@FindBy(id="city")
	@CacheLookup
	public WebElement userCityTxtbox;

	@FindBy(id="id_state")
	@CacheLookup
	public WebElement userStateDDN;

	@FindBy(id="postcode")
	@CacheLookup
	public WebElement userPostCodeTxtbox;

	@FindBy(id="other")
	@CacheLookup
	public WebElement userAdditionalInfoTxtbox;

	@FindBy(id="phone")
	@CacheLookup
	public WebElement userHomePhoneTxtbox;

	@FindBy(id="phone_mobile")
	@CacheLookup
	public WebElement userMobilePhoneTxtbox;

	@FindBy(id="alias")
	@CacheLookup
	public WebElement userAddressAliasTxtbox;

	@FindBy(id="submitAccount")
	@CacheLookup
	public WebElement registerButton;


	public RegistrationPage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	public String getPageHeading() {

		return pageHeading.getText();

	}

	public void selectUserTitle(String gender) {

		if(gender.toLowerCase().equals("male")) {

			maleUserTitleRadio.click();
		} else {

			femaleUserTitleRadio.click();
		}
	}

	public void enterUserFirstName(String nameValue) {
		userFirstNameTxtbox.clear();
		userFirstNameTxtbox.sendKeys(nameValue);
	}

	public void enterUserLastName(String nameValue) {
		userLastNameTxtbox.clear();
		userLastNameTxtbox.sendKeys(nameValue);
	}

	public void enterUserPassword(String pwdValue) {
		userPasswordTxtbox.clear();
		userPasswordTxtbox.sendKeys(pwdValue);
	}

	public void selectUserBirthDay(String value) {

		Select dropdown = new Select(userBirthDayDDN);
		dropdown.selectByValue(value);
	}

	public void selectUserBirthMonth(String value) {

		Select dropdown = new Select(userBirthMonthDDN);
		dropdown.selectByValue(value);
	}

	public void selectUserBirthYear(String value) {

		Select dropdown = new Select(userBirthYearDDN);
		dropdown.selectByValue(value);
	}
	
	public void enterUserCompanyName(String nameValue) {

		userCompanyTxtbox.sendKeys(nameValue);
	}

	public void enterUserAddress1Name(String value) {

		userAddress1Txtbox.sendKeys(value);
	}

	public void enterUserAddress2Name(String value) {

		userAddress2Txtbox.sendKeys(value);
	}
	
	public void enterUserCityName(String value) {

		userCityTxtbox.sendKeys(value);
	}
	
	public void selectUserState(String value) {

		Select dropdown = new Select(userStateDDN);
		dropdown.selectByVisibleText(value);
	}
	public void enterUserPostCode(String value) {

		userPostCodeTxtbox.sendKeys(value);
	}

	public void enterUserAdditionalInfo(String value) {

		userAdditionalInfoTxtbox.sendKeys(value);
	}
	
	public void enterUserHomeNo(String value) {

		userHomePhoneTxtbox.sendKeys(value);
	}
	
	public void enterUserMobileNo(String value) {

		userMobilePhoneTxtbox.sendKeys(value);
	}
	
	public void enterUserAddressAlias(String value) {
		userAddressAliasTxtbox.clear();
		userAddressAliasTxtbox.sendKeys(value);
	}
	
	public void clickRegisterButton() {

		registerButton.click();
	}





}
