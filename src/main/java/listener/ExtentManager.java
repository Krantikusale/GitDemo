package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helper.Utility;

public class ExtentManager {
	public static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = createInstance();
			return extent;
		} else {
			return extent;
		}
	}

	public static ExtentReports createInstance() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/reports/Automation_"+Utility.getCurrentTime()+".html");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setReportName("Automation Reports");
		sparkReporter.config().setDocumentTitle("Sprint 2 reports");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		return extent;
	}
}