package testCases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.NEMR.demo_Pages.ForgotPasswordPage;
import com.NEMR.demo_Pages.HomePage;
import com.NEMR.demo_Pages.LaunchPage;
import com.NEMR.demo_Pages.LoginPage;
import com.NEMR.demo_Pages.RegistrationPage;
import com.relevantcodes.extentreports.LogStatus;

import utils.Constants;
import utils.DataUtil;
import utils.ExtentManager;
import utils.Xls_Reader;

public class ForgotPasswordTest extends BaseTest {

	/*
	 * private static final boolean True = false; WebDriver driver;
	 */
	@BeforeMethod
	public void init() {
		rep = ExtentManager.getInstance();
		test = rep.startTest("ForgotPasswordTest");

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

	@Test(dataProvider = "getData", priority = 1)
	public void doLogin(Hashtable<String, String> data)
			throws InterruptedException, IOException, ClassNotFoundException, SQLException {
		String expectedResult = "ForgotPassword_PASS";
		String actualResult = "";
		String browser = data.get("Browser");
		test.log(LogStatus.INFO, "ForgotPassword test started");
		if (!DataUtil.isTestRunnable(xls, "ForgotPasswordTest") || data.get("Runmode").equals("N")) {

			test.log(LogStatus.SKIP, "Skipping the test");

			throw new SkipException("skipping the test");

			// throw new SkipException("Skipping the test");
		}

		// System.out.println("test skip code");

		openBrowser(browser);
		test.log(LogStatus.INFO, "Browser Opened");
		LaunchPage launch = new LaunchPage(driver, test);
		HomePage hp = launch.goToHomePage();
		Object resultPage2 = hp.navigateToLoginRegPage(data.get("MobNumber"));
		LoginPage lp = new LoginPage(driver, test);
		if (resultPage2 instanceof LoginPage) {

			lp = (LoginPage) resultPage2;
		} else {

			RegistrationPage rp = new RegistrationPage(driver, test);
			rp = (RegistrationPage) resultPage2;
			PageFactory.initElements(driver, rp);
			Object lObj = rp.doReg("22", "TestUser", data.get("Username"), "Noida");
			if (lObj instanceof LoginPage) {
				lp = (LoginPage) lObj;
			} else {
				System.out.println("Unable to register mobile number");
			}

		}
		String title = driver.getTitle();
		System.out.println(title);
		test.log(LogStatus.INFO, "ForgotPassword Page");
		lp.takeScreenShot();
		ForgotPasswordPage fp = lp.forgotPass();

		test.log(LogStatus.INFO, "Forgot Password page");

		Object forgotPage = fp.forgotPassword(data.get("MobNumber"), data.get("NewPassword"), data.get("ConfPassword"));

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
		Object[][] data = DataUtil.getData(xls, "ForgotPasswordTest");
		return data;
	}

}
