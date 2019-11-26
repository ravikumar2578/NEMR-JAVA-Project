package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utils.Constants;
import utils.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {
	public ExtentReports rep;
	public static ExtentTest test;
	public static WebDriver driver;
	public Xls_Reader xls = new Xls_Reader(Constants.XLS_FILE_PATH);
	

		
	public static void openBrowser(String browser){
		
		
			// normal machine
			if(browser.equals("Mozilla"))
			{
				driver = new FirefoxDriver();	
			}
			else if(browser.equals("Chrome"))
			{
			System.setProperty("webdriver.chrome.driver",Constants.CHROME_PATH );
				//System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			}
			else if(browser.equals("ie"))
			{
				//System.setProperty("webdriver.ie.driver", Constants.IEDRIVER_PATH);
				driver = new InternetExplorerDriver();
				
			}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//test.log(LogStatus.INFO, "Opened the browser");
	}
}
