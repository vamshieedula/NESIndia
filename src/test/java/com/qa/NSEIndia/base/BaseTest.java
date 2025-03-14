package com.qa.NSEIndia.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.NSEIndia.Factory.DriverManager;
import com.qa.NSEIndia.pages.CompanyInfoPage;
import com.qa.NSEIndia.pages.HomePage;
import com.qa.NSEIndia.pages.Nifty50Page;

public class BaseTest {

	DriverManager dm;
	WebDriver driver;

	protected Properties prop;
	protected HomePage homePage;
	protected Nifty50Page nifty50Page;
	protected CompanyInfoPage companyInfoPage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(@Optional("chrome") String browserName) {
		dm = new DriverManager();
		prop = dm.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = dm.initDriver(prop);
		homePage = new HomePage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
