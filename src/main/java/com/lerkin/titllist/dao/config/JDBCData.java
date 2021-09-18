package com.lerkin.titllist.dao.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class JDBCData {

	private static final String DB_URL = "url";
	private static final String DB_USER = "user";
	private static final String DB_PASSWORD = "password";
	private static final String DB_DRIVER = "driver";
	private static final String DB_POOL_SIZE = "poolSize";

	static final String URL;
	static final String USER;
	static final String PASSWORD;
	static final String DRIVER;
	static final Integer POOL_SIZE;

	static {
		InputStream inputStream = JDBCData.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			URL = properties.getProperty(DB_URL);
			USER = properties.getProperty(DB_USER);
			PASSWORD = properties.getProperty(DB_PASSWORD);
			DRIVER = properties.getProperty(DB_DRIVER);
			POOL_SIZE = Integer.parseInt(properties.getProperty(DB_POOL_SIZE));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

