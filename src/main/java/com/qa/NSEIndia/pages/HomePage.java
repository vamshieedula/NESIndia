package com.qa.NSEIndia.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.NSEIndia.constants.AppConstants;
import com.qa.NSEIndia.utilities.ElementUtilities;
import com.qa.NSEIndia.utilities.TimeUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtilities eleUtil;

	private By nifty50 = By.xpath("(//img[@title=\"NSE - NIFTY 50\"])[1]");
	private By selectProduct = By.xpath("(//div[@id=\"header-search-input_listbox\"]//div/div)[1]");
	private By search = By.xpath("//input[@id=\"header-search-input\"]");
	private By weekHigh = By.xpath("//span[@id=\"week52highVal\"]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtilities(driver);
	}

	public String getHomePageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.HOME_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("home page title : " + title);
		return title;
	}

	public String selectProduct(String company) throws InterruptedException {
		eleUtil.isPageLoaded(TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.doSendKeys(search, company);
		eleUtil.clickWhenReady(selectProduct, TimeUtil.DEFAULT_MEDIUM_TIME);
		Thread.sleep(5000);
		return eleUtil.doGetText(weekHigh);
	}
	
	public boolean checkNifty50LinkExist() {
		return eleUtil.doIsDisplayed(nifty50);
	}

	public void getWindow() {
		String parent = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();

		while (it.hasNext()) {
			String windowId = it.next();
			if (!windowId.equals(parent)) {
				driver.switchTo().window(windowId);
				eleUtil.isPageLoaded(TimeUtil.DEFAULT_LONG_TIME);
				String title = driver.getTitle();
				System.out.println(title);
			}
		}
	}

	public String clickNifty50LinkAndGetText(String company) throws InterruptedException {
		eleUtil.doClickWithWait(nifty50, TimeUtil.DEFAULT_MEDIUM_TIME);
		String titlePar = driver.getTitle();
		System.out.println(titlePar);
		getWindow();
		String text = driver.findElement(By.xpath("(//td[@headers='"+company+"']/parent::tr//td)[12]")).getText();
		return text;

	}
}
