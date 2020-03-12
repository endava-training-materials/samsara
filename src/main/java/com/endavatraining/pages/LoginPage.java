package com.endavatraining.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	private final String ENDAVATRAINING_URL = "http://localhost:9010";
	public final String WRONG_CREDENTIALS_MESSAGE = "Invalid username and password.";
	private static Logger log = Logger.getLogger(LoginPage.class);//

	private By loginButton = By.xpath("//input[@value='Log In']");
	private By userName = By.xpath("//input[@name='username']");
	private By password = By.xpath("//input[@name='password']");
	public By invalidCredentialsLabel = By.className("alert");

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	

	public void open() {
		log.info("Open Endava training web site");
		log.debug("Open Endava training web site");
		driver.get(ENDAVATRAINING_URL);
		driver.manage().window().maximize();
	}

	public By getLoginButton() {
		return loginButton;
	}

	public void clickLoginButton() {
		driver.findElement(this.loginButton).click();
	}

	public void enterUsername(String username) {
		driver.findElement(this.userName).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(this.password).sendKeys(password);
	}

	public String getErrorMessageText() {
		return driver.findElement(invalidCredentialsLabel).getText();
	}

	public HomePage openAs(String username, String password) {
		open();
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		
		return new HomePage(driver);		
	}

	public String enterWrongCredentials(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();

		return getErrorMessageText();
	}
}
