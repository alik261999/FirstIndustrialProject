package com.main.Basic;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.Main.ExtentReport;


public class MainDriver {
	
	public WebDriver driver;
	ExtentReport extent;
	public MainDriver(WebDriver driver,ExtentReport extent) {
		this.driver = driver;
		this.extent = extent;
	}
	
	public void findWebCourse() throws InterruptedException, IOException {
		
		Thread.sleep(5000);
		//Selecting level of the course
		extent.actionInfo("Selecting level of the course");
		driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]/span[1]/input[1]")).click();
		driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[7]/div[1]/button[1]")).click();
		Thread.sleep(1000);
		//Selecting preffered language
		extent.actionInfo("Selecting preffered language");
		driver.findElement(By.xpath("//body[1]/div[10]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[5]/div[1]/label[1]/span[1]/span[1]/input[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body[1]/div[10]/div[3]/div[1]/div[1]/div[2]/div[3]/button[1]")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,50)","");
		Thread.sleep(3000);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshot\\web_development.png";
		// Now we take a screenshot
		FileUtils.copyFile(scrFile, new File(path));

		// Logging the action
		extent.screenShotInfo("Snapshot taken and saved to ", path);
		
	}
	
	public void getWebCourseDetails() throws InterruptedException {
		extent.actionInfo("Fetching first two desired course details");
		Thread.sleep(1000);
		String parent = driver.getWindowHandle();
		System.out.println("***********Web Development Courses***********");
		//printing course details
		for(int i=1;i<=2;i++) {
			String cname = driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li["+i+"]/div[1]/div[1]/a[1]/div[1]/div[2]/h2[1]")).getText();
			String rate = driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li["+i+"]/div[1]/div[1]/a[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/span[1]")).getText();
			System.out.println("Course Name: "+cname+"\nRatings: "+rate);
			extent.actionInfo("Printing details of "+cname);
			driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li["+i+"]/div[1]/div[1]/a")).click();
			Set<String> win =  driver.getWindowHandles();
			Iterator<String> itr = win.iterator();
			while(itr.hasNext()) {
				String child = itr.next();
				if(!parent.equals(child)) {
					driver.switchTo().window(child);
					String time = driver.findElement(By.xpath("//div[contains(@class,'_16ni8zai')]/span[contains(text(),'to complete')]")).getAttribute("innerHTML");
					System.out.println(time);
					driver.close();
				}
			}
			System.out.println();
			driver.switchTo().window(parent);
		}
		
		
		System.out.println("*********************************************\n");
	}
	
	public void searchCourse(String sub) {
		WebElement ele = driver.findElement(By.xpath("//input[contains(@placeholder,'What do you want to learn?')]"));
		ele.sendKeys(sub);
		ele.sendKeys(Keys.ENTER);
		extent.actionInfo("Searching "+sub);
	}

	public void langLearn() throws InterruptedException {
		extent.actionInfo("Finding Language learning courses based on level");
		System.out.println("\nLanguage learning course details...");
		Thread.sleep(5000);
		for(int i=1;i<=4;i++) {
			//System.out.println(i);
			driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/fieldset[1]/div[1]/div["+i+"]/div[1]/div[1]/label[1]/span[1]/span[1]/input[1]")).click();
			Thread.sleep(3000);
			String lvl = driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[2]")).getText();
			String num = driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/h1[1]/span[1]")).getText();
			num = num.split(" ")[0];
			System.out.println(lvl+" courses available are: "+num);
			extent.actionInfo(lvl+" courses available are: "+num);
			try {
				driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/button[1]")).click();
			}catch(Exception e) {
				driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]/span[1]/input[1]")).click();
			}
			Thread.sleep(3000);
		}
	}
	
	public void sortLang() throws Exception {
		extent.actionInfo("Finding courses available in different languages");
		String rec = "";
		driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[7]/div[1]/button[1]/span[1]")).click();
		int len = driver.findElements(By.xpath("//div[@id='checkbox-group']/div")).size();
		System.out.println("\nAvailable Courses of Language...");
		for(int i=1;i<=len;i++) {
			String lang = driver.findElement(By.xpath("//body[1]/div[10]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div["+i+"]/div[1]/label[1]/span[2]")).getText();
			driver.findElement(By.xpath("//body[1]/div[10]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div["+i+"]/div[1]/label[1]/span[1]/span[1]/input[1]")).click();
			driver.findElement(By.xpath("//body[1]/div[10]/div[3]/div[1]/div[1]/div[2]/div[3]/button[1]")).click();
			Thread.sleep(2000);
			String str = driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/h1[1]/span[1]")).getText();
			str = str.split(" ")[0];
			System.out.println("Courses available in "+lang+": "+str);
			rec += (lang+": "+str+", ");
			try {
				driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[7]/div[1]/button[1]")).click();
			}catch(Exception e) {
				driver.findElement(By.xpath("//body[1]/div[3]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/button[1]")).click();
			}
			driver.findElement(By.xpath("//body[1]/div[10]/div[3]/div[1]/div[1]/div[2]/div[3]/button[2]")).click();
		}
		driver.findElement(By.xpath("//body[1]/div[10]/div[3]/div[1]/div[1]/div[1]/button[1]")).click();
		extent.actionInfo("Recorded results:\n"+rec);
	}

}
