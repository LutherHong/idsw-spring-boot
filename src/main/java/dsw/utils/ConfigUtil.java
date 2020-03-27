package dsw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import dsw.common.Constants;

public class ConfigUtil {

	private static Properties prop = new Properties();
	private static boolean hasLoad = false;

	private static void loadProperties() {
		InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream(Constants.CONFIG_FILE);
		try {
			prop.load(is);
			hasLoad = true;
		} catch (IOException e) {
			e.printStackTrace();
			hasLoad = false;
		}
	}

	public static String getString(String key) {
		// 优先加载环境变量
		String value = System.getenv(key);
		if (null == value) {
			if (!hasLoad) {
				loadProperties();
			}
			value = prop.getProperty(key);
		}
		return value == null ? "" : value.trim();
	}

	public static String getString(String key, String defaultStr) {
		String value = getString(key);
		return null == value ? defaultStr : value;
	}

}
