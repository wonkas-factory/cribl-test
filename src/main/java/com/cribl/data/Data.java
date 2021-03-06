package com.cribl.data;

import java.io.FileReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.cribl.openapi.dto.User;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.github.javafaker.Faker;

public class Data extends Faker {
	private static Map<String, String> env = new HashMap<String, String>();

	public Data() {
		super();
		loadEnvironment();
	}
	
	public User getRandomUser() {
		User user = new User();
		user.setDisabled(false);
		user.setEmail(this.internet().emailAddress());
		user.setFirst(this.name().firstName());
		user.setLast(this.name().lastName());
		user.setId(this.internet().uuid());
		user.setUsername(this.name().username());
		user.setPassword(this.internet().password());
		return user;
	}

	public String getEnvProp(String key) {
		String value = System.getProperty(key);
		if (value != null) {
			return value;
		}

		if (env.containsKey(key)) {
			return env.get(key);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private void loadEnvironment() {
		String envFile = System.getProperty("envFile", "environment.yml");
		try {
			String path = Paths.get(".").toAbsolutePath().normalize().toString() + "/config/" + envFile;
			YamlReader reader = new YamlReader(new FileReader(path));
			env = (HashMap<String, String>) reader.read();
			reader.close();
		} catch (Exception e) {

		}
	}
}
