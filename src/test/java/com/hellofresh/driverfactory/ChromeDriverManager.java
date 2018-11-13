package com.hellofresh.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {
	
	protected WebDriver driver;
	
	@Override
	public WebDriver getDriverInstance() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
	    options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
		return driver;
		
	}

    

}
