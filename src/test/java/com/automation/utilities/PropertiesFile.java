package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFile {

	public static Properties readInputPropertiesFile() throws Exception{
		Properties properties = null;
		try {
			File file = new File("Data\\Input.properties");
			FileInputStream fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (Exception e) {
			throw e;
		}
		return properties;
	}
	
	
}
