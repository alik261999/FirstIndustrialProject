package com.main.Basic;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.main.Tests.ReadExcelData;
import com.test.Main.ExtentReport;

public class TransformPage {
	
	public static String[] info;
	public WebDriver driver;
	ExtentReport extent;
	
	public TransformPage(WebDriver driver,ExtentReport report) {
		this.driver = driver;
		this.extent = report;
	}
	
	public void navigateToHomePage() {
		extent.actionInfo("Navigating to Home Page");
		driver.findElement(By.xpath("//*[@id=\"rendered-content\"]/div[1]/div[1]/span[1]/div[1]/header[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/a[1]")).click();
		extent.actionPass("Landed to Home Page");
	}
	
	public void navigateToForm() {
		extent.actionInfo("Navigating to 'Ready to Transform' form");
		driver.findElement(By.partialLinkText("For Enterprise")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String parent = driver.getWindowHandle();
		WebElement ele = driver.findElement(By.partialLinkText("Products"));
		Actions ac = new Actions(driver);
		ac.moveToElement(ele).perform();
		driver.findElement(By.partialLinkText("For Campus")).click();
		
		Set<String> win =  driver.getWindowHandles();
		Iterator<String> itr = win.iterator();
		while(itr.hasNext()) {
			String child = itr.next();
			if(!parent.equals(child)) {
				driver.switchTo().window(child);
				
			}
		}
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scroll(0,4300)","");
		extent.actionInfo("Form is ready to fill");
		
	}
	
	public void fillForm() throws IOException {
		
		info = ReadExcelData.readExcelData();
		try {
			extent.actionInfo("First Name: "+info[0]);
			driver.findElement(By.id("FirstName")).sendKeys(info[0]);
			extent.actionInfo("Last Name: "+info[1]);
			driver.findElement(By.id("LastName")).sendKeys(info[1]);
			extent.actionInfo("Job Title: "+info[2]);
			driver.findElement(By.id("Title")).sendKeys(info[2]);
			extent.actionInfo("Email: "+info[3]);
			driver.findElement(By.id("Email")).sendKeys(info[3]);
			extent.actionInfo("Phone No.: "+info[4]);
			driver.findElement(By.id("Phone")).sendKeys("+91"+info[4]);
			extent.actionInfo("Company Name: "+info[5]);
			driver.findElement(By.id("Company")).sendKeys(info[5]);
			
			Select sel1 = new Select(driver.findElement(By.id("Institution_Type__c")));
			sel1.selectByVisibleText(info[6]);
			extent.actionInfo("Institution type: "+info[6]);
			
			Select sel2 = new Select(driver.findElement(By.id("Primary_Discipline__c")));
			sel2.selectByVisibleText(info[7]);
			extent.actionInfo("Discipline: "+info[7]);
			
			Select sel3 = new Select(driver.findElement(By.id("Country")));
			sel3.selectByVisibleText(info[8]);
			extent.actionInfo("Country: "+info[8]);
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("document.getElementById('State').value="+"'"+info[9]+"'");
			extent.actionInfo("State: "+info[9]);
			driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
			extent.actionInfo("Submitting the form");
		} catch (Exception e) {
			extent.actionInfo("Submitting the form");
			driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		}
		
		
	}
	
	public void getMessage() {
		try {
			extent.actionInfo("Fetching the submitting Msg");
			Thread.sleep(3000);
			String str = driver.findElement(By.xpath("//body[1]/main[1]")).getText();
			if(!str.startsWith("Prepare students with")) {
				extent.actionInfo(str);
				System.out.println(str);
			}
			else {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,-100)","");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String path = System.getProperty("user.dir") + "\\screenshot\\error_msg.png";
				// Now we take a screenshot
				FileUtils.copyFile(scrFile, new File(path));

				// Logging the action
				extent.screenShotInfo("Snapshot taken and saved to ", path);
				getErrorMsg();
			}
		}catch(Exception e) {
			e.getMessage();	
		}
		
	}
	
	public void getErrorMsg() throws InterruptedException {
		extent.actionInfo("Something is wrong in input data");
		List<WebElement> inp = driver.findElements(By.xpath("//input[contains(@class,'mktoRequired')]"));
		List<WebElement> sel = driver.findElements(By.tagName("select"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		for(int i=0;i<inp.size();i++) {
			String res1 = inp.get(i).getAttribute("aria-invalid");
			if(res1.equals("true")) {
				String txt1 = inp.get(i).getAttribute("aria-describedby");
				inp.get(i).sendKeys("");
				String err1 = js.executeScript("return document.getElementById('"+txt1+"').innerText").toString();
				System.out.println("\n"+txt1+": "+err1);
				extent.actionInfo(err1);
				//break;
			}
		}
		for(int j=0;j<sel.size();j++) {
			String res2 = sel.get(j).getAttribute("aria-invalid");
			if(res2.equals("true")) {
				String txt2 = js.executeScript("return arguments[0].getAttribute('aria-describedby')",sel.get(j)).toString();
				String err2 = js.executeScript("return document.getElementById('"+txt2+"').innerText").toString();
				System.out.println("\n"+txt2+": "+err2);
				extent.actionInfo(err2);
				//break;
			}
		}
		
	}

}
