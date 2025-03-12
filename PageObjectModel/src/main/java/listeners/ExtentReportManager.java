package listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BasePage;

public class ExtentReportManager {

	public static ExtentReports extent;
	public static String screenshotName;

	public static ExtentReports createInstence(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setReportName(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Automation Tester", "Naveen Kumar D");
		extent.setSystemInfo("Organization", "Selenium Tester");
		extent.setSystemInfo("Build No", "Selenium-123");

		return extent;
	}

	public static void captureScreenshot() throws IOException {

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshot = ((TakesScreenshot) BasePage.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
				new File("D:\\Selenium Projects\\PageObjectModel\\test-output\\html\\" + screenshotName));
	}
}
