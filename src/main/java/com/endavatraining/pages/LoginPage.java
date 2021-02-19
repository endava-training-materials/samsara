package com.endavatraining.pages;

import com.endavatraining.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	private static Logger log = LogManager.getLogger(LoginPage.class.getName());

	private static final String ENDAVATRAINING_URL = "http://localhost:9010";
	public static final String WRONG_CREDENTIALS_MESSAGE = "Invalid username and password.";

	// UI elements - locators
	private By loginButton = By.xpath("//input[@value='Log In']");
	private By userName = By.xpath("//input[@name='username']");
	private By password = By.xpath("//input[@name='password']");
	private By invalidCredentialsLabel = By.xpath("//div[@class='alert alert-danger']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}


	/**
	 * login button get method
	 *
	 */
	public By getLoginButton() {

		return loginButton;

	}


	/**
	 * Open samsara website and maximize the window
	 *
	 */
	public void open() {

		log.debug("Open Endava training web site");
		driver.get(ENDAVATRAINING_URL);
		driver.manage().window().maximize();
	}


	/**
	 *
	 * open samsara website and login
	 *
	 * @param username - correct username
	 * @param password - correct password
	 */
	public HomePage openAs(String username, String password){

		open();

		log.debug("Type username - " + username);
		driver.findElement(this.userName).sendKeys(username);

		log.debug("Type password - " + password);
		driver.findElement(this.password).sendKeys(password);

		log.debug("Click on login button - " + password);
		driver.findElement(this.loginButton).click();

		return new HomePage(driver);
	}

	/**
	 *
	 * Try login process with invalid credentials
	 *
	 * @param username - invalid username
	 * @param password - invalid password
	 */
	public void enterWrongCredentials(String username, String password) {

		log.debug("Type username - " + username);
		driver.findElement(this.userName).sendKeys(username);

		log.debug("Type password - " + password);
		driver.findElement(this.password).sendKeys(password);

		log.debug("Click on login button");
		driver.findElement(this.loginButton).click();

		Utils.webDriverWait(driver, this.invalidCredentialsLabel);
	}

	/**
	 * Get invalid login alert text
	 *
	 */
	public String getInvalidCredentialsLabelText(){

		String label = driver.findElement(invalidCredentialsLabel).getText();
		log.debug("Unsuccessful logging alert - " + label);

		return label;
	}


	/**
	 * Check if login button is present on a page
	 *
	 * @return Boolean
	 */
	public Boolean isLoginButtonPresent(){

		return isElementPresent(loginButton);
	}

}
