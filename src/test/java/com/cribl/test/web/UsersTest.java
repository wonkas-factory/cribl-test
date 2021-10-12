package com.cribl.test.web;

import org.testng.annotations.Test;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.cribl.data.Data;
import com.cribl.openapi.client.ApiException;
import com.cribl.openapi.dto.User;
import com.cribl.openapi.service.UsersApi;
import com.cribl.pom.logic.LoginLogic;
import com.cribl.pom.pages.BasePage;
import com.cribl.pom.pages.LocalUserDetailPage;
import com.cribl.pom.pages.LoginPage;
import com.cribl.pom.pages.SettingsPage;
import com.cribl.util.CriblClientHelper;

public class UsersTest {
	private UsersApi api;
	private User user;
	private Data data;
	private BasePage page;

	@BeforeClass
	public void beforeClass() throws ApiException {
		data = new Data();
		user = data.getRandomUser();

		// Create user through API
		api = new UsersApi(CriblClientHelper.initialize(true));
		api.systemUsersPost(user);
		
		// Login to welcome page
		page = LoginLogic.login(new LoginPage());
	}

	/**
	 * Verify created user on web
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void VerifyCreatedUserTest() throws InterruptedException {
		page = page.leftNavigationMenu("Settings");
		((SettingsPage) page).selectSetting("Local Users");
		page = ((SettingsPage) page).localUsersSelect(user.getId());
		((LocalUserDetailPage) page).verifyUser(user);		
	}

	@AfterClass
	public void afterClass() throws ApiException {
		api.systemUsersIdDelete(user.getId());
		page.quit();
	}
}
