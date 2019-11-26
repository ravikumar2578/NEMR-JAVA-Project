package com.NEMR.demo_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.Constants;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	@FindBy(id = Constants.LOGIN_USERNAME)
	public WebElement usernameField;

	@FindBy(id = Constants.LOGIN_PASSWORD)
	public WebElement passwordField;

	@FindBy(className = Constants.LOGIN_BUTTON)
	public WebElement Submit_button;

	@FindBy(xpath = "//*[@id='loginForm']//*[@class='form-group has-error']")
	public WebElement loginError;

	@FindBy(xpath = "//a[@href='/Extramarks-Application/registration']")
	public WebElement SignUpField;

	@FindBy(xpath = "//*[@class='forgot']")
	public WebElement forgotLink;

	public Object doLogin(String username, String password) throws InterruptedException {
		test.log(LogStatus.INFO, "Entering username ->" + username);
		Thread.sleep(2000);
		usernameField.clear();
		Thread.sleep(1000);
		usernameField.sendKeys(username);

		test.log(LogStatus.INFO, "Entering password ->" + password);
		passwordField.sendKeys(password);
		Submit_button.click();
		Thread.sleep(5000);
		String title = driver.getTitle();
		System.out.println(title);
		boolean login = false;
		// if(title.equals("Extramarks India"))
		String loginErrorMsg = "";
		try {
			loginErrorMsg = loginError.getText();
		} catch (Exception e) {

		}
		if (title.equals("Extramarks: Dashboard") || title.equals("Extramarks: Change Password")
				|| loginErrorMsg.contains("Incorrect username or password")
				|| loginErrorMsg.contains("User does not exist"))

			login = true;

		if (login = false) {
			test.log(LogStatus.INFO, "Login Fail");
			LoginPage loginPage = new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		} else {
			test.log(LogStatus.INFO, "Login successful");
			DashBoardPage dp = new DashBoardPage(driver, test);

			PageFactory.initElements(driver, dp);
			return dp;
		}
	}

	public RegistrationPage doReg() throws InterruptedException {

		RegistrationPage rp = new RegistrationPage(driver, test);
		PageFactory.initElements(driver, rp);
		Thread.sleep(2000);
		SignUpField.click();
		return rp;
	}

	public ForgotPasswordPage forgotPass() throws InterruptedException {

		ForgotPasswordPage fp = new ForgotPasswordPage(driver, test);
		PageFactory.initElements(driver, fp);
		Thread.sleep(2000);
		forgotLink.click();
		return fp;
	}

	public ChangePasswordPage changePass() throws InterruptedException {

		ChangePasswordPage cp = new ChangePasswordPage(driver, test);
		PageFactory.initElements(driver, cp);

		return cp;
	}
}
