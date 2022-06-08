package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	public Properties loadProperties() {
		try {
			Properties prop = new Properties();
			String configPath= "C:\\Users\\VISHAKAN\\eclipse-workspace\\RestAssuredLMSAPI\\src\\test\\resources\\Configurations\\config.properties";
			FileInputStream input = new FileInputStream(configPath);
			prop.load(input);
			return prop;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
