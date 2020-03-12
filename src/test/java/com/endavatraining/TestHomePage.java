package com.endavatraining;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endavatraining.util.Utils;
import com.endavatraining.pages.HomePage;
import com.endavatraining.pages.LoginPage;

public class TestHomePage {

	private HomePage homePage;
	private LoginPage loginPage;
	private String username = "admin";
	private String password = "password";
	private static Logger log = Logger.getLogger(TestHomePage.class);

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		loginPage = Utils.setUpWebBrowser(browser);
	}
	
	/*
	 * Test 1
	 */
	@Test
	public void testIsHomePageAvailable() {
		homePage = loginPage.openAs(username, password);
		assert homePage.isWelcomeTextPresent() : "Welcome text is not present";
		assert homePage.isStartTestingButtonPresent() : "'Start testing' button is not present";
		assert homePage.isShareWithFriendsButtonPresent() : "'Share with friends' button is not present";
	}

	@AfterMethod
	public void tearDown() {
		if (loginPage != null)
			loginPage.quit();
	}
}
