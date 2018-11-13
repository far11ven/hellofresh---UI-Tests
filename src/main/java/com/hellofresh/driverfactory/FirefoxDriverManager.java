package com.hellofresh.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {
	
	protected WebDriver driver;
	
	@Override
	public WebDriver getDriverInstance() {
		
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
		 
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
		return driver;
		
	}


    

}
