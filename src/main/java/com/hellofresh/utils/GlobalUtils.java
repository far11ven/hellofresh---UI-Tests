package com.hellofresh.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GlobalUtils {

	public boolean isElementPresent(WebDriver driver, By elem) {

		try {
			driver.findElement(elem);
			return true;

		}catch(NoSuchElementException e) {

			return false;
		}
	}
	
	
	public static WebDriverWait defaultWait(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(ConfigReader.getProperty("DEFAULT_TIMEOUT")), Integer.parseInt(ConfigReader.getProperty("DEFAULT_POLLING_TIMEOUT")));
		return wait;

	}


	public static WebDriverWait waitFor(WebDriver driver, long durationInSeconds, int pollingTimeout) {

		WebDriverWait wait = new WebDriverWait(driver, durationInSeconds, pollingTimeout);
		return wait;

	}

	public static boolean waitForElementToBeVisible(WebDriver driver, By locator) {

		WebDriverWait wait = waitFor(driver, 10, 200);

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)) != null;

	}

	public static boolean waitForElementToBeClickable(WebDriver driver, By locator) {

		WebDriverWait wait = waitFor(driver, 10, 200);

		return wait.until(ExpectedConditions.elementToBeClickable(locator)) != null;

	}

	public static boolean waitForElementToBeVisible(WebDriver driver, WebElement elem) {

		WebDriverWait wait = waitFor(driver, 10, 200);

		return wait.until(ExpectedConditions.visibilityOf(elem)) != null;

	}

	public static boolean waitForElementToBeClickable(WebDriver driver,  WebElement elem) {

		WebDriverWait wait = waitFor(driver, 10, 200);

		return wait.until(ExpectedConditions.elementToBeClickable(elem)) != null;

	}

}
