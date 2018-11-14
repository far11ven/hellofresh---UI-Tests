package com.hellofresh.driverfactory;

import org.openqa.selenium.WebDriver;

import com.hellofresh.utils.ConfigReader;
import com.hellofresh.utils.LogUtils;

/*
 * This class provides instance of WebDriver
 */
public abstract class DriverManager {

	private static final LogUtils LOGGER = new LogUtils(DriverManager.class);

	DriverManager driverManager;

	private static String browserType;
	protected WebDriver driver;
	public abstract WebDriver getDriverInstance();

	/*
	 * This method closes the active driver instance
	 */
	public void quitDriver() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}
	}

	/*
	 * This method provides instance of a driver based on BrowserType
	 */
	public WebDriver getDriver() {

		if(System.getProperty("browser")!=null) {
			browserType = System.getProperty("browser").toLowerCase();

		} else {
			browserType = ConfigReader.getProperty("BROWSER").toLowerCase();
		}

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
