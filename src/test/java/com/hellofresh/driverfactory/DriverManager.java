package com.hellofresh.driverfactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	
	DriverManager driverManager;
	
	protected WebDriver driver;
    protected abstract WebDriver createDriverInstance(String browserType);

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
    
    public WebDriver getDriver(String browserType) {
    	
    	System.out.println(" browserType : " + browserType);
    	
        if (null == driver) {
        	System.out.println(" Inside If : " + browserType);
        	switch (browserType) {
        	
        		case "CHROME":
        					driverManager = new ChromeDriverManager();
        					driver = driverManager.createDriverInstance(browserType);
        					break;
        					
        	}
        }
        
        return driver;
    }

}
