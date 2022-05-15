package com.test.Main;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.main.Basic.DriverSetup;
import com.main.Basic.TransformPage;

public class TransFormation {
	
	public static WebDriver driver=null;
	ExtentReport report = new ExtentReport();
	
	@BeforeSuite
	public void ManageDriver() throws Exception {
		
		DriverSetup obj = new DriverSetup();
		report.createTest("Invoke Browser");
		report.actionInfo("Launching driver");
		driver = obj.getWebDriver();
		report.actionPass("Diver launched Successfully");
	}
	
	@Test
	public void navigatePage() {
		report.createTest("Navigate to Form");
		TransformPage tp = new TransformPage(driver,report);
		tp.navigateToForm();
		report.actionPass("Time to fill the form");
		
	}
	
	@Test(dependsOnMethods = "navigatePage")
	public void fillForm() {
		report.createTest("Filling Ready to Transform form");
		TransformPage tp = new TransformPage(driver,report);
		try {
			tp.fillForm();
			report.actionPass("Form submitted");
		} catch (IOException e) {
			report.actionFail("Error encountered: "+e.getMessage());
		}
	}
	
	@Test(dependsOnMethods = "fillForm")
	public void fetchMessage() {
		report.actionInfo("Fetching Message");
		TransformPage tp = new TransformPage(driver,report);
		tp.getMessage();
		report.actionPass("Message printed successfully");
	}
	
	@AfterSuite
	public void closeDriver() {
		report.completeTest();
		driver.quit();
	}

}
