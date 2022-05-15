package com.test.Main;

import java.util.List;

import org.testng.TestNG;

import com.beust.jcommander.internal.Lists;

public class Main {
	
	/*@Test(dependsOnMethods = {"findDetails","langLearn","sortByLanguage"})
	public void navigateToHome() {
		TransformPage tp = new TransformPage(driver,report);
		tp.navigateToHomePage();
	}*/
	

	public void invokeTest() {

		TestNG test = new TestNG();
		List<String> suite = Lists.newArrayList();
		suite.add("testng.xml");
		test.setTestSuites(suite);
		test.run();

	}

	public static void main(String[] args) {
		Main mn = new Main();
		mn.invokeTest();

	}

}
