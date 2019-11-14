package com.endavatraining.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endavatraining.pages.HomePage;

public class LoginPage extends BasePage {

	private final String ENDAVATRAINING_URL = "http://localhost:9010";
	public final String WRONG_CREDENTIALS_MESSAGE = "Invalid username and password";
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

	public HomePage openAs(String username, String password){
		open();
		driver.findElement(this.userName).sendKeys(username);
		driver.findElement(this.password).sendKeys(password);
		
		driver.findElement(this.loginButton).click();
		
		return new HomePage(driver);		
	}

	public String enterWrongCredentials(String username, String password) {
		driver.findElement(this.userName).sendKeys(username);
		driver.findElement(this.password).sendKeys(password);

		driver.findElement(this.loginButton).click();
		return driver.findElement(invalidCredentialsLabel).getText();
	}
}
