package com.endavatraining.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

	// UI elements - locators
	private By homePageWelcomeMessage = By.xpath("//div[contains(" +
			"text(),'Hello, and welcome to our gamers page!')]");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Check if welcome text  is present on a page
	 *
	 * @return Boolean
	 */
	public boolean isWelcomeTextPresent() {

		return isElementPresent(homePageWelcomeMessage);
	}

}
