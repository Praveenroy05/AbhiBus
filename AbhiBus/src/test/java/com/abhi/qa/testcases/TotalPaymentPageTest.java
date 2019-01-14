
package com.abhi.qa.testcases;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.abhi.qa.base.TestBase;
import com.abhi.qa.pages.TotalPaymentPage;
import com.abhi.qa.pages.SearchSeatPage;
import com.abhi.qa.pages.HomePage;
import com.abhi.qa.util.TestUtil;

public class TotalPaymentPageTest extends TestBase{

	HomePage homePage;
	SearchSeatPage searchSeatPage;
	TestUtil testUtil;
	TotalPaymentPage paymentPage;
	
	
	
	   
	public TotalPaymentPageTest(){
			super();
			
	}
	
	
	@BeforeMethod
	public void setUp() throws IOException {
		
		initialization();
		testUtil = new TestUtil();
		paymentPage = new TotalPaymentPage();
		homePage = new HomePage();
		searchSeatPage = homePage.fillDetails(prop.getProperty("leavingFrom"), prop.getProperty("goingTo"));
		paymentPage = searchSeatPage.bookOnwardAndReturnSeat();
	}
	
	@Test(priority=1)
	public void verifySourceDestinationNetPayment() throws IOException{
		et= er.startTest("verifySourceDestinationNetPayment");
		
		String onwSorDes= paymentPage.verifyOnwardSourceAndDestination();
		Assert.assertEquals(onwSorDes, "Hyderabad To Goa");
		String onwBoarding= paymentPage.verifyOnwardBoardingPoint();
		Assert.assertEquals(onwBoarding, "Kondapur");
		String resSorDes= paymentPage.verifyReturnSourceAndDestination();
		Assert.assertEquals(resSorDes, "Goa To Hyderabad");
		String retBoarding= paymentPage.verifyReturnBoardingPoint();
		Assert.assertEquals(retBoarding, "Mapusa");
		paymentPage.verifyNetPayment();
	}

	
}
