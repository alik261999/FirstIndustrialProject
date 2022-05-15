package com.main.Basic;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {

	// Create properties reference to read from properties file
	static Properties p = new Properties();

	// Constructor to initialize reader object with our properties file and load it
	public DriverSetup() throws Exception {
		FileReader reader = new FileReader("project.properties");
		p.load(reader);
	}
	
	public WebDriver getWebDriver() {
		
		if((p.getProperty("Browser")).equalsIgnoreCase("Firefox")) {
			return getFirefoxDriver();
		}else if((p.getProperty("Browser")).equalsIgnoreCase("Edge")) {
			return getEdgeDriver();
		}else {
			return getChromeDriver();
		}
		
	}

	@SuppressWarnings("deprecation")
	public WebDriver getChromeDriver() {

		// Set driver properties (driverName, driverLocation)
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		// Get the value of baseURL by driver to open the website/web application
		try {
			driver.get(p.getProperty("baseUrl"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Maximize the opened window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	@SuppressWarnings("deprecation")
	public WebDriver getFirefoxDriver() {

		// Set driver properties (driverName, driverLocation)
		System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();

		// Get the value of baseURL by driver to open the website/web application
		try {
			driver.get(p.getProperty("baseUrl"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Maximize the opened window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	public WebDriver getEdgeDriver() {

		// Set driver properties (driverName, driverLocation)
		System.setProperty("webdriver.edge.driver", ".\\drivers\\msedgedriver.exe");
		EdgeDriver driver = new EdgeDriver();

		// Get the value of baseURL by driver to open the website/web application
		try {
			driver.get(p.getProperty("baseUrl"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Maximize the opened window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

}
