package com.qa.NSEIndia.Factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.NSEIndia.constants.AppConstants;
import com.qa.NSEIndia.error.AppError;
import com.qa.NSEIndia.exceptions.BrowserException;

public class DriverManager {

	WebDriver driver;
	Properties prop;

	/**
	 * this method is used to initialize the driver on the basis of given browser
	 * name
	 * @return driver
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is : " + browserName);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//System.setProperty("webdriver.chrome.driver", "F:\\QA\\7LiveProjects\\DataDrivenFramework\\DataDrivenFramework\\src\\test\\resources\\executables\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-blink-features");
			options.addArguments("--disable-blink-features=AutomationControlled");
			driver = new ChromeDriver(options);
			break;
		case "edge":
			EdgeOptions optionsEdge = new EdgeOptions();
			optionsEdge.addArguments("--disable-blink-features");
			optionsEdge.addArguments("--disable-blink-features=AutomationControlled");
			driver = new EdgeDriver(optionsEdge);
			break;
		case "firefox":
			FirefoxOptions optionsFirefox = new FirefoxOptions();
			optionsFirefox.addArguments("--disable-blink-features");
			optionsFirefox.addArguments("--disable-blink-features=AutomationControlled");
			driver = new FirefoxDriver(optionsFirefox);
			break;

		default:
			System.out.println("Please pass the right browser name");
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;
	}

	/**
	 * this method is used to initialize the properties from the .properties file
	 * @return prop
	 */
	public Properties initProp() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
