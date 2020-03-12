package com.endavatraining;

import com.endavatraining.pages.HeroesPage;
import com.endavatraining.pages.HomePage;
import com.endavatraining.pages.LoginPage;
import com.endavatraining.util.Utils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestHeroesPage {

    private HomePage homePage;
    private LoginPage loginPage;
    private HeroesPage heroesPage;
    private String adminUsername = "admin";
    private String userUsername = "user";
    private String password = "password";
    private static Logger log = Logger.getLogger(TestHeroesPage.class);

    @BeforeMethod
    @Parameters({ "browser" })
    public void setUp(String browser) {
        loginPage = Utils.setUpWebBrowser(browser);
    }

    /*
     * Test 3
     */
    @Test
    public void testAdminDeletesHero() {
        homePage = loginPage.openAs(adminUsername, password);
        heroesPage = homePage.clickOnHeroesPage();

        String adminsUserName = heroesPage.getNameOfFirstAdminsUser();
        heroesPage.clickOnDeleteButtonForUser(adminsUserName);
        heroesPage.clickOnCancelInModalWindow();
        heroesPage.assertHeroNamePresent(adminsUserName);

        heroesPage.clickOnDeleteButtonForUser(adminsUserName);
        heroesPage.clickOnDeleteInModalWindow();
        heroesPage.assertHeroNameNotPresent(adminsUserName);

        String usersUserName = heroesPage.getNameOfFirstUsersUser();
        heroesPage.clickOnDeleteButtonForUser(usersUserName);
        heroesPage.clickOnDeleteInModalWindow();
        heroesPage.assertHeroNameNotPresent(usersUserName);
    }

    /*
     * Test 4
     */
    @Test
    public void testUserDeletesHero() {
        homePage = loginPage.openAs(userUsername, password);
        heroesPage = homePage.clickOnHeroesPage();

        String usersUserName = heroesPage.getNameOfFirstUsersUser();
        heroesPage.clickOnDeleteButtonForUser(usersUserName);
        heroesPage.clickOnCancelInModalWindow();
        heroesPage.assertHeroNamePresent(usersUserName);

        heroesPage.clickOnDeleteButtonForUser(usersUserName);
        heroesPage.clickOnDeleteInModalWindow();
        heroesPage.assertHeroNameNotPresent(usersUserName);

        String adminsUserName = heroesPage.getNameOfFirstAdminsUser();
        heroesPage.clickOnDeleteButtonForUser(adminsUserName);
        heroesPage.assertDeleteButtonDoesNotExistInModal();
    }

    @AfterMethod
    public void tearDown() {
        if (loginPage != null)
            loginPage.quit();
    }
}
