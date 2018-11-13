package com.hellofresh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(css="h1")
	@CacheLookup
	WebElement headingText ;
	
	@FindBy(className="info-account")
	@CacheLookup
	WebElement welcomeLabel ;
	
	@FindBy(className="account")
	@CacheLookup
	WebElement usernameLabel ;
	
	@FindBy(className="logout")
	@CacheLookup
	WebElement logutLabel ;
	
	@FindBy(id="SubmitLogin")
	@CacheLookup
	WebElement loginSubmit ;
	
	public HomePage(WebDriver driver) {           
        this.driver = driver; 
        PageFactory.initElements(driver, this);
	}
	
	public String getUsername() {
		
		return usernameLabel.getText();
			
	}
	
	public String getWelcomeText() {
		
		return welcomeLabel.getText();
			
	}
	

}
