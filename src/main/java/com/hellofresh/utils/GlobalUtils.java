package com.hellofresh.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/*
 * This class widely used element related reusable functions
*/
public class GlobalUtils {
	
	/*
	 * This method checks if an element is visible on page
	 * it takes arguments @driver which is current driver instance and @elem which webelement
	*/
	public boolean isElementPresent(WebDriver driver, By elem) {

		try {
			driver.findElement(elem);
			return true;

		}catch(NoSuchElementException e) {

			return false;
		}
	}
	
	
	/*
	 * This method provides instance of WebDriverWait with default timeouts from "config.properties" file
	 * it takes arguments @driver which is current driver instance
	*/
	public static WebDriverWait waitDefault(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(ConfigReader.getProperty("DEFAULT_TIMEOUT")), Integer.parseInt(ConfigReader.getProperty("DEFAULT_POLLING_TIMEOUT")));
		return wait;

	}

	/*
	 * This method provides instance of WebDriverWait with runtime timeout values
	 * it takes arguments @driver which is current driver instance, @durationInSeconds time to wait for, @pollingTimeout polling every
	*/
	public static WebDriverWait waitFor(WebDriver driver, long durationInSeconds, int pollingTimeout) {

		WebDriverWait wait = new WebDriverWait(driver, durationInSeconds, pollingTimeout);
		return wait;

	}
	
	/*
	 * This method provides wait within default timeout for an element to be visible, which is identified by it's locator
	 * it takes arguments @driver which is current driver instance, @locator is locator for element
	*/
	public static boolean waitForElementToBeVisible(WebDriver driver, By locator) {

		WebDriverWait wait = waitDefault(driver);

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)) != null;

	}
	
	/*
	 * This method provides wait within default timeout for an element to be clickable, which is identified by it's locator
	 * it takes arguments @driver which is current driver instance, @locator is locator for element
	*/
	public static boolean waitForElementToBeClickable(WebDriver driver, By locator) {

		WebDriverWait wait = waitDefault(driver);

		return wait.until(ExpectedConditions.elementToBeClickable(locator)) != null;

	}
	
	/*
	 * This method provides wait within default timeout for an element to be visible
	 * it takes arguments @driver which is current driver instance, @elem is webelement to wait for
	*/
	public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement elem) {

		WebDriverWait wait = waitFor(driver, 10, 200);

		wait.until(ExpectedConditions.visibilityOf(elem));
		
		return elem;

	}
	
	
	/*
	 * This method provides wait within default timeout for an element to be clickable
	 * it takes arguments @driver which is current driver instance, @elem is webelement to wait for
	*/
	public static WebElement waitForElementToBeClickable(WebDriver driver,  WebElement elem) {

		WebDriverWait wait = waitFor(driver, 10, 200);

		wait.until(ExpectedConditions.visibilityOf(elem));
		
		return elem;

	}

}
