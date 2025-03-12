package com.qa.NSEIndia.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.NSEIndia.base.BaseTest;
import com.qa.NSEIndia.constants.AppConstants;
import com.qa.NSEIndia.error.AppError;

public class HomePageTests extends BaseTest {
	
	@Test
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void checkNifty50LinkExistTest() {
		Assert.assertTrue(homePage.checkNifty50LinkExist(), AppError.ELEMENT_NOT_FOUND);
	}
	
	@Test
	public void checkTextTest() throws InterruptedException {
		Assert.assertEquals(homePage.clickNifty50LinkAndGetText("RELIANCE"), AppConstants.ACTUAL_VALUE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void selectProductTest() throws InterruptedException {
		String text = homePage.selectProduct("tatamotors");
		Assert.assertEquals(text, AppConstants.ACTUAL_VALUE, AppError.TITLE_NOT_FOUND);
		System.out.println(text);
	}
	
}
