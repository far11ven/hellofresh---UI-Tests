package com.hellofresh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/*
 * This class provides instance of HomePage and provides instantiation for all the web elements and their actions
*/
public class HomePage {

	WebDriver driver;

	@FindBy(css="h1")
	@CacheLookup
	public WebElement headingText ;

	@FindBy(className="info-account")
	@CacheLookup
	public WebElement welcomeLabel ;

	@FindBy(className="account")
	@CacheLookup
	public WebElement userFullNameLabel ;

	@FindBy(className="logout")
	@CacheLookup
	public WebElement logutLink ;

	@FindBy(id="SubmitLogin")
	@CacheLookup
	public WebElement loginSubmit ;

	@FindBy(linkText="Women")
	@CacheLookup
	public WebElement womenPageLink ;
	

	public HomePage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	public String getHeadingText() {

		return headingText.getText();

	}

	public String getUserFullname() {

		return userFullNameLabel.getText();

	}

	public String getWelcomeText() {

		return welcomeLabel.getText();

	}

	public void clickWomensSectionLink() {

		womenPageLink.click();

	}

	

}
