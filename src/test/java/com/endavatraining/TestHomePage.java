package com.endavatraining;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endavatraining.util.Utils;
import com.endavatraining.pages.HomePage;
import com.endavatraining.pages.LoginPage;

import static org.testng.Assert.assertTrue;

public class TestHomePage {

	private static Logger log = LogManager.getLogger(TestHomePage.class.getName());

	private HomePage homePage;
	private LoginPage loginPage;

	private static final  String USERNAME = "user";
	private static final  String PASSWORD = "password";

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		loginPage = Utils.setUpWebBrowser(browser);
	}

	/*
	 * Test validates that HomePage page is available
	 */
	@Test
	public void testIsHomePageAvailable() {

		log.info("Log in to Endava training web site.");

		homePage = loginPage.openAs(USERNAME, PASSWORD);

		assertTrue(
				homePage.isWelcomeTextPresent(),
				"ERROR: Welcome text is not present"
		);

		log.info("Success: Welcome text is present on a home page.");
	}

	@AfterMethod
	public void tearDown() {
		if (loginPage != null)
			loginPage.quit();
	}
}
