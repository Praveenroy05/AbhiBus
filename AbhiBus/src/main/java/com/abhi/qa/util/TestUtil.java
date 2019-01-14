package com.abhi.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import com.abhi.qa.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 50;
	public static long IMPLICIT_WAIT = 50;
	public static long WEBDRIVER_WAIT=20;
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\ADMIN\\Downloads\\PageObjectModel-master\\PageObjectModel-master"
									+"\\src\\main\\java\\com\\crm\\qa\\testdata\\AbhiBusTestData.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	
	
	public void switchToFrame(){
		driver.switchTo().frame("mainpanel");
	}
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	public static String takeScreenshotAtEndOfTest() throws IOException {

		SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
		Date d=new Date();
		String ssname=sf.format(d);
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination= ssname+".png";
		File dest=new File(destination);
		FileUtils.copyFile(src,dest);
		return destination;
	}
	/*// To add passed Screenshots in Extent report
	public static void extentReportForPassedTestCase(ITestResult result) throws IOException {
		er=new ExtentReports("abhibuspassedresult.html",true);
		String screenshotPath= TestUtil.takeScreenshotAtEndOfTest();
		if(result.getStatus()==ITestResult.SUCCESS) {
			et.log(LogStatus.PASS, "Test Case Failed is "+ result.getName());
			et.log(LogStatus.PASS, "Test Case Failed is "+ result.getThrowable());
		et.log(LogStatus.PASS,"Abhi bus test passed "+et.addScreenCapture(screenshotPath));
		}
	}
	// To add failed sceenshots in Extent report
	public static void extentReportForFailedTestCase(ITestResult result) throws IOException {
		er=new ExtentReports("abhibuspassedresult.html",true);
		String screenshotPath= TestUtil.takeScreenshotAtEndOfTest();
		if(result.getStatus()==ITestResult.FAILURE) {
			et.log(LogStatus.FAIL, "Test Case Failed is "+ result.getName());
			et.log(LogStatus.FAIL, "Test Case Failed is "+ result.getThrowable());
		et.log(LogStatus.FAIL,"Abhi bus test Failed "+et.addScreenCapture(screenshotPath));
		}
	}*/

	

}
