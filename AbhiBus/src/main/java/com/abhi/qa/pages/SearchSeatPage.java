package com.abhi.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.abhi.qa.base.TestBase;
import com.abhi.qa.util.TestUtil;

public class SearchSeatPage extends TestBase {
	
	@FindBy(xpath="//*[text()='Hyderabad → Goa ']")
	WebElement onwardJourneyValidation;

	@FindBy(xpath = "(//*[text()='Select Seat'])[1]")
	WebElement selectOnwardSeatButton;

	@FindBy(xpath = "//*[@id='O1-13ZZ']")
	WebElement selectOnwardSeat;
	
	@FindBy(xpath = "//*[@id='O1-15ZZ']")
	WebElement selectOnwardSeat1;
	
	@FindBy(xpath = "//*[@class='dropdown_custom']")
	WebElement onwardBoadingPointDropDown;
	
	@FindBy(xpath="(//*[@id='totalfare'])")
	WebElement onwardTotalFare;

	@FindBy(xpath = "//*[@id='btnEnable1']")
	WebElement bookReturnButton;

	@FindBy(xpath = "//*[text()='Goa → Hyderabad ']")
	WebElement returnJourneyvalidation;
	
	@FindBy(xpath="//*[@id='497007323']")
    WebElement selectReturnSeatButton;
	
	@FindBy(xpath="(//*[@id='O1-13ZZ'])[2]")
	WebElement selectReturnSeat;
	
	@FindBy(xpath="(//*[@id='O1-15ZZ'])[2]")
	WebElement selectReturnSeat1;
	
	@FindBy(xpath="(//*[@class='dropdown_custom'])[2]")
	WebElement returnBoardingPointDropDown;
	
	@FindBy(xpath="(//*[@id='totalfare'])[2]")
	WebElement returnTotalFare;
	
	@FindBy(xpath="(//*[@value='Continue to Payment '])[2]")
	WebElement continuePaymentButton;
	
	@FindBy(xpath="//*[text()='Payment ']")
	WebElement payment;
	
	
	// Initializing the Page Objects:
	public SearchSeatPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	// To check whether we are on the onward ticket
	// booking or not
	public boolean verifyOnwardJourneyScreen(){
		return onwardJourneyValidation.isEnabled();
	}
	

	public TotalPaymentPage bookOnwardAndReturnSeat() throws IOException{
	try {
		
		wait.until(ExpectedConditions.visibilityOf(selectOnwardSeatButton));
		selectOnwardSeatButton.click();
		
		wait.until(ExpectedConditions.visibilityOf(selectOnwardSeat1));
		try {
		if(selectOnwardSeat.isEnabled()) {
			selectOnwardSeat.click();
		}
		
		}
		catch(Exception ex) {
			selectOnwardSeat1.click();
		}
		
		TestUtil.takeScreenshotAtEndOfTest();
	
		
		wait.until(ExpectedConditions.visibilityOf(onwardTotalFare));
		onwardfare= onwardTotalFare.getText();
	//nwardfare.replace(".00","");
		System.out.println(onwardfare);
		
		wait.until(ExpectedConditions.visibilityOf(onwardBoadingPointDropDown));
		Select select= new Select(onwardBoadingPointDropDown);
		select.selectByIndex(3);
		TestUtil.takeScreenshotAtEndOfTest();
		
		
		wait.until(ExpectedConditions.visibilityOf(bookReturnButton));
		bookReturnButton.click();
		TestUtil.takeScreenshotAtEndOfTest();
		
		// check whether we are on the book return 
		// page or not
		
		wait.until(ExpectedConditions.visibilityOf(returnJourneyvalidation));
		returnJourneyvalidation.isDisplayed();
		returnJourneyvalidation.isEnabled();
		System.out.println(" We are on the return journey booking page");
		
		wait.until(ExpectedConditions.visibilityOf(selectReturnSeatButton));
		selectReturnSeatButton.click();
		
		wait.until(ExpectedConditions.visibilityOf(selectReturnSeat1));
		
		try {
		if(selectReturnSeat.isEnabled())
		{
			selectReturnSeat.click();
		}
		}
		catch(Exception ex1)
		{
			selectReturnSeat1.click();
		}
		
		wait.until(ExpectedConditions.visibilityOf(returnTotalFare));
		 returnFare= returnTotalFare.getText();
	//returnFare.replace(".00","");
		System.out.println(returnFare);
		
		wait.until(ExpectedConditions.visibilityOf(returnBoardingPointDropDown));
		Select select1= new Select(returnBoardingPointDropDown);
		select1.selectByIndex(1);
		
		wait.until(ExpectedConditions.visibilityOf(continuePaymentButton));
		continuePaymentButton.click();
		
		wait.until(ExpectedConditions.visibilityOf(payment));
		
		if(payment.isDisplayed())
				{
					TestUtil.takeScreenshotAtEndOfTest();
				
					System.out.println("Abhi Bus Booking Passed");
				}
		else {
			System.out.println("Abhi Bus Booking Failed");
		}
		
		
	}
	catch(IOException e)
		{
			
			e.printStackTrace();
		}
	
	return new TotalPaymentPage();
	}
	
	
	
	
	
	
	

}
