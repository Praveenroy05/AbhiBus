package com.abhi.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.abhi.qa.util.TestUtil;
import com.abhi.qa.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ExtentReports er;
	public static ExtentTest et;
	public static ExtentReports er1;
	public static ExtentTest et1;
	public static String onwardfare;
	public static String returnFare;
	public static String netPaymentFare;
	
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\ADMIN\\Downloads\\PageObjectModel-master\\PageObjectModel-master\\src"
														+ "\\main\\java\\com\\abhi\\qa\\config\\config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\PageObjectModel-master\\PageObjectModel-master"
																	+ "\\src\\main\\resources\\chromedriver.exe");
			 driver=new ChromeDriver();
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\ADMIN\\Downloads\\PageObjectModel-master\\PageObjectModel-master"
																		+ "\\src\\main\\resources\\geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		
		else if(browserName.equals("IE")){
			System.setProperty("webdriver.ie.driver", "C:\\Users\\ADMIN\\Downloads\\PageObjectModel-master\\PageObjectModel-master"
																		+ "\\src\\main\\resources\\IEDriverServer.exe");	
			driver = new InternetExplorerDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait= new WebDriverWait(driver, TestUtil.WEBDRIVER_WAIT);
		
		driver.get(prop.getProperty("url"));
		
	}
	
	@BeforeTest
	public void setExtent()
	{
		//er=new ExtentReports("ExtentPassed.html",true);
		er=new ExtentReports("ExtentReport.html",true);
	}
	
	@AfterTest(alwaysRun= true)
	public void endReport()
	{
		er.endTest(et);
		er.flush();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		String screenshotPath= TestUtil.takeScreenshotAtEndOfTest();
		if(result.getStatus()==ITestResult.SUCCESS) {
			et.log(LogStatus.PASS, "Test Case Passed is "+ result.getName());
		
		}
		if(result.getStatus()==ITestResult.FAILURE) {
			et.log(LogStatus.FAIL, "Test Case Failed is "+ result.getName());
			et.log(LogStatus.FAIL, "Test Case Failed is "+ result.getThrowable());
		et.log(LogStatus.FAIL,"Abhi bus test Failed "+et.addScreenCapture(screenshotPath));
	}
		if(result.getStatus()==ITestResult.SKIP) {
			et.log(LogStatus.SKIP, "Test Case Skipped is "+ result.getName());
			
		}
		driver.quit();
	}
	
	
	
	
	
	
	
	
	

}
