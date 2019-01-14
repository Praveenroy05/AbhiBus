package com.abhi.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.abhi.qa.base.TestBase;
import com.abhi.qa.util.TestUtil;

public class TotalPaymentPage extends TestBase {

	@FindBy(xpath = "(//*[@class='detailjrny'])[1]/child::*[1]/child::*[1]")
	WebElement onwardSourceAndDestination;
	
	@FindBy(xpath = "(//*[@class='detailjrny'])[1]/child::*[1]/child::*[3]/strong")
	WebElement onwardBoardingPointValidation;
	
	@FindBy(xpath = "(//*[@class='detailjrny'])[2]/child::*[1]/child::*[1]")
	WebElement returnSourceAndDestination;
	
	@FindBy(xpath = "(//*[@class='detailjrny'])[2]/child::*[1]/child::*[3]/strong")
	WebElement returnBoardingPointValidation;
	
	@FindBy(xpath="//*[text()=' Net Payable']/following::*[1]/child::*[2]")
	WebElement netPayment;
	
	
	
	// Initializing the Page Objects:
	public TotalPaymentPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public String verifyOnwardSourceAndDestination(){
		return onwardSourceAndDestination.getText();
	}
	
	public String verifyOnwardBoardingPoint(){
		return onwardBoardingPointValidation.getText();
	}
	
	public String verifyReturnSourceAndDestination(){
		return returnSourceAndDestination.getText();
	}
	
	public String verifyReturnBoardingPoint(){
		return returnBoardingPointValidation.getText();
	}
	
	public void verifyNetPayment() throws IOException{
		try {
		wait.until(ExpectedConditions.visibilityOf(netPayment));
		netPaymentFare= netPayment.getText();
		String netPaymentFare1=netPaymentFare.replace(",","");
		
		System.out.println(netPaymentFare1);
		float netPaymentFare2=Float.parseFloat(onwardfare)+Float.parseFloat(returnFare);
		System.out.println(netPaymentFare2);
		TestUtil.takeScreenshotAtEndOfTest();
		
		
		if(netPaymentFare2==Float.parseFloat(netPaymentFare1))
		{
			System.out.println("Net payment is equal to onward plus return payment");
		}
		else {
			System.out.println("Net payment is not equal to onward plus return payment");
		}
		}
		catch(IOException e)
		{
			
			e.printStackTrace();
		}
		
	}

}
