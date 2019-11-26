package com.NEMR.demo_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.NEMR.demo_Pages.LoginPage;
import utils.Constants;
import com.relevantcodes.extentreports.ExtentTest;

public class LaunchPage extends BasePage {

	public LaunchPage(WebDriver dr, ExtentTest t) {
		super(dr, t);

	}
	/*
	 * public void openpage(){ driver.get(Constants.URL); LoginPage lp=new
	 * LoginPage(driver, test); }
	 * 
	 * }
	 * 
	 * 
	 * public class LaunchPage extends BasePage {
	 * 
	 * public LaunchPage(WebDriver dr,ExtentTest t){ super(dr,t); }
	 */

	public HomePage goToHomePage() {
		driver.get(Constants.URL);
		HomePage hp = new HomePage(driver, test);
		PageFactory.initElements(driver, hp);

		return hp;

	}

}
