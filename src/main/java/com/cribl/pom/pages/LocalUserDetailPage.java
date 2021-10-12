package com.cribl.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.cribl.openapi.dto.User;
import com.cribl.util.Logging;

public class LocalUserDetailPage extends BasePage {

	public LocalUserDetailPage(BasePage page) {
		this.driver = page.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void verifyUser(User user) {
		Logging.log("Verifying user: " + user.toString());
		driver.findElement(By.xpath("//input[@placeholder='Enter username'][@value='" + user.getId() + "']"));
		driver.findElement(By.xpath("//input[@placeholder='Enter first name'][@value='" + user.getFirst() + "']"));
		driver.findElement(By.xpath("//input[@placeholder='Enter last name'][@value='" + user.getLast() + "']"));
		driver.findElement(By.xpath("//input[@placeholder='Enter email'][@value='" + user.getEmail() + "']"));
	}
}
