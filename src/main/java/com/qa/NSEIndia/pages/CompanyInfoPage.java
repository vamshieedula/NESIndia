package com.qa.NSEIndia.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.NSEIndia.constants.AppConstants;
import com.qa.NSEIndia.utilities.ElementUtilities;
import com.qa.NSEIndia.utilities.TimeUtil;

public class CompanyInfoPage {
	
	private WebDriver driver;
	private ElementUtilities eleUtil;
	
	public CompanyInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtilities(driver);		
	}
	
	private By currentMarketValue = By.xpath("//span[@id=\"quoteLtp\"]");
	private By tradeBar = By.xpath("//a[@id='infoTrade']");
	private By weekHighLow = By.xpath("//span[starts-with(@id,\"week52\")]");
	
	public boolean checkTradeBarExist() {
		return eleUtil.doIsDisplayed(tradeBar);
	}
	
	public List<String> selectProduct() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String currentValue = eleUtil.doGetText(currentMarketValue);
		System.out.println("current market value of the stock is "+currentValue);
		List<String> week52Values = eleUtil.getElementsTextList(weekHighLow);
		return week52Values;
	}

}
