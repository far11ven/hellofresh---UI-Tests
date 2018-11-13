package com.hellofresh.driverfactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	
	DriverManager driverManager;
	
	protected WebDriver driver;
    public abstract WebDriver getDriverInstance();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
    
   public WebDriver getDriver(String browserType) {
    	
    	System.out.println(" browserType : " + browserType);
    	
        if (null == driver) {

        	switch (browserType.toLowerCase()) {
        		case "chrome":
        			driverManager = new ChromeDriverManager();
        			driver = driverManager.getDriverInstance();
        			break;
        					
        		case "firefox":
					driverManager = new FirefoxDriverManager();
					driver = driverManager.getDriverInstance();
					break;
        					
        	}
        }
        
        return driver;
    }
 

}
