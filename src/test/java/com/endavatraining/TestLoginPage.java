package com.endavatraining;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endavatraining.util.Utils;
import com.endavatraining.pages.LoginPage;

import static org.testng.Assert.*;

public class TestLoginPage {


	private static Logger log = LogManager.getLogger(TestLoginPage.class.getName());

	private LoginPage loginPage;

	private static final String USERNAME = "user";
	private static final String WRONG_PASS = "wrongPassword";

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		loginPage = Utils.setUpWebBrowser(browser);
	}

	/*
	 * Test validates that login page is opened
	 * by checking if log in button is visible on the page
	 */
	@Test
	public void testLoginPageIsOpened() {
		log.info("Open Endava training web site");

		loginPage.open();

		assertTrue(
				loginPage.isLoginButtonPresent(),
				"ERROR: Login Button not present on login page!"
		);

		log.info("Success: Login button is present on a Login page.");
	}

	/*
	 * Test validates that proper error message is displayed
	 * upon unsuccessful login
	 */
	@Test
	public void testUnsuccessfulLogin() {
		log.info("Open Endava training web site");

		loginPage.open();

		log.info("Try to log in with wrong credentials");
		loginPage.enterWrongCredentials(USERNAME, WRONG_PASS);

		assertEquals(
				loginPage.getInvalidCredentialsLabelText(),
				LoginPage.WRONG_CREDENTIALS_MESSAGE,
				"ERROR: Unsuccessful logging alert is not correct!"
		);
		log.info("Success: User is unable to login using wrong credentials");
	}

	@AfterMethod
	public void tearDown() {
		if (loginPage != null)
			loginPage.quit();
	}
}
