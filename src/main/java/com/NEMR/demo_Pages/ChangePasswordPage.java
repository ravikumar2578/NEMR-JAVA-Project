package com.NEMR.demo_Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ChangePasswordPage extends BasePage {
	public ChangePasswordPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	@FindBy(xpath = "//*[@id='old_password']")
	public WebElement oldPassword;

	@FindBy(xpath = "//*[@id='changePasswordForm']//input[@id='new_password']")
	public WebElement newPassword;

	@FindBy(xpath = "//*[@id='changePasswordForm']//input[@id='verify_password']")
	public WebElement confPassword;

	@FindBy(xpath = "//*[@id='changePasswordForm']//button")
	public WebElement changePasswordBtn;

	@FindBy(xpath = "//*[@class='has-error']")
	public WebElement changePassError;

	@FindBy(xpath = "//*[@id='loginForm']//*[@class='form-group ']")
	public WebElement changePassSuccess;

	public Object changePassword(String oldPass, String newPass, String confPass)
			throws InterruptedException, IOException {
		Thread.sleep(2000);

		test.log(LogStatus.INFO, "Entering old password ->" + oldPass);
		oldPassword.sendKeys(oldPass);

		Thread.sleep(1000);

		test.log(LogStatus.INFO, "Entering new password ->" + newPass);
		newPassword.sendKeys(newPass);

		test.log(LogStatus.INFO, "Entering conf password ->" + confPass);
		confPassword.sendKeys(confPass);

		changePasswordBtn.click();

		Thread.sleep(5000);

		boolean cp = false;
		String changePassErr = "";
		String changePassSucc = "";

		try {
			
			changePassSucc = changePassSuccess.getText();
			
		} catch (Exception e) {
			changePassErr = changePassError.getText();
		}
		Thread.sleep(5000);
		if (changePassErr.contains("New password must be at least 8 characters long")
				|| changePassErr.contains("Incorrect username or password")
				|| changePassErr.contains("Passwords do not match")
				|| changePassSucc.contains("Please logged in with new password")) {
			cp = true;
		} else {
			cp = false;
		}

		if (cp == false) {

			test.log(LogStatus.INFO, "Change Password Fail");
			ChangePasswordPage changePage = new ChangePasswordPage(driver, test);
			PageFactory.initElements(driver, changePage);
			return changePage;
		} else {
			test.log(LogStatus.INFO, "Change Password successful");
			LoginPage loginPage = new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}
	}

}
