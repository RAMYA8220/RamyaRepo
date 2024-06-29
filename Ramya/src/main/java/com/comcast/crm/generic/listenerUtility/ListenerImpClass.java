package com.comcast.crm.generic.listenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpClass implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public  ExtentReports report;
    public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) 
	{

		System.out.println("report configuration");

		// spark report config
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM test suite result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// Add env info & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WINDOWS-11");
		report.setSystemInfo("CHROME", "chrome-100");
	}

	@Override
	public void onFinish(ISuite suite) 
	{

		System.out.println("report backup");
		
		report.flush();
	}
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("======" + result.getMethod().getMethodName() + "===START===");
		test= report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+" started");

	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{

		System.out.println("======" + result.getMethod().getMethodName() + "===END===");
		test.log(Status.PASS, result.getMethod().getMethodName()+" completed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) UtilityClassObject.getDriver();
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		
		UtilityClassObject.getTest().addScreenCaptureFromBase64String(filepath, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+" failed");
	}

 	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
 		

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	
}
