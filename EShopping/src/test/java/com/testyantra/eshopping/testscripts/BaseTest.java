package com.testyantra.eshopping.testscripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.testyantra.eshopping.genericlibs.FrameworkUtility;
import com.testyantra.eshopping.genericlibs.IAutoConstants;
import com.testyantra.eshopping.genericlibs.WebActionUtil;
import com.testyantra.eshopping.pom.HomePage;
import com.testyantra.eshopping.pom.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

//To perform some common Preconditions and Post Conditions
public class BaseTest implements IAutoConstants {
	
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	public HomePage hp;
	
	@Parameters({"browserName", "appUrl", "implicit", "explicit"})
	@BeforeClass(alwaysRun=true)
	public void openApp(@Optional(DEFAULT_BROWSER)String browserName,
						@Optional(APP_URL)String appUrl,
						@Optional(ITO)String implicit,
						@Optional(ETO)String explicit) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
		} else {
			Assert.fail("Browser Not Supported");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(implicit), TimeUnit.SECONDS);
		driver.get(appUrl);
		webActionUtil =  new WebActionUtil(driver, Long.parseLong(explicit));
	}
	
	@Parameters({"emailId", "emailPassword"})
	@BeforeMethod
	public void loginToApp(@Optional(DEFAULT_USER)String emailId, 
						   @Optional(DEFAULT_PASSWORD)String emailPassword) {
		LoginPage lp = new LoginPage(driver, webActionUtil);
		hp=lp.signIn(emailId, emailPassword);
	}
	
	@AfterMethod
	public void logoutFromApp(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			String path=FrameworkUtility.takeScreenshot(driver, result.getName());
			System.out.println(path);
		}		
		
		hp.signOut();
	}
	
	@AfterClass
	public void closeApp() {
		driver.quit();
	}
}
