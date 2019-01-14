package com.abhi.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.abhi.qa.base.TestBase;
import com.abhi.qa.pages.TotalPaymentPage;
import com.abhi.qa.pages.SearchSeatPage;
import com.abhi.qa.pages.HomePage;
import com.abhi.qa.util.TestUtil;

public class SearchSeatPageTest extends TestBase {
	HomePage homePage;
	SearchSeatPage searchSeatPage;
	TestUtil testUtil;
	TotalPaymentPage paymentpage;

	public SearchSeatPageTest() {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		testUtil = new TestUtil();
		paymentpage = new TotalPaymentPage();
		homePage = new HomePage();
		searchSeatPage= new SearchSeatPage();
		searchSeatPage = homePage.fillDetails(prop.getProperty("leavingFrom"), prop.getProperty("goingTo"));
	}
	
	
	@Test(priority=1)
	public void verifyOnwardJourneyBookingPage() throws IOException{
		try {
			et= er.startTest("verifyOnwardJourneyBookingPage");
		boolean onwardJourneyPage = searchSeatPage.verifyOnwardJourneyScreen();
		Assert.assertTrue(onwardJourneyPage);
		TestUtil.takeScreenshotAtEndOfTest();
		
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void bookOnwardandReturnTicket() throws IOException{
		try{
			et= er.startTest("bookOnwardandReturnTicket");
			searchSeatPage.bookOnwardAndReturnSeat();
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	
	
	
	
	

}
