package listener;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import browserFactory.BrowserFactory;
import helper.Utility;

public class ExtentTestNGITestListener implements ITestListener {

	ExtentReports extent=ExtentManager.getInstance();
	ThreadLocal<ExtentTest> parentTest=new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result)
	{ExtentTest extemtTest =extent.createTest(result.getMethod().getMethodName());
	parentTest.set(extemtTest);
	}
	
	public void onTestSuccess(ITestResult result)
	{WebDriver driver=BrowserFactory.getBrowserInstance();
	String base64=Utility.captureScreenshotInBase64(driver);
		parentTest.get().pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
	}
	
	
	public void onTestFailure(ITestResult result)
	{
		WebDriver driver=BrowserFactory.getBrowserInstance();
		String base64=Utility.captureScreenshotInBase64(driver);
		parentTest.get().fail("Test failed"+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		parentTest.get().skip("Test skipped"+result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}
