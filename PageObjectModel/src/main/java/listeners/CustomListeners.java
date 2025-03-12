package listeners;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class CustomListeners implements ITestListener {

	// extentreport
	public ExtentReports rep = ExtentReportManager
			.createInstence("D:\\Selenium Projects\\PageObjectModel\\test-output\\Reports\\" + "Extent.html");
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		ExtentTest testrep = rep.createTest(result.getMethod().getMethodName());

		test.set(testrep);
	}

	/* Invoked each time a test succeeds. */
	public void onTestSuccess(ITestResult result) {

		String log = "Test case:- " + result.getName().toUpperCase() + " " + "Passed";
		Markup m = MarkupHelper.createLabel(log, ExtentColor.GREEN);
		test.get().pass(m);
		try {
			ExtentReportManager.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.get().pass("Screenshot",
				MediaEntityBuilder.createScreenCaptureFromPath(
						"D:\\Selenium Projects\\PageObjectModel\\test-output\\html\\" + ExtentReportManager.screenshotName)
						.build());
	}

	/* Invoked each time a test fails. */
	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			ExtentReportManager.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>" + "\n");

		String log = "Test case:- " + result.getName().toUpperCase() + " " + "Failed";
		Markup m = MarkupHelper.createLabel(log, ExtentColor.RED);
		test.get().log(Status.FAIL, m);
		test.get().fail("Screenshot for failure",
				MediaEntityBuilder.createScreenCaptureFromPath(
						"D:\\Selenium Projects\\PageObjectModel\\test-output\\html\\" + ExtentReportManager.screenshotName)
						.build());

		Reporter.log("Capturing scrrenshot!!!");
		Reporter.log("<a target=\"_blank\" href=" + ExtentReportManager.screenshotName + ">Screenshot</a>");
		Reporter.log("</br>");
		Reporter.log("<a target=\"_blank\" href=" + ExtentReportManager.screenshotName
				+ "><img height=200 width=200 src=" + ExtentReportManager.screenshotName + "></img></a>");
	}

	/* Invoked each time a test is skipped. */
	public void onTestSkipped(ITestResult result) {

		String logText = "<b>" + "Test Case:- " + result.getName().toUpperCase() + " SKIPPED" + "</b>";

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		test.get().skip(m);
	}

	/*
	 * Invoked each time a method fails but has been annotated with
	 * successPercentage and this failure.
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	/* Invoked each time a test fails due to a timeout. */
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	/*
	 * Invoked before running all the test methods belonging to the classes inside
	 */
	public void onStart(ITestContext context) {
		// ExtentTest testrep= rep.startTest("ExtentDemo");

	}

	/* Invoked after all the test methods belonging to the classes inside the */
	public void onFinish(ITestContext context) {
		if (rep != null) {
			rep.flush();
		}
	}
}
