package com.hellofresh.driverfactory;

/*
 * This class provides instance of driverManager
 */

public class DriverManagerFactory {
	
	/*
	 * This method provides instance of driverManager, based-on "Browser"  value
	 */

    public static DriverManager getManager(String browser) {

        DriverManager driverManager = null;

        switch (browser.toLowerCase()) {
            case "chrome":
                driverManager = new ChromeDriverManager();
                break;
            case "firefox":
                driverManager = new FirefoxDriverManager();
                break;
        }
        
        return driverManager;

    }
}