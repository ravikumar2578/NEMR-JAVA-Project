package com.NEMR.demo_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.Constants;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	@FindBy(xpath = "//*[@id='age']")
	public WebElement ageField;

	@FindBy(id = "displayname")
	public WebElement nameField;

	@FindBy(id = "mobilenumber")
	public WebElement mobNumberField;

	@FindBy(xpath = "//*[@id='city_id']")
	public WebElement locationField;

	@FindBy(className = Constants.LOGIN_BUTTON)
	public WebElement Signup_button;

	@FindBy(xpath = "//*[@class='has-error']")
	public WebElement regError;

	public Object doReg(String age, String name, String mobNumber, String location) throws InterruptedException {
		Thread.sleep(3000);

		test.log(LogStatus.INFO, "Entering age ->" + age);
		Select ageSelect = new Select(ageField);
		ageSelect.selectByValue(age);

		test.log(LogStatus.INFO, "Entering name ->" + name);
		nameField.sendKeys(name);

		test.log(LogStatus.INFO, "Entering mobileNumber ->" + mobNumber);
		mobNumberField.clear();
		Thread.sleep(1000);
		mobNumberField.sendKeys(mobNumber);

		test.log(LogStatus.INFO, "Entering location ->" + location);
		// locationField.click();
		// Select locSelect = new Select(locationField);
		// locSelect.deselectByVisibleText(location);

		Signup_button.click();

		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		String title = driver.getTitle();
		System.out.println(title);
		boolean reg = false;

		if (title.equals("Extramarks: Log in") || regError.getText().contains("User account already exists"))
			reg = true;
		else
			reg = false;

		if (reg = false) {

			test.log(LogStatus.INFO, "Registration Fail");
			RegistrationPage rp = new RegistrationPage(driver, test);
			PageFactory.initElements(driver, rp);
			return rp;
		} else {
			test.log(LogStatus.INFO, "Registration successful");
			LoginPage loginPage = new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}
	}
}