package com.cribl.pom.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.cribl.data.Data;
import com.cribl.util.Logging;

public class BasePage {
	protected WebDriver driver;
	protected Data data;

	public WebDriver initialize() {
		data = new Data();
		String browser = data.getEnvProp("browser");
		if (browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		String host = data.getEnvProp("host");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(host);

		return driver;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public Data getData() {
		return this.data;
	}

	public void quit() {
		driver.quit();
	}

	public BasePage leftNavigationMenu(String item) {
		// TODO enumerate
		Logging.log("Selecting left navigation menu item: " + item);
		if (item.equalsIgnoreCase("Settings")) {
			driver.findElement(By.xpath("//a[@href='/settings/info']")).click();
			return new SettingsPage(this);
		}

		return null;
	}
}
