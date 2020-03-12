package com.endavatraining.pages;

import com.endavatraining.util.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HeroesPage extends BasePage {

    private static Logger log = Logger.getLogger(HeroesPage.class);
    private By nameOfFirstAdminsUser = By.xpath("//span[text()='admin']/ancestor::tr//td[1]");
    private By nameOfFirstUsersUser = By.xpath("//span[text()='user']/ancestor::tr//td[1]");
    private By modalDialog = By.className("modal-content");
    private By cancelButtonModal = By.xpath("//button[text()='Cancel']");
    private By deleteButtonModal = By.xpath("//button[text()='Delete']");

    public HeroesPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnDeleteButtonForUser(String name) {
        driver.findElement(By.xpath("//td[text()='"+name+"']/ancestor::tr//a[3]")).click();
    }

    public String getNameOfFirstAdminsUser() {
        Utils.webDriverWait(driver, nameOfFirstAdminsUser);
        return driver.findElement(nameOfFirstAdminsUser).getText();
    }

    public String getNameOfFirstUsersUser() {
        Utils.webDriverWait(driver, nameOfFirstUsersUser);
        return driver.findElement(nameOfFirstUsersUser).getText();
    }

    public void clickOnCancelInModalWindow() {
        Utils.webDriverWait(driver, modalDialog);
        driver.findElement(cancelButtonModal).click();
    }

    public void clickOnDeleteInModalWindow() {
        Utils.webDriverWait(driver, modalDialog);
        driver.findElement(deleteButtonModal).click();
    }

    public void assertHeroNamePresent(String name) {
        Assert.assertEquals(driver.findElements(By.xpath("//td[text()='"+name+"']")).size(), 1);
    }

    public void assertHeroNameNotPresent(String name) {
        Assert.assertEquals(driver.findElements(By.xpath("//td[text()='"+name+"']")).size(), 0);
    }

    public void assertDeleteButtonDoesNotExistInModal() {
        Utils.webDriverWait(driver, modalDialog);
        Assert.assertEquals(driver.findElements(deleteButtonModal).size(), 0);
    }
}
