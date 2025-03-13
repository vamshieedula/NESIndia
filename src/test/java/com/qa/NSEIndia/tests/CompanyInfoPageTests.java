package com.qa.NSEIndia.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.NSEIndia.base.BaseTest;
import com.qa.NSEIndia.constants.AppConstants;
import com.qa.NSEIndia.error.AppError;

public class CompanyInfoPageTests extends BaseTest{
	
	@BeforeClass
	public void accSetup() throws InterruptedException {
		companyInfoPage = homePage.searchProduct("bel");
	}
	
	@Test
	public void selectProductTest() throws InterruptedException {
		List<String> text = companyInfoPage.selectProduct();
		for (int i = 0; i < text.size(); i++) {
				System.out.println("Last 52week high and low values for the company is "+text.get(i));
			}
	}
	
	@Test
	public void selectProductTestWithAssert() throws InterruptedException {
		List<String> text = companyInfoPage.selectProduct();
		for (int i = 0; i < text.size(); i++) {
			if(i==0) {
				System.out.println("Last 52week high value for the company is "+text.get(i));
				Assert.assertEquals(text.get(i), AppConstants.ACTUAL_52WEEK_HIGHVALUE, AppError.VALUE_NOT_MATCHED);
			}else {
				System.out.println("Last 52week low value for the company is "+text.get(i));
				Assert.assertEquals(text.get(i), AppConstants.ACTUAL_52WEEK_LOWVALUE, AppError.VALUE_NOT_MATCHED);
			}
		}
	}

}
