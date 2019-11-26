package com.NEMR.demo_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class DashBoardPage extends BasePage {
	public DashBoardPage(WebDriver dr, ExtentTest t) {
		super(dr, t);
	}

	public LogOutPage logOut() {

		LogOutPage lout = new LogOutPage(driver, test);
		PageFactory.initElements(driver, lout);

		return lout;

	}

}
