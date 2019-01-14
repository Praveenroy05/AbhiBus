package com.abhi.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.abhi.qa.base.TestBase;
import com.abhi.qa.pages.SearchSeatPage;
import com.abhi.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;
import com.abhi.qa.pages.HomePage;

public class HomePageTest extends TestBase{
	HomePage homePage;
	SearchSeatPage searchSeatPage;
	TestUtil testUtil;
	
	public HomePageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		testUtil = new TestUtil();
		homePage= new HomePage();
		searchSeatPage = new SearchSeatPage();	
	}
	
	@Test(priority=1, enabled=true)
	
	public void validateAbhiBusLogoToolTip() throws IOException{
		
		try {
			et= er.startTest("validateAbhiBusLogoToolTip");
		String tooltip = homePage.validateAbhiBusLogo();
		TestUtil.takeScreenshotAtEndOfTest();
	
		Assert.assertEquals(tooltip, "abhibus.com - India's Fastest Online bus ticket booking site");
		//TestUtil.extentReportForPassedTestCase();
	}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void searchSeat() throws IOException{
		try {
			et= er.startTest("searchSeat");
			searchSeatPage = homePage.fillDetails(prop.getProperty("leavingFrom"), prop.getProperty("goingTo"));
			TestUtil.takeScreenshotAtEndOfTest();
		
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	


}
