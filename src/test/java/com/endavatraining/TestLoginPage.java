package com.endavatraining;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endavatraining.util.Utils;
import com.endavatraining.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestLoginPage {

	private LoginPage loginPage;
	private static Logger log = Logger.getLogger(TestLoginPage.class);//

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		loginPage = Utils.setUpWebBrowser(browser);
	}
	
	/*
	 * Test validates that login page is opened by checking if log in button is
	 * visible on the page
	 */
	@Test
	public void testLoginPageIsOpened() {
		log.info("Open Endava training web site");
		log.debug("Open Endava training web site");

		loginPage.open();
		new WebDriverWait(loginPage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButton()));
	}

    /*
     * Test validates that proper error message is displayed upon unsuccessful login
     */
    @Test
    public void testUnsuccessfulLogin() {
        log.info("Open Endava training web site");
        log.debug("Open Endava training web site");

        loginPage.open();
        String error_label = loginPage.enterWrongCredentials("user", "wrongPassword");
        Assert.assertEquals(error_label, loginPage.WRONG_CREDENTIALS_MESSAGE);
    }

	@AfterMethod
	public void tearDown() {
		if (loginPage != null)
			loginPage.quit();
	}
}
