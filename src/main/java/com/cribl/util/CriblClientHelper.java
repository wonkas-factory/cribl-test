package com.cribl.util;

import com.cribl.data.Data;
import com.cribl.openapi.client.ApiClient;
import com.cribl.openapi.client.ApiException;
import com.cribl.openapi.client.Configuration;
import com.cribl.openapi.dto.AuthToken;
import com.cribl.openapi.dto.LoginInfo;
import com.cribl.openapi.service.AuthApi;

public class CriblClientHelper {
	private static ApiClient criblClient;
	private static Data data = new Data();
	private final static Integer TIMEOUT = 15 * 1000;

	public static ApiClient initialize(Boolean getToken) {
		Logging.logToTestNgReporter();
		criblClient = new ApiClient();
		criblClient.setBasePath(data.getEnvProp("host")+data.getEnvProp("apiPath"));
		criblClient.setConnectTimeout(TIMEOUT);
		criblClient.setReadTimeout(TIMEOUT);
		criblClient.setWriteTimeout(TIMEOUT);

		if (getToken) {
			criblClient.addDefaultHeader("Authorization", "Bearer " + getToken());
		}
		criblClient.setDebugging(true);
		Configuration.setDefaultApiClient(criblClient);
		return criblClient;
	}

	private static String getToken() {
		AuthApi api = new AuthApi(criblClient);
		LoginInfo info = new LoginInfo();
		info.setUsername(data.getEnvProp("un"));
		info.setPassword(data.getEnvProp("pw"));
		try {
			AuthToken response = api.authLoginPost(info);
			return response.getToken();
		} catch (ApiException e) {
			Logging.error(e.getMessage());
			return null;
		}
	}
}
