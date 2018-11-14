package com.hellofresh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * This class provides instance of LoginPage and provides instantiation for all the web elements and their actions
*/
public class LoginPage {

	WebDriver driver;

	@FindBy(className="login")
	@CacheLookup
	public WebElement loginLink ;

	@FindBy(id="email_create")
	@CacheLookup
	public WebElement registerUserEmailTxtbox ;

	@FindBy(id="email")
	@CacheLookup
	public WebElement emailTxtbox ;

	@FindBy(id="passwd")
	@CacheLookup
	public WebElement passwordTxtbox ;

	@FindBy(css="div.alert.alert-danger")
	@CacheLookup
	public WebElement errorMessageBox ;
	
	@FindBy(id="SubmitCreate")
	@CacheLookup
	public WebElement createAnAccountBtn ;

	@FindBy(id="SubmitLogin")
	@CacheLookup
	public WebElement loginSubmit ;

	public LoginPage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	public void enterNewUserEmailId(String email) {

		registerUserEmailTxtbox.sendKeys(email);

	}

	public void enterEmailId(String email) {

		emailTxtbox.sendKeys(email);

	}

	public String getErrorMessageText() {

		return errorMessageBox.getText();

	}

	public void enterPassword(String pwd) {

		passwordTxtbox.sendKeys(pwd);


	}
	
	public void clickCreateAccount() {

		createAnAccountBtn.click();


	}

	public void clickLoginSubmit() {

		loginSubmit.click();


	}

}
