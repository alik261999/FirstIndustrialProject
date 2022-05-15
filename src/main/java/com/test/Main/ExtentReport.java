package com.test.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReport {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	public static ExtentTest logger;
	static Properties p = new Properties();

	public ExtentReport() {
		
		FileReader reader;
		try {
			reader = new FileReader("project.properties");
			p.load(reader);
			testInfo();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testInfo() {

		if (htmlReporter == null && report == null) {
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/testReport.html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", p.getProperty("OS"));
			report.setSystemInfo("Environment", p.getProperty("Environment"));
			report.setSystemInfo("Build Number", p.getProperty("Version"));
			report.setSystemInfo("Browser", p.getProperty("Browser"));

			htmlReporter.config().setDocumentTitle("UAT UI Automation Result");
			htmlReporter.config().setReportName("UAT UI Automation Result");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

		}
	}
	
	public void createTest(String test) {
		//create test
		logger = report.createTest(test);
	}

	public void actionInfo(String action) {
		// Logging actions
		logger.log(Status.INFO, action);
	}

	public void actionPass(String action) {
		// Logging actions
		logger.log(Status.PASS, action);
	}

	public void actionFail(String action) {
		// Logging actions
		logger.log(Status.FAIL, action);
	}

	public void screenShotInfo(String action, String path) throws IOException {
		// Logging the screenshot
		logger.log(Status.INFO, action + logger.addScreenCaptureFromPath(path));
	}

	public void screenShotFail(String action, String path) throws IOException {
		// Logging the screenshot
		logger.log(Status.FAIL, action + logger.addScreenCaptureFromPath(path));
	}

	public void completeTest() {
		// writing everything to document
		report.flush();
	}

}
