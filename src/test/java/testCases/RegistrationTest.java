package testCases;

import java.sql.SQLException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.NEMR.demo_Pages.HomePage;
import com.NEMR.demo_Pages.LaunchPage;
import com.NEMR.demo_Pages.LoginPage;
import com.NEMR.demo_Pages.RegistrationPage;
import com.relevantcodes.extentreports.LogStatus;

import utils.Constants;
import utils.DataUtil;
import utils.ExtentManager;
import utils.Xls_Reader;

public class RegistrationTest extends BaseTest {
	@BeforeMethod
	public void init() {
		rep = ExtentManager.getInstance();
		test = rep.startTest("RegistrationTest");

		// test = rep.startTest("Login Test");
	}

	@AfterMethod
	public void quit() {
		rep.endTest(test);
		rep.flush();

		/*
		 * if(driver !=null) driver.quit();
		 */
	}

	@Test(dataProvider = "getData")
	public void doregisteration(Hashtable<String, String> data)
			throws InterruptedException, ClassNotFoundException, SQLException {
		String expectedResult = "RegistrationTest_Pass";
		String actualResult = "";
		String browser = data.get("Browser");
		test.log(LogStatus.INFO, "Registration test started");
		if (!DataUtil.isTestRunnable(xls, "RegistrationTest") || data.get("Runmode").equals("N")) {

			test.log(LogStatus.SKIP, "Skipping the test");

			throw new SkipException("skipping the test");

			// throw new SkipException("Skipping the test");
		}

		// System.out.println("test skip code");

		openBrowser(browser);
		test.log(LogStatus.INFO, "Browser Opened");
		LaunchPage launch = new LaunchPage(driver, test);
		HomePage hp = launch.goToHomePage();
		Object resultPage2 = hp.navigateToLoginRegPage(data.get("MobileNumber"));
		RegistrationPage rp = new RegistrationPage(driver, test);

		String title = driver.getTitle();
		System.out.println(title);
		test.log(LogStatus.INFO, "NEMR Registration Page");
		rp.takeScreenShot();
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Doing registration");
		if (resultPage2 instanceof RegistrationPage) {

			rp = (RegistrationPage) resultPage2;
			Thread.sleep(2000);
			Object resultPage = rp.doReg(data.get("Age"), data.get("Name"), data.get("MobileNumber"),
					data.get("Location"));

		} else {
			LoginPage lp = (LoginPage) resultPage2;
			rp = lp.doReg();
			Object resultPage = rp.doReg(data.get("Age"), data.get("Name"), data.get("MobileNumber"),
					data.get("Location"));

		}

		/*
		 * if (!expectedResult.equals(actualResult)) { // take screenshot
		 * lp.takeScreenShot(); test.log(LogStatus.PASS, "Login Test Passed");
		 * 
		 * }
		 * 
		 * test.log(LogStatus.PASS, "Login Test Passed");
		 */
		Thread.sleep(1000);

	}

	@DataProvider
	public Object[][] getData() {
		Xls_Reader xls = new Xls_Reader(Constants.XLS_FILE_PATH);
		Object[][] data = DataUtil.getData(xls, "RegistrationTest");
		return data;
	}
}
