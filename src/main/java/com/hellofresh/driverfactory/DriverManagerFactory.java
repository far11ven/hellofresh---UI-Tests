package com.hellofresh.driverfactory;

import com.hellofresh.utils.ConfigReader;

/*
 * This class provides instance of driverManager
 */

public class DriverManagerFactory {
	private static String browser;
	
	/*
	 * This method provides instance of driverManager, based-on "Browser"  value
	 */

    public static DriverManager getManager() {

        DriverManager driverManager = null;
        
        if(System.getProperty("browser")!=null) {
			browser = System.getProperty("browser").toLowerCase();
			
		} else {
			browser = ConfigReader.getProperty("BROWSER").toLowerCase();
		}

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