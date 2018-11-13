package com.hellofresh.driverfactory;

public class DriverManagerFactory {

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