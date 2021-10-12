package com.cribl.pom.logic;

import com.cribl.pom.pages.LoginPage;
import com.cribl.pom.pages.WelcomePage;

public class LoginLogic {
	public static WelcomePage login(LoginPage page) {
		page.enterUsername(page.getData().getEnvProp("un"));
		page.enterPassword(page.getData().getEnvProp("pw"));
		return page.clickSubmit();
	}
}
