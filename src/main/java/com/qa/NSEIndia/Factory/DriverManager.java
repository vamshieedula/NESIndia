package com.qa.NSEIndia.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;

import com.qa.NSEIndia.constants.AppConstants;
import com.qa.NSEIndia.error.AppError;
import com.qa.NSEIndia.exceptions.BrowserException;
import com.qa.NSEIndia.logger.Log;

public class DriverManager {

	WebDriver driver;
	Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is used to initialize the driver on the basis of given browser
	 * name
	 * @return driver
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		//System.out.println("Browser name is : " + browserName);
		Log.info("Running test on "+browserName+ " browser");
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			ChromeOptions optionsChrome = new ChromeOptions();
			optionsChrome.addArguments("--disable-blink-features");
			optionsChrome.addArguments("--disable-blink-features=AutomationControlled");
			tlDriver.set(new ChromeDriver(optionsChrome));
			break;
		case "edge":
			EdgeOptions optionsEdge = new EdgeOptions();
			optionsEdge.addArguments("--disable-blink-features");
			optionsEdge.addArguments("--disable-blink-features=AutomationControlled");
			tlDriver.set(new EdgeDriver(optionsEdge));
			break;
		case "firefox":
			FirefoxOptions optionsFirefox = new FirefoxOptions();
			optionsFirefox.addArguments("--disable-blink-features");
			optionsFirefox.addArguments("--disable-blink-features=AutomationControlled");
			tlDriver.set(new FirefoxDriver(optionsFirefox));
			break;

		default:
			Log.error("Not a valid browser, Please pass the right browser name... "+browserName);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	
	/**
	 * get the local thread copy of driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
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
			Log.info("Properties file loaded");
		} catch (FileNotFoundException e) {
			Log.error("Unable to find properites file at the given location");
			e.printStackTrace();
		} catch (IOException e) {
			Log.error("error in loading properites file");
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot(String methodName) {

		TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
		File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
		String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots";
		File screenshotsDir = new File(screenshotsDirPath);
		if (!screenshotsDir.exists()) {
			if (screenshotsDir.mkdirs()) {
				Log.info("Folder 'screenshots' created successfully at: " + screenshotsDirPath);
			} else {
				Log.info("Failed to create the folder 'screenshots' at: " + screenshotsDirPath);
			}
		}

		String screenshotPath = screenshotsDirPath + "/" + methodName + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(screenshotPath);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination.getAbsolutePath();
	}
	
}
