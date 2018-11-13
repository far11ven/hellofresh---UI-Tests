package com.hellofresh.pages;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="email")
	@CacheLookup
	WebElement emailTxtbox ;
	
	@FindBy(id="passwd")
	@CacheLookup
	WebElement passwordTxtbox ;
	
	@FindBy(id="SubmitLogin")
	@CacheLookup
	WebElement loginSubmit ;
	
	public LoginPage(WebDriver driver) {           
        this.driver = driver; 
        PageFactory.initElements(driver, this);
	}
	
	public void enterEmailId(String email) {
		
		emailTxtbox.sendKeys(email);
			
	}
	
	public void enterPassword(String pwd) {
		
		passwordTxtbox.sendKeys(pwd);
		
		
	}
	
  public void clickLoginSubmit() {
		
	  loginSubmit.click();
		
	
	}
	
}
