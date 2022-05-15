package com.test.Main;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.main.Basic.DriverSetup;
import com.main.Basic.MainDriver;

public class WebDevelopment {
	
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
	public void searchWebCourse() {
		report.createTest("Searching Web Development Course Details");
		MainDriver md = new MainDriver(driver,report);
		md.searchCourse("Web Development Courses");
		report.actionPass("Web Development Courses are searched");
	}
	
	@Test(dependsOnMethods = "searchWebCourse")
	public void findWebCourse() {
		report.createTest("Sorting desired Web Development Courses");
		MainDriver md = new MainDriver(driver,report);
		try {
			md.findWebCourse();
			report.actionPass("Desired courses found");
		} catch (Exception e) {
			report.actionFail("Searching courses failed due to: "+e.getMessage());
		}
	}
	
	@Test(dependsOnMethods = "findWebCourse")
	public void findDetails() {
		report.createTest("Fetching Web Development Course Details");
		MainDriver md = new MainDriver(driver,report);
		try {
			md.getWebCourseDetails();
			report.actionPass("Course details recorder successfully");
		} catch (Exception e) {
			report.actionFail("Error encountered due to: "+e.getMessage());
		}
	}
	
	@AfterSuite
	public void closeDriver() {
		report.completeTest();
		driver.quit();
	}

}
