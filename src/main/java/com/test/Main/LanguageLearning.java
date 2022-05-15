package com.test.Main;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.main.Basic.DriverSetup;
import com.main.Basic.MainDriver;

public class LanguageLearning {
	
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
	public void searchLangLearn() {
		report.createTest("Search language learning");
		MainDriver md = new MainDriver(driver,report);
		md.searchCourse("Language Learning");
		report.actionPass("Language learning courses are searched");
	}
	
	@Test(dependsOnMethods = "langLearn")
	public void sortByLanguage() {
		report.createTest("language based language learning");
		MainDriver md = new MainDriver(driver,report);
		try {
			md.sortLang();
			report.actionPass("All languages are explored");
		} catch (Exception e) {
			report.actionFail("Error encountered: "+e.getMessage());
		}
	}
	
	@Test(dependsOnMethods = "searchLangLearn")
	public void langLearn() throws InterruptedException {
		report.createTest("Level based language learning");
		MainDriver mn = new MainDriver(driver,report);
		mn.langLearn();
		report.actionPass("All levels are explored");
	}
	
	@AfterSuite
	public void closeDriver() {
		report.completeTest();
		driver.quit();
	}

}
