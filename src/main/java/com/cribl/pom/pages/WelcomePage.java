package com.cribl.pom.pages;

import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends BasePage {

	public WelcomePage(BasePage page) {
		this.driver = page.getDriver();
		PageFactory.initElements(driver, this);
	}
}
