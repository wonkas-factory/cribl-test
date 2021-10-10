package com.cribl.test.api;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.cribl.data.Data;
import com.cribl.openapi.client.ApiException;
import com.cribl.openapi.dto.AuthToken;
import com.cribl.openapi.dto.InlineResponse20026;
import com.cribl.openapi.dto.LoginInfo;
import com.cribl.openapi.service.AuthApi;
import com.cribl.util.CriblClientHelper;

public class AuthTest {
	private AuthApi api;
	private Data data;
	private String token;

	@BeforeClass
	public void beforeClass() {
		api = new AuthApi(CriblClientHelper.initialize(false));
		data = new Data();
	}

	/**
	 * Log in and get authentication token
	 *
	 * 
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test
	public void authLoginPostInvalidTest() {
		LoginInfo body = new LoginInfo();
		body.setUsername(data.name().username());
		body.setPassword(data.internet().password());

		try {
			api.authLoginPost(body);
		} catch (ApiException e) {
			Assert.assertTrue(e.getResponseBody().contains("Invalid user or password"));
		}
	}

	/**
	 * Log in and get authentication token
	 *
	 * 
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test
	public void authLoginPostTest() throws ApiException {
		LoginInfo body = new LoginInfo();
		body.setUsername(data.getEnvProp("un"));
		body.setPassword(data.getEnvProp("pw"));
		AuthToken response = api.authLoginPost(body);

		Assert.assertNotNull(response.getToken());
		token = response.getToken();
	}

	/**
	 * get the external authentication system&#x27;s groups
	 *
	 * get the external authentication system&#x27;s groups
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test(dependsOnMethods = { "authLoginPostTest" })
	public void authGroupsGetTest() throws ApiException {
		api.getApiClient().addDefaultHeader("Authorization", "Bearer " + token);
		InlineResponse20026 response = api.authGroupsGet();

		Assert.assertEquals(Integer.valueOf(response.getItems().size()), response.getCount());
	}

	/**
	 * Log out current user
	 *
	 * 
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test(dependsOnMethods = { "authLoginPostTest" })
	public void authLogoutPostTest() throws ApiException {
		api.getApiClient().addDefaultHeader("Authorization", "Bearer " + token);
		api.authLogoutPost();
	}
}
