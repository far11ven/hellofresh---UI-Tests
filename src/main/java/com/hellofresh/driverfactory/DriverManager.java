package com.hellofresh.driverfactory;

import org.openqa.selenium.WebDriver;

import com.hellofresh.utils.LogUtils;

public abstract class DriverManager {
	
	private static final LogUtils LOGGER = new LogUtils(DriverManager.class);
	
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
    	
	   LOGGER.info("browser : " + browserType);
    	
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
