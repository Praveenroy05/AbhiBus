package com.abhi.qa.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.abhi.qa.base.TestBase;
import com.abhi.qa.util.TestUtil;

public class HomePage extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(xpath="//*[contains(@title,'abhibus.com -')]")
	WebElement abhibusLogo;
	
	@FindBy(name="source")
	WebElement leavingFrom;
	
	@FindBy(xpath="//*[text()='Hyderabad']")
	WebElement selectLeavingFromLocation;
	
	@FindBy(name="destination")
	WebElement goingTo;
	
	@FindBy(xpath="//*[text()='Goa']")
	WebElement selectGoingToLocation;
	
	@FindBy(name="journey_date")
	WebElement dateOfJourneybutton;
	
	@FindBy(xpath="(//table)[1]/tbody/tr[4]/td[6]")
	WebElement pickLeavingDateFromCalendar;
	
	@FindBy(name="journey_rdate")
	WebElement dateOfReturnJourneybutton;
	
	@FindBy(xpath="(//table)[1]/tbody/tr[5]/td[4]")
	WebElement pickReturnDateFromCalander;
	
	@FindBy(xpath="//*[text()='Search']")
	WebElement searchBtn;
	
	@FindBy(xpath="//*[text()='Hyderabad → Goa ']")
	WebElement onwardJourneyDetails;
	
	
	//Initializing the Page Objects:
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	//Validate Abhi Bus Logo tool Tip message
	public String validateAbhiBusLogo(){
		return abhibusLogo.getAttribute("title");
		
	}
	
	// Fill the abhi bus form to search a bus for 
	// both onward and return journey and click
	// on search button
	
	public SearchSeatPage fillDetails(String LeavingFrom, String GoingTo) throws IOException{
		try {
		
		wait.until(ExpectedConditions.visibilityOf(leavingFrom));
		leavingFrom.sendKeys(LeavingFrom);
		
		wait.until(ExpectedConditions.visibilityOf(selectLeavingFromLocation));
		selectLeavingFromLocation.click();
		
		wait.until(ExpectedConditions.visibilityOf(goingTo));
		goingTo.sendKeys(GoingTo);
		
		wait.until(ExpectedConditions.visibilityOf(selectGoingToLocation));
		selectGoingToLocation.click();
		
		wait.until(ExpectedConditions.visibilityOf(dateOfJourneybutton));
		dateOfJourneybutton.click();
		
		wait.until(ExpectedConditions.visibilityOf(pickLeavingDateFromCalendar));
		pickLeavingDateFromCalendar.click();
		TestUtil.takeScreenshotAtEndOfTest();
		
	
		wait.until(ExpectedConditions.visibilityOf(dateOfReturnJourneybutton));
		dateOfReturnJourneybutton.click();
		
		wait.until(ExpectedConditions.visibilityOf(pickReturnDateFromCalander));
		pickReturnDateFromCalander.click();
		
		searchBtn.click();
		    	
		onwardJourneyDetails.isDisplayed();
		
	}
		 catch (IOException e) {
				
					e.printStackTrace();
				}
		return new SearchSeatPage();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
