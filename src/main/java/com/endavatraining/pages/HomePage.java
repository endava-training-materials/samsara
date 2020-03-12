package com.endavatraining.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.endavatraining.pages.HomePage;

public class HomePage extends BasePage {

	private static Logger log = Logger.getLogger(HomePage.class);//
	private By homePageWelcomeMessage = By.xpath("//div[@class='panel-title text-center'][contains(.,'Hello, and welcome to our gamers page!')]");
	private By startTestingButton = By.xpath("//*[contains(text(),'Start Testing!')]");
	private By shareWithFriendsButton = By.xpath("//*[contains(text(),'Share with friends!')]");
	private By heroesTab = By.xpath("//a[@href='/heroes']");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean isWelcomeTextPresent() {
		return isElementPresent(homePageWelcomeMessage);
	}

	public boolean isStartTestingButtonPresent() {
		return isElementPresent(startTestingButton);
	}

	public boolean isShareWithFriendsButtonPresent() {
		return isElementPresent(shareWithFriendsButton);
	}

	public HeroesPage clickOnHeroesPage() {
		driver.findElement(heroesTab).click();

		return new HeroesPage(driver);
	}
}
