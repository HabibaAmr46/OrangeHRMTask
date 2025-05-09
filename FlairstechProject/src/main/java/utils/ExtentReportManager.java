package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	private static ExtentReports report;
	private static ExtentTest test;

	public static void initReports() {
		report = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports.html");
		report.attachReporter(spark);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Extent Report");
		spark.config().setReportName("Automation-Practice Extent Report");

	}

	public static void createTest(String testcasename) {
		test = report.createTest(testcasename);
	}

	public static void info(String message) {
		if (test != null) {
			test.info(message);
		}
	}

	public static void pass(String message) {
		test.pass(message);
	}
	
	public static void fail(String message) {
		test.fail(message);
	}

	public static void flushReports() {
		report.flush();
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getName() + " got successfully passed");
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

}
