package com.endavatraining.pages;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class BasePage {

	private static Logger log = LogManager.getLogger(BasePage.class.getName());

	public WebDriver driver;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Check if given element is present on a page
	 *
	 * @param by - ui element locator
	 * @return Boolean
	 */

	protected boolean isElementPresent(By by){
		try{
			driver.findElement(by);
			log.debug("Element found.");
			return true;
		}
		catch(NoSuchElementException e){
			log.debug("Element not found!");
			return false;
		}
	}

	/**
	 * Quit driver
	 *
	 */

	public void quit() {
		if (this.driver != null) {
			log.debug("Closing browser.");
			driver.quit();
		}
	}
}
