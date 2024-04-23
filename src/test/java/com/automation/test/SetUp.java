package com.automation.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.automation.pageObjects.ADSSRegressionSuitePgObjects;
import com.automation.utilities.PropertiesFile;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class SetUp {

	public static WebDriver driver;
	static ADSSRegressionSuitePgObjects adssRegPgObjs;
	public static Actions actions;
	public static String message="";
	public static String browserName="";
	public static Properties data = null;
	public static String scenarioName="";
	public static Scenario scenario1;
	public static Scenario scenarioToTest;
	static String browser;

	FileOutputStream fop = null;
	File file;

	static{
		DOMConfigurator.configure("log4j2.xml");
	}

	@Before
	public static void test(Scenario scenario) throws Exception{
		scenarioToTest = scenario;
		scenario1 = scenario;
		scenarioName = scenario.getName();
		data = PropertiesFile.readInputPropertiesFile();
		browser = System.getProperty("browser");
		if(browser==null){
		browser = data.getProperty("browser");
		}
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch (browser) {
		
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\exe\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
///*			chromeOptions.addArguments("--allow-running-insecure-content");*/
//			chromeOptions.addArguments("--disable-extensions");
//			chromeOptions.setExperimentalOption("useAutomationExtension", false);
//			chromeOptions.setExperimentalOption("excludeSwitches", 
//			Collections.singletonList("enable-automation"));
//			chromeOptions.addArguments("disable-infobars");
//			Map<String, Object> prefs = new HashMap<String, Object>();
//			prefs.put("credentials_enable_service", false);
//			prefs.put("profile.password_manager_enabled", false);
//
//			chromeOptions.setExperimentalOption("prefs", prefs);
//			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//			driver = new ChromeDriver(capabilities);
			//chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
			chromeOptions.addArguments("test-type","disable-extensions");
			chromeOptions.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
			chromeOptions.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
			//chromeOptions.addArguments("--headless"); // only if you are ACTUALLY running headless
			chromeOptions.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
			//chromeOptions.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
			//chromeOptions.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
			//chromeOptions.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
			chromeOptions.addArguments("--disable-gpu");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
			break;
		
//		case "IE":
//			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\exe\\IEDriverServer.exe");
//			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
//			capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
//			capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
//			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//			capabilities.setJavascriptEnabled(true);
//			capabilities.setCapability("ignoreProtectedModeSettings", true);
//			driver = new InternetExplorerDriver(capabilities);
//			break;
			
		default:
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\exe\\geckodriver.exe");
			driver=new FirefoxDriver();
			break;
		}
		adssRegPgObjs = PageFactory.initElements(driver, ADSSRegressionSuitePgObjects.class);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		actions = new Actions(driver);
	}
	
	@After
	public static void tearDown(Scenario result) throws IOException, InterruptedException {
		if(result.isFailed()){
			result.write(result.toString());
			result.write(result.getStatus());
			result.write("Screenshot taken for failed step");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\Screenshots\\" + scenarioName + "\\" + System.currentTimeMillis() + ".png"));
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			result.write(message);
			result.embed(screenshot, "image/png");
		}
		result.write(message);
		

		//driver.close();
		//driver.quit();

		
		if(browser.equalsIgnoreCase("Chrome")){
		Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
		}
		else if (browser.equalsIgnoreCase("IE")) {
			Runtime.getRuntime().exec("taskkill /f /im iedriver*");
		}
		else {
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		}
		
		Thread.sleep(3000);
	}

}
