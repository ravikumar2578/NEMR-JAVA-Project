package com.NEMR.demo_Pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ForgotPasswordPage extends BasePage {
	public ForgotPasswordPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	@FindBy(xpath = "//*[@id='mobilenumber']")
	public WebElement mobileNum;

	@FindBy(xpath = "//*[@id='btn-forget']")
	public WebElement forgotBtn;

	@FindBy(xpath = "//*[@id='reset_code']")
	public WebElement resetCode;

	@FindBy(xpath = "//*[@id='new_password']")
	public WebElement newPassword;

	@FindBy(xpath = "//*[@id='verify_password']")
	public WebElement confPassword;

	@FindBy(xpath = "//*[@id='btn-reset']")
	public WebElement resetBtn;

	@FindBy(xpath = "//*[@id='forgotSuccMessage']")
	public WebElement forgotSuccess;

	@FindBy(xpath = "//*[@id='resetErrorMessage']")
	public WebElement forgotError;

	public Object forgotPassword(String mobNumber, String newPass, String confPass)
			throws InterruptedException, IOException {

		test.log(LogStatus.INFO, "Entering mobile number ->" + mobNumber);
		mobileNum.sendKeys(mobNumber);
		Thread.sleep(1000);
		forgotBtn.click();
		Thread.sleep(3000);
		System.out.println("Enter Reset Code");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();

		test.log(LogStatus.INFO, "Entering resetCode ->" + code);
		resetCode.sendKeys(code);

		test.log(LogStatus.INFO, "Entering new password ->" + newPass);
		newPassword.sendKeys(newPass);

		test.log(LogStatus.INFO, "Entering conf password ->" + confPass);
		confPassword.sendKeys(confPass);

		resetBtn.click();

		Thread.sleep(4000);

		boolean fp = false;

		if (forgotSuccess.getText().contains("Please login with new password")
				|| forgotError.getText().contains("Invalid verification code")) {
			fp = true;
		} else {

			fp = false;
		}
		if (fp == false)

		{

			test.log(LogStatus.INFO, "ForgotPassword Fail");
			ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver, test);
			PageFactory.initElements(driver, forgotPage);
			return forgotPage;
		} else {

			test.log(LogStatus.INFO, "ForgotPassword successful");
			LoginPage loginPage = new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}
	}

}
