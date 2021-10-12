package com.cribl.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.cribl.util.Logging;

public class SettingsPage extends BasePage {

	public SettingsPage(BasePage page) {
		this.driver = page.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void selectSetting(String setting) {
		// TODO enumerate
		Logging.log("Selecting following setting: " + setting);
		if (setting.equalsIgnoreCase("Local Users")) {
			driver.findElement(By.xpath("//a[@href='/settings/access/users']")).click();
		}
	}

	public LocalUserDetailPage localUsersSelect(String id) {
		Logging.log("Selecting following local user with id: " + id);
		driver.findElement(By.xpath("//div[text()='" + id + "']")).click();
		return new LocalUserDetailPage(this);
	}
}
