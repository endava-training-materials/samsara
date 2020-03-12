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
     * Test 2
     */
    @Test
    public void testUnsuccessfulLogin() {
        log.info("Open Endava training web site");
        log.debug("Open Endava training web site");

        loginPage.open();
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(), loginPage.WRONG_CREDENTIALS_MESSAGE);

        loginPage.enterUsername("admin");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageText(), loginPage.WRONG_CREDENTIALS_MESSAGE);

		loginPage.enterPassword("password");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageText(), loginPage.WRONG_CREDENTIALS_MESSAGE);

		loginPage.enterUsername("adnim");
		loginPage.enterPassword("password");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageText(), loginPage.WRONG_CREDENTIALS_MESSAGE);
    }

	@AfterMethod
	public void tearDown() {
		if (loginPage != null)
			loginPage.quit();
	}
}
