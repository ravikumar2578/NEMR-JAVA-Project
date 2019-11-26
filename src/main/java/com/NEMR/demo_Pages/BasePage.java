package com.NEMR.demo_Pages;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Constants;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage 

	{
		WebDriver driver;
		ExtentTest test;
		//WebDriverWait wt= new WebDriverWait(driver, 30);
		public BasePage(WebDriver dr,ExtentTest t)
		{
			driver=dr;// single place in framework where driver is init
			test=t;		// screenshot
		}
		
		
		public void sendKeys(WebElement element, String text){
			element.sendKeys(text);
			
		}
		
		public void takeScreenShot(){
			// decide name - time stamp
			Date d = new Date();
			String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
			String path=Constants.SCREENSHOT_PATH+screenshotFile;
			// take screenshot
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				//FileUtils.copyFile(srcFile, new File(path));
				Files.copy(srcFile, new File(path));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			// embed
			test.log(LogStatus.INFO, test.addScreenCapture(path));
		}
		
		public void pause(int i) {
			try {
				Thread.sleep(1000*i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void wait( WebElement XYZ) {
			
			
			WebElement Practicepanal= driver.findElement(By.xpath("//a[@id='practise-panel']"));
			//wt.Until(ExpectedConditions.elementToBeClickable(XYZ));

		}

	}

