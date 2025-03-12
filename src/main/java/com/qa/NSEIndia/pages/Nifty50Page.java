package com.qa.NSEIndia.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Nifty50Page {
	
	private WebDriver driver;
	
	public Nifty50Page(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getWindow() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for(String childWindowHandle : allWindowHandles) {
			driver.switchTo().window(childWindowHandle);
		}
	}
	
	public String getText(String company) {
		getWindow();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = driver.findElement(By.xpath("(//td[@headers='"+company+"']/parent::tr//td)[12]")).getText();
		return text;
	}

}
