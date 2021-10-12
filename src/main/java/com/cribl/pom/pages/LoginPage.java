package com.cribl.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cribl.util.Logging;

public class LoginPage extends BasePage {
	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submit;

	public LoginPage(BasePage page) {
		this.driver = page.getDriver();
		PageFactory.initElements(driver, this);
	}

	public LoginPage() {
		this.driver = this.initialize();
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String s) {
		Logging.log("Entering username: " + s);
		username.sendKeys(s);
	}

	public void enterPassword(String s) {
		Logging.log("Entering password: " + s);
		password.sendKeys(s);
	}

	public WelcomePage clickSubmit() {
		Logging.log("Clicking submit button");
		submit.click();
		return new WelcomePage(this);
	}

}
