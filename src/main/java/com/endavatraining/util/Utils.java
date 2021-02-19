package com.endavatraining.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endavatraining.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Utils {

	private static Logger log = LogManager.getLogger(Utils.class.getName());

	/**
	 *
	 * setup browsers driver
	 *
	 * @param browser - browser type (chrome or firefox)
	 * @return LoginPage
	 */
	public static LoginPage setUpWebBrowser(String browser) {
		LoginPage loginPage;

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			loginPage = new LoginPage(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			loginPage = new LoginPage(new FirefoxDriver());
		} else
			throw new RuntimeException();

		return loginPage;
	}


	/**
	 *
	 * wait for given element to become visible, max 5 sec
	 *
	 * @param driver - selenium webdriver
	 * @param locator - ui element locator
	 */
	public static void webDriverWait(WebDriver driver, By locator) {
		new WebDriverWait(driver, 5).
				until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}