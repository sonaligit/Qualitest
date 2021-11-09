package com.reusable;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.webdriver.DriverSource;

public class reusable implements DriverSource {
	public final static String Application_url_property_file = "testSettings.properties";
	@Override
	public WebDriver newDriver() {

		String browserType = "";
		WebDriver driver = null;
		String chromeDriverexe = readProperty(Application_url_property_file, "CHROME_DRIVER_PATH");
		 System.setProperty("webdriver.chrome.driver", chromeDriverexe);
		ChromeOptions chromeOptions;
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("start-maximized");
			driver = new ChromeDriver(chromeOptions);

		return driver;
	}

	@Override
	public boolean takesScreenshots() {
		// TODO Auto-generated method stub
		return false;
	}

	public final static String PROPERTY_PATH = "testproperties/";

	public String readProperty(String fileName, String propertyName) {
		String propertyValue = "";
		InputStream inputStream;

		try {
			Properties prop = new Properties();
			String propFileName = PROPERTY_PATH + fileName;

			inputStream = ClassLoader.getSystemClassLoader().getSystemResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			}
			propertyValue = prop.getProperty(propertyName);
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Issue with properties" + e.getMessage());
		}
		return propertyValue;
	}
	
	
}

