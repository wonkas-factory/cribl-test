package com.cribl.test.api;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;

import com.cribl.data.Data;
import com.cribl.openapi.client.ApiException;
import com.cribl.openapi.dto.InlineResponse20011;
import com.cribl.openapi.dto.User;
import com.cribl.openapi.service.UsersApi;
import com.cribl.util.CriblClientHelper;

public class UsersTest {
	private UsersApi api;
	private User user;
	private Data data;

	@BeforeClass
	public void beforeClass() {
		api = new UsersApi(CriblClientHelper.initialize(true));
		user = new User();
		data = new Data();
	}

	/**
	 * Create User
	 *
	 * Create User
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test
	public void systemUsersPostTest() throws ApiException {
		user.setDisabled(false);
		user.setEmail(data.internet().emailAddress());
		user.setFirst(data.name().firstName());
		user.setLast(data.name().lastName());
		user.setId(data.internet().uuid());
		user.setUsername(data.name().username());
		user.setPassword(data.internet().password());
		InlineResponse20011 response = api.systemUsersPost(user);

		Assert.assertEquals(response.getItems().get(0).getId(), user.getId());
	}

	/**
	 * Get User by ID
	 *
	 * Get User by ID
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test(dependsOnMethods = { "systemUsersPostTest" })
	public void systemUsersIdGetTest() throws ApiException {
		InlineResponse20011 response = api.systemUsersIdGet(user.getId());

		Assert.assertEquals(response.getItems().get(0).getId(), user.getId());
	}

	/**
	 * Update User
	 *
	 * Update User
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test(dependsOnMethods = { "systemUsersPostTest" })
	public void systemUsersIdPatchTest() throws ApiException {
		user.setDisabled(true);
		InlineResponse20011 response = api.systemUsersIdPatch(user.getId(), user);

		Assert.assertEquals(response.getItems().get(0).isDisabled(), user.isDisabled());
	}

	/**
	 * Get a list of User objects
	 *
	 * Get a list of User objects
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test(dependsOnMethods = { "systemUsersPostTest" })
	public void systemUsersGetTest() throws ApiException {
		InlineResponse20011 response = api.systemUsersGet();

		Boolean found = false;
		for (User user : response.getItems()) {
			if (user.getId().equalsIgnoreCase(this.user.getId())) {
				found = true;
			}
		}

		if (!found) {
			throw new ApiException("Newly created user with following id not found: " + user.getId());
		}
	}

	/**
	 * Update user except for their roles
	 *
	 * Update user except for their roles
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Ignore
	@Test(dependsOnMethods = { "systemUsersPostTest" })
	public void systemUsersidInfoPatchTest() throws ApiException {
		InlineResponse20011 response = api.systemUsersidInfoPatch();

		// TODO: this has documentation errors
	}

	/**
	 * Delete User
	 *
	 * Delete User
	 *
	 * @throws ApiException if the Api call fails
	 */
	@Test(dependsOnMethods = { "systemUsersGetTest", "systemUsersIdPatchTest", "systemUsersIdGetTest" })
	public void systemUsersIdDeleteTest() throws ApiException {
		InlineResponse20011 response = api.systemUsersIdDelete(user.getId());

		Assert.assertEquals(response.getItems().get(0).getId(), user.getId());
	}
}
