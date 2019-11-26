package com.NEMR.demo_Pages;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.Constants;
import utils.DataUtil;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	@FindBy(id = Constants.LOGIN_USERNAME)
	public WebElement usernameField;

	@FindBy(className = Constants.LOGIN_BUTTON)
	public WebElement Submit_button;

	@FindBy(id = Constants.LOGIN_PASSWORD)
	public WebElement passwordField;

	public Object navigateToLoginRegPage(String username)
			throws InterruptedException, ClassNotFoundException, SQLException {

		List<HashMap<String, String>> dbObj = DataUtil.connectionsGetData();
		boolean isLoginPage = false;
		for (int i = 0; i < dbObj.size(); i++) {
			if (dbObj.get(i).get("MobileNumber").contains( String.valueOf(username))) {
				//System.out.println("Mobile Number already registered");
				isLoginPage = true;
			} else {

			}

		}

		test.log(LogStatus.INFO, "Entering username ->" + username);
		usernameField.sendKeys(username);
		Submit_button.click();

		if (isLoginPage == true) {
			System.out.println("Login page");
			LoginPage lp = new LoginPage(driver, test);
			PageFactory.initElements(driver, lp);
			return lp;

		} else {
			System.out.println("Registration page");
			RegistrationPage rp = new RegistrationPage(driver, test);
			PageFactory.initElements(driver, rp);
			return rp;

		}

	}
}
