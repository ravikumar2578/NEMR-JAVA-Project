package com.NEMR.demo_Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LogOutPage extends BasePage {
	public LogOutPage(WebDriver dr, ExtentTest t) {
		super(dr, t);
	}

	@FindBy(xpath = "//*[@id='header']//*[@class='pull-left btn-group']/a")
	public WebElement logOutIcon;

	@FindBy(xpath = "//a[contains(text(),'Log Out')]")
	public WebElement logOutLink;
	
	@FindBy(id = "username")
	public WebElement userName;

	public Object doLogOut() throws InterruptedException {
		test.log(LogStatus.INFO, "Logging out ->");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(logOutIcon));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", logOutIcon);

		Thread.sleep(2000);
		logOutLink.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(userName));
		String title = driver.getTitle();
		System.out.println(title);
		boolean isLogin = false;

		if (title.trim().equalsIgnoreCase("Extramarks: Log in")) {
			isLogin = false;
		} else {
			isLogin = true;
		}

		if (isLogin == false) {
			test.log(LogStatus.INFO, "Logout Successfull");
			HomePage hp = new HomePage(driver, test);
			PageFactory.initElements(driver, hp);
			return hp;
		} else {
			test.log(LogStatus.INFO, "Logout Failed");
			DashBoardPage dp = new DashBoardPage(driver, test);
			PageFactory.initElements(driver, dp);
			return dp;
		}
	}

}
