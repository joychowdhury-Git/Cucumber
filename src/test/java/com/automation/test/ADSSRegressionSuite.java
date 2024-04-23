
package com.automation.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import com.automation.interfaces.ExecuteCommand;
import com.automation.pageObjects.ADSSRegressionSuitePgObjects;
import com.automation.utilities.ActionMethods;
import com.automation.utilities.GlobalVars;
import com.automation.utilities.PropertiesFile;
import com.automation.utilities.Utils;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ADSSRegressionSuite extends GlobalVars {

	static Logger log = Logger.getLogger(ADSSRegressionSuite.class);
	static Scenario scenario = SetUp.scenarioToTest;
	ActionMethods actions = new ActionMethods();
	Actions action = new Actions(SetUp.driver);
	ADSSRegressionSuitePgObjects adssRegressionSuitePgObjects = SetUp.adssRegPgObjs;
	WebDriver driver = SetUp.driver;
	public static Properties data = null;
	String totalPrice;
	String TotalPriceAdit;
	private final static String notes= "This is a test Automation order";
	String orderId;
	String draftId;
	String orderIdAfterEdit;
	String TotalPriceAditRenewAd;
	String TotalOrderPriceAdit;
	String designAdMaterialPreviewSourceLink;
	//Partha
	String queryString;
	//Preetham
	String category;
	String townname;
	String statename;

	ArrayList<ArrayList> RunDatesList = new ArrayList<ArrayList>();
	ArrayList<String> RunDatesListAdit = new ArrayList<String>();
	ArrayList<String> OrderPrice = new ArrayList<String>();
	ArrayList<String> OrderLinesPriceAdit = new ArrayList<>();
	ArrayList<ArrayList> RunDatesListEdit = new ArrayList<ArrayList>();
	ArrayList<String> RunDatesListAditEditAd = new ArrayList<String>();
	ArrayList<ArrayList> RunDatesListRenewAd = new ArrayList<ArrayList>();
	ArrayList<String> RunDatesListAditRenewAd = new ArrayList<String>();
	ArrayList<String> RelativeproductsInAdit = new ArrayList<String>();//partha

	public void waitForPageToLoad() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			return; 
		} 

	}


	@Given("^Open the URL to test the \"([^\"]*)\"  Purchase Functionality$")
	public void open_the_URL_to_test_the_Purchase_Functionality(String url) throws Throwable {
		try {
			String environment;
			String urlToTest;
			data = PropertiesFile.readInputPropertiesFile();
			try {
				environment = System.getProperty("environment");
				urlToTest = url + environment + "URL";
				driver.get(data.getProperty(urlToTest));
				//Thread.sleep(2000);
				driver.navigate().refresh();
				Thread.sleep(6000);
			} catch (NullPointerException e) {
				environment = "QA";
				urlToTest = url + environment + "URL";
				driver.get(data.getProperty(urlToTest));
			}
			waitForPageToLoad();
			actions.takeScreenshot(driver);
			log.info("Able to open the URL to test the " + url +  " Functionality");
		} catch (Exception e) {
			log.error("Unable to open the URL to test the " + url +  " Functionality");
			scenario.write("Unable to open the URL to test the " + url +  " Functionality");
			throw e;
		}
	}

	@When("^click on the \"([^\"]*)\" link at the top right corner of the page$")
	public void click_on_the_link_at_the_top_right_corner_of_the_page(String adminPgLink) throws Throwable {
		try {
			Thread.sleep(5000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.adminPgLinkITopRight(adminPgLink));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.adminPgLinkITopRight(adminPgLink));
			Thread.sleep(1000);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.adminPgLinkITopRight(adminPgLink));
			
			actions.click(adssRegressionSuitePgObjects.adminPgLinkITopRight(adminPgLink));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + adminPgLink + " link at the top right corner of the page");
		} catch (Exception e) {
			log.error("Unable to click on the " + adminPgLink + " link at the top right corner of the page");
			scenario.write("Unable to click on the " + adminPgLink + " link at the top right corner of the page");
			throw e;			
		}
	}

	@When("^enter required \"([^\"]*)\" in the \"([^\"]*)\" field in the sign in window$")
	public void enter_required_in_the_field_in_the_sign_in_window(String dataToEnter, String inputField) throws Throwable {
		try {
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.inputFieldInSignwindowList(inputField));
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 5000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField));
			actions.click(adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField));
			String environment=System.getProperty("environment");
			String localDataToEnter = dataToEnter;
			if(environment==null || (environment.trim().equalsIgnoreCase("QA"))){
				dataToEnter = dataToEnter + "QA";
			}
			else if (environment.trim().equalsIgnoreCase("STAGE")) {
				dataToEnter = dataToEnter + "STAGE";
			}
			else if (environment.trim().equalsIgnoreCase("PROD")) {
				// do nothing
			}
			else {
				Assert.fail("Correct environemnnt is not provided");
			}
			String dataToType = null;
			if(environment==null || (environment.trim().equalsIgnoreCase("QA"))){
				if(dataToEnter.trim().contains("User")){
					if(localDataToEnter.trim().equalsIgnoreCase("LocUser")){
						dataToType = System.getProperty("ADSSLOCUSERNAME");
							if(dataToType==null){
							dataToType = data.getProperty(dataToEnter);
						}
					}
					else if (localDataToEnter.trim().equalsIgnoreCase("NonLocUser")) {
						dataToType = System.getProperty("ADSSNONLOCUSERNAME");
							if(dataToType==null){
							dataToType = data.getProperty(dataToEnter);
						}
					}
					else {
						Assert.fail("Wrong data is provided");
					}
				}
				else if (dataToEnter.trim().contains("Password")) {
					if(localDataToEnter.trim().equalsIgnoreCase("LocPassword")){
						dataToType = System.getProperty("ADSSLOCPASSWORD");
						if(dataToType==null){
							dataToType = data.getProperty(dataToEnter);
						}
					}
					else if (localDataToEnter.trim().equalsIgnoreCase("NonLocPassword")) {
						dataToType = System.getProperty("ADSSNONLOCPASSWORD");
						if(dataToType==null){
							dataToType = data.getProperty(dataToEnter);
						}
					}
					else {
						Assert.fail("Wrong data is provided");
					}
				}
				else {
					Assert.fail("Data provided and/or input fields are not correct");
				}
				if(dataToType==null){
					actions.type(adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField), data.get(dataToEnter));
				}
				else {
					actions.type(adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField), dataToType);
				}
				if(dataToType==null){
					Assert.assertEquals(data.get(dataToEnter), adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField).getAttribute("value").trim());
				}
				else {
					//Assert.assertEquals(dataToType, adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField).getAttribute("value").trim());
				}
			}
			else if (environment.trim().equalsIgnoreCase("STAGE")) {
				if(dataToEnter.trim().contains("User")){
					if(localDataToEnter.trim().equalsIgnoreCase("LocUser")){
						dataToType = System.getProperty("ADSSLOCUSERNAME");
						if(dataToType==null){
							dataToType = data.getProperty(dataToEnter);
						}
					}
					else if (localDataToEnter.trim().equalsIgnoreCase("NonLocUser")) {
						dataToType = System.getProperty("ADSSNONLOCUSERNAME");
						if(dataToType==null){
							dataToType = data.getProperty(dataToEnter);
						}
					}
					else {
						Assert.fail("Wrong data is provided");
					}
				}
				else if (dataToEnter.trim().contains("Password")) {
					if(localDataToEnter.trim().equalsIgnoreCase("LocPassword")){
						dataToType = System.getProperty("ADSSLOCPASSWORD");
						if(dataToType==null){
							dataToType = data.getProperty(dataToEnter);
						}
					}
					else if (localDataToEnter.trim().equalsIgnoreCase("NonLocPassword")) {
						dataToType = System.getProperty("ADSSNONLOCPASSWORD");
						if(dataToType==null){
							dataToType = data.getProperty(dataToEnter);
						}
					}
					else {
						Assert.fail("Wrong data is provided");
					}
				}
				else {
					Assert.fail("Data provided and/or input fields are not correct");
				}
				if(dataToType==null){
					actions.type(adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField), data.get(dataToEnter));
				}
				else {
					actions.type(adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField), dataToType);
				}
				if(dataToType==null){
					Assert.assertEquals(data.get(dataToEnter), adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField).getAttribute("value").trim());
				}
				else {
					Assert.assertEquals(dataToType, adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField).getAttribute("value").trim());
				}

			}
			else if (environment.trim().equalsIgnoreCase("PROD")){
				if(dataToEnter.trim().contains("User")){
					if(localDataToEnter.trim().equalsIgnoreCase("LocUser")){
						dataToType = System.getProperty("ADSSLOCUSERNAME");
					}
					else if (localDataToEnter.trim().equalsIgnoreCase("NonLocUser")) {
						dataToType = System.getProperty("ADSSNONLOCUSERNAME");
					}
					else {
						Assert.fail("Wrong data is provided");
					}
				}
				else if (dataToEnter.trim().contains("Password")) {
					if(localDataToEnter.trim().equalsIgnoreCase("LocPassword")){
						dataToType = System.getProperty("ADSSLOCPASSWORD");
					}
					else if (localDataToEnter.trim().equalsIgnoreCase("NonLocPassword")) {
						dataToType = System.getProperty("ADSSNONLOCPASSWORD");
					}
					else {
						Assert.fail("Wrong data is provided");
					}
				}
				else {
					Assert.fail("Data provided and/or input fields are not correct");
				}
				Assert.assertNotEquals(null, dataToType);
				actions.type(adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField), dataToType);
				Assert.assertEquals(dataToType, adssRegressionSuitePgObjects.inputFieldInSignwindow(inputField).getAttribute("value").trim());
			}
			else {
				Assert.fail("Correct environment is not provided");
			}
			actions.takeScreenshot(driver);
			log.info("Able to enter data in the input field " + inputField + " in the sign in window");
		} catch (Exception e) {
			log.error("Problem in entering data in the input field " + inputField + " in the sign in window");
			scenario.write("Problem in entering data in the input field " + inputField + " in the sign in window");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to enter data in the input field " + inputField + " in the sign in window");
			scenario.write("Unable to enter data in the input field " + inputField + " in the sign in window");
			throw e;
		}
	}


	@When("^click on \"([^\"]*)\" button in the login window$")
	public void click_on_button_in_the_login_window(String btnInLoginWindow) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.btnInLoginWindow(btnInLoginWindow));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.btnInLoginWindow(btnInLoginWindow));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.btnInLoginWindow(btnInLoginWindow));
			actions.click(adssRegressionSuitePgObjects.btnInLoginWindow(btnInLoginWindow));
			actions.takeScreenshot(driver);
			waitForPageToLoad();
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + btnInLoginWindow + " button in the login window");
		} catch (Exception e) {
			Assert.fail();
			log.error("Unable to click on the " + btnInLoginWindow + " button in the login window");
			scenario.write("Unable to click on the " + btnInLoginWindow + " button in the login window");
			
			throw e;
		}
	}

	@Then("^click on the Schedule tab$")
	public void click_on_the_Schedule_tab() throws Throwable {
		try {
			Thread.sleep(10000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.scheduleTab);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.scheduleTab);
			
			actions.click(adssRegressionSuitePgObjects.scheduleTab);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Schedule Tab");
		} catch (Exception e) {
			log.error("Unable to click on the Schedule Tab");
			scenario.write("Unable to click on the Schedule Tab");
			throw e;
		}
	}
	@Then("^Scroll to Schedule tab$")
	public void Scroll_to_Schedule_tab() throws Throwable {
		try {
			
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.scheduleTab);
			actions.scrollUp(driver);
			//actions.scrollToElement(driver, adssRegressionSuitePgObjects.scheduleTab);
			Thread.sleep(2000);
		} catch (Exception e) {
			log.error("Unable to scrool to the Schedule Tab");
			scenario.write("Unable to scrool to the Schedule Tab");
			throw e;
		}
	}

	@Then("^clear the default dates$")
	public void clear_the_default_dates() throws Throwable {
		try {
			actions.waitUntilListOfElementsVisible(driver, adssRegressionSuitePgObjects.defaultDates);
			Assert.assertTrue(adssRegressionSuitePgObjects.defaultDates.size()>0);
			try {
				for (int i=adssRegressionSuitePgObjects.defaultDates.size()-1;i>=0;i--)
				{
					try {Thread.sleep(2000);}catch(Exception e) {}
					//defaultDates.get(i).click();
					actions.scrollToElement(driver, adssRegressionSuitePgObjects.defaultDates.get(i));
					Thread.sleep(2000);
					actions.javascriptClk(driver, adssRegressionSuitePgObjects.defaultDates.get(i));
				}
			} catch (Exception e) {
				for (int i=adssRegressionSuitePgObjects.defaultDates.size()-1;i>=0;i--)
				{
					actions.scrollToElement(driver, adssRegressionSuitePgObjects.defaultDates.get(i));
					adssRegressionSuitePgObjects.defaultDates.get(i).click();
				}
			}
			log.info("Able to clear the default Pub-Dates");
		} catch (Exception e) {
			log.error("Unable to clear the default Pub-Dates");
			scenario.write("Unable to clear the default Pub-Dates");
			throw e;
		}
		catch (AssertionError e) {
			log.error("No Default dates are present");
			scenario.write("No Default dates are present");
			throw e;
		}
	}

	@Then("^select one calendar date$")
	public void select_one_calendar_date() throws Throwable {
		try {
			if (adssRegressionSuitePgObjects.calendarDates.size()>0) {

				try {Thread.sleep(2000);}catch(Exception e) {}
				/*for (int i=0;i<calendarDates.size();i++)
				{
					if(!calendarDates.get(i).getAttribute("class").contains("ui-state-disabled"))
					{	
						calendarDates.get(i).click();
						break;
					}
				}*/
				try{
				adssRegressionSuitePgObjects.calendarDates.get(adssRegressionSuitePgObjects.calendarDates.size()-3).click();
				}
				catch(WebDriverException e){
					actions.javascriptClk(driver, adssRegressionSuitePgObjects.calendarDates.get(adssRegressionSuitePgObjects.calendarDates.size()-3));
				}

			} 
			actions.takeScreenshot(driver);
			log.info("New Pub-Dates Selected");
		} catch (Exception e) {
			log.error("Unable to select one calendar date");
			scenario.write("Unable to select one calendar date");
			throw e;
		}
	}

	@Then("^click Layout tab$")
	public void click_Layout_tab() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.layoutTab);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.layoutTab);
			Thread.sleep(5000);
			actions.click(adssRegressionSuitePgObjects.layoutTab);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Layout tab");
		} catch (Exception e) {
			log.error("Unable to click on the Layout tab");
			scenario.write("Unable to click on the Layout tab");
			throw e;
		}
	}

	@Then("^select Ad Size$")
	public void select_Ad_Size() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.adSizeId);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.adSizeId);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.adSizeId);
			Thread.sleep(5000);
			actions.click(adssRegressionSuitePgObjects.adSizeId);
			actions.takeScreenshot(driver);
			log.info("Ad-Size selected");
		} catch (Exception e) {
			log.error("Unable to select Ad Size");
			scenario.write("Unable to select Ad Size");
			throw e;
		}
	}

	@Then("^click on upload button$")
	public void click_on_upload_button() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.uploadButton);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.uploadButton);
			actions.click(adssRegressionSuitePgObjects.uploadButton);
			actions.takeScreenshot(driver);
			log.info("Able to click on upload button");
		} catch (Exception e) {
			log.error("Unable to click on upload button");
			scenario.write("Unable to click on upload button");
			throw e;
		}
	}

	@Then("^upload photo$")
	public void upload_photo() throws Throwable {
		try {
			adssRegressionSuitePgObjects.uploadLink.sendKeys(System.getProperty("user.dir") + "\\Images\\"+Utils.pickRandomIamge1());
			log.info("Able to upload photo");
		} catch (Exception e) {
			log.error("Unable to upload photo");
			scenario.write("Unable to upload photo");
			throw e;
		}
	}

	@Then("^click on Review and Submit button$")
	public void click_on_Review_and_Submit_button() throws Throwable {
		try {
			try {Thread.sleep(5000);
			action.moveToElement(adssRegressionSuitePgObjects.ReviewandSubmitButton).click().build().perform();
			}catch(Exception e){

			}
			try {
				Thread.sleep(5000);
				//				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.ReviewandSubmitButton);
				//				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.ReviewandSubmitButton);
				adssRegressionSuitePgObjects.ReviewandSubmitButton.click();
				actions.takeScreenshot(driver);
				waitForPageToLoad();
				log.info("Able to click on the Review And Submit button");
			}catch(Exception e1){}

			try {
				Thread.sleep(5000);
				//				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.ReviewandSubmitButton);
				//				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.ReviewandSubmitButton);
				//				actions.scrollToElement(driver, adssRegressionSuitePgObjects.ReviewandSubmitButton);
				adssRegressionSuitePgObjects.ReviewandSubmitButton.click();
			}
			catch (Exception e) {
				// do nothing
			}
			actions.takeScreenshot(driver);
			log.info("Able to click on the Review and Submit button");
		} catch (Exception e) {
			log.error("Unable to click on the Review and Submit button");
			scenario.write("Unable to click on the Review and Submit button");
			throw e;
		}
	}

	@Then("^insert order notes as \"([^\"]*)\"$")
	public void insert_order_notes_as(String orderNotes) throws Throwable {
		try {
			/*actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderNotes);*/
			try{
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.orderNotes);
			}
			catch(Exception e){
				Thread.sleep(10000);
			}
			actions.type(adssRegressionSuitePgObjects.orderNotes, orderNotes + "-" + notes);
			actions.takeScreenshot(driver);
			log.info("Able to insert order notes as " + orderNotes);
		} catch (Exception e) {
			log.error("Unable to insert order notes as " + orderNotes);
			scenario.write("Unable to insert order notes as " + orderNotes);
			throw e;
		}
	}

	@Then("^fill in all the info$")
	public void fill_in_all_the_info() throws Throwable {
		try {
			try{
				Utils.fillText(adssRegressionSuitePgObjects.contactPhoneNumber, "8867548169");
			}
			catch (NoSuchElementException e) {
				Utils.fillText(adssRegressionSuitePgObjects.contactPhoneNumberWithoutPlaceholder, "8867548169");
			}
			Utils.fillText(adssRegressionSuitePgObjects.streetAddress, "123 Park St");
			Utils.fillText(adssRegressionSuitePgObjects.city, "Miami");
			Utils.selectDropdown(adssRegressionSuitePgObjects.state, "Florida");
			Utils.fillText(adssRegressionSuitePgObjects.emailId, "aghosh@tribpub.com");
			Utils.fillText(adssRegressionSuitePgObjects.confEmailId, "aghosh@tribpub.com");
			Utils.fillText(adssRegressionSuitePgObjects.expirationMonth,"December"); //Joy
			Utils.fillText(adssRegressionSuitePgObjects.expirationYear,"2030"); //Joy
			try{
			adssRegressionSuitePgObjects.businessName.clear();
			Utils.fillText(adssRegressionSuitePgObjects.businessName, "test");
			}
			catch (NoSuchElementException e) {
				// do nothing
			}
			String ccType="Visa";
			try{
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.creditCardCheckboxToCheck(ccType));
				actions.javascriptClk(driver, adssRegressionSuitePgObjects.creditCardCheckboxToCheck(ccType));
				actions.click(adssRegressionSuitePgObjects.creditCardCheckboxToCheck(ccType));
			}
			catch (Exception e) {
				// do nothing
			}
			actions.takeScreenshot(driver);
			log.info("Able to fill in all the info");
		} catch (Exception e) {
			log.error("Unable to fill in all the info");
			scenario.write("Unable to fill in all the info");
			throw e;
		}
	}

	@Then("^check the Proof Read checkbox$")
	public void check_the_Proof_Read_checkbox() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.checkProofRead);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.checkProofRead);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.checkProofRead);
			actions.click(adssRegressionSuitePgObjects.checkProofRead);
			actions.takeScreenshot(driver);
			log.info("Able to check the Proof Read checkbx");
		} catch (Exception e) {
			log.error("Unable to check the Proof Read checkbx");
			scenario.write("Unable to check the Proof Read checkbx");
			throw e;
		}
	}

	@Then("^click book ad button in the confirmation page$")
	public void click_book_ad_button_in_the_confirmation_page() throws Throwable {
		try {

			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.bookAdButton);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.bookAdButton);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.bookAdButton);
			actions.click(adssRegressionSuitePgObjects.bookAdButton);
			try
			{	
				Thread.sleep(5000);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.orderId);
				adssRegressionSuitePgObjects.bookAdButton.click();
			}
			catch(Exception e)
			{
				try
				{	
					Thread.sleep(5000);
					adssRegressionSuitePgObjects.bookAdButton.click();
					actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.orderId);
				}
				catch(Exception e1)
				{
					try
					{	
						Thread.sleep(5000);
						adssRegressionSuitePgObjects.bookAdButton.click();
						actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.orderId);
					}
					catch(Exception e2)
					{

					}
				}
			}

			actions.takeScreenshot(driver);
			log.info("Able to click on the book ad button in the confirmation page");

		} catch (Exception e) {
			log.error("Unable to click on the book ad button in the confirmation page");
			scenario.write("Unable to click on the book ad button in the confirmation page");
			throw e;
		}
	}

	@Then("^check if the order number is generated$")
	public void check_if_the_order_number_is_generated() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderId);
			orderId = adssRegressionSuitePgObjects.orderId.getText();
			Assert.assertNotNull(orderId);
			actions.takeScreenshot(driver);
			log.info("The order number is generated and the Number is "+orderId);
			scenario.write("The order number generated is " + orderId);
		} catch (Exception e) {
			log.error("Unable to check if the order number is generated");
			scenario.write("Unable to check if the order number is generated");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The order number is not generated");
			scenario.write("The order number is not generated");
			throw e;
		}
	}

	@Then("^get the Run Dates Print In Order Line$")
	public void get_the_Run_Dates_Print_In_Order_Line() throws Throwable {
		try {
			int orderDateSize = adssRegressionSuitePgObjects.orderDate.size();
			Assert.assertTrue(orderDateSize>0);
			RunDatesList.add(new ArrayList(actions.getRunDatesPrintAdOrderLine(orderDateSize, adssRegressionSuitePgObjects.orderDate)));
			actions.takeScreenshot(driver);
			log.info("Able to get the Run Dates Print In Order Line");
		} catch (Exception e) {
			log.error("Error in getting the Run Dates Print in Order Line");
			scenario.write("Error in getting the Run Dates Print in Order Line");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to get the Run Dates Print In Order Line");
			scenario.write("Unable to get the Run Dates Print In Order Line");
			throw e;
		}
	}

	@Then("^get the total price$")
	public void get_the_total_price() throws Throwable {
		try {
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.totalPriceList);
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 20000);
			try{
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.totalPrice);
			}
			catch(NoSuchElementException e){
				actions.scrollDown(driver);
				actions.scrollToElement(driver, adssRegressionSuitePgObjects.totalPrice);
			}
			totalPrice = adssRegressionSuitePgObjects.totalPrice.getText().replaceAll("\\s", "");
			actions.takeScreenshot(driver);
			log.info("Able to get the total price:"+totalPrice);
		} catch (Exception e) {
			log.error("Unable to get the total price");
			scenario.write("Unable to get the total price");
			throw e;
		}
	}


	@Then("^open a new tab and open the Adit URL$")
	public void open_a_new_tab_and_open_the_Adit_URL() throws Throwable {
		try {
			JavascriptExecutor je1 = (JavascriptExecutor) driver;
			je1.executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			String environment;
			try{
				environment= System.getProperty("environment");
				String aditUrl = "ADIT" + environment + "URL";
				driver.get(data.getProperty(aditUrl));
			}
			catch (NullPointerException e) {
				driver.get(data.getProperty("ADITQAURL"));
			}
			actions.takeScreenshot(driver);
			log.info("Able to open new tab and enter Adit URL");
		} catch (Exception e) {
			log.error("Unable to open new tab and enter Adit URL");
			scenario.write("Unable to open new tab and enter Adit URL");
			throw e;
		}
	}

	@Then("^login to the Adit URL$")
	public void login_to_the_Adit_URL() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.aditEmailBox);
			adssRegressionSuitePgObjects.aditEmailBox.click();
			String environment=System.getProperty("environment");
			try{
				if(environment.trim().equalsIgnoreCase("PROD")){
					adssRegressionSuitePgObjects.aditEmailBox.sendKeys(System.getProperty("ADITUSERNAME"));
				}
				else {
					if(System.getProperty("ADITUSERNAME")!=null){
						adssRegressionSuitePgObjects.aditEmailBox.sendKeys(System.getProperty("ADITUSERNAME"));
					}
					else{
						adssRegressionSuitePgObjects.aditEmailBox.sendKeys(data.getProperty("usernameAdit2"));
					}
				}
			}
			catch (NullPointerException e) {
				if(System.getProperty("ADITUSERNAME")!=null){
					adssRegressionSuitePgObjects.aditEmailBox.sendKeys(System.getProperty("ADITUSERNAME"));
				}
				else{
					adssRegressionSuitePgObjects.aditEmailBox.sendKeys(data.getProperty("usernameAdit2"));
				}
			}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.aditPasswordBox);
			adssRegressionSuitePgObjects.aditPasswordBox.click();
			try{
				if(environment.trim().equalsIgnoreCase("PROD")){
					adssRegressionSuitePgObjects.aditPasswordBox.sendKeys(System.getProperty("ADITPASSWORD"));
				}
				else {
					if(System.getProperty("ADITPASSWORD")!=null){
						adssRegressionSuitePgObjects.aditPasswordBox.sendKeys(System.getProperty("ADITPASSWORD"));
					}
					else{
						adssRegressionSuitePgObjects.aditPasswordBox.sendKeys(data.getProperty("passwordAdit2"));
					}
				}
			}
			catch (NullPointerException e) {
				if(System.getProperty("ADITPASSWORD")!=null){
					adssRegressionSuitePgObjects.aditPasswordBox.sendKeys(System.getProperty("ADITPASSWORD"));
				}
				else{
					adssRegressionSuitePgObjects.aditPasswordBox.sendKeys(data.getProperty("passwordAdit2"));
				}
			}
			Thread.sleep(2000);
			adssRegressionSuitePgObjects.loginButton.click();
			Thread.sleep(3000);
			waitForPageToLoad();
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.dashboardPageHeader);
			actions.takeScreenshot(driver);
			log.info("Able to login to the Adit URL");
		} catch (Exception e) {
			log.error("Unable to login to the Adit URL");
			scenario.write("Unable to login to the Adit URL");
			throw e;
		}
	}

	@Then("^get the order pub dates in order line$")
	public void get_the_order_pub_dates_in_order_line() throws Throwable {
		try {
			RunDatesListAdit.clear();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(5000);
			String orderPrice=adssRegressionSuitePgObjects.aditOrderLinesPubDates.get(0).getText();
			RunDatesListAdit.add(orderPrice);
			actions.takeScreenshot(driver);
			log.info("Able to get the order pub dates inorder line");
		} catch (Exception e) {
			log.error("Unable to get the order pub dates inorder line");
			scenario.write("Unable to get the order pub dates inorder line");
			throw e;
		}
	}

	@Then("^check if ADSS and ADIT rundates are same$")
	public void check_if_ADSS_and_ADIT_rundates_are_same() throws Throwable {
		try {
			Assert.assertTrue(RunDatesList.size()>0);
			Assert.assertTrue(RunDatesListAdit.size()>0);
			Assert.assertEquals(RunDatesList.get(0).get(0),RunDatesListAdit.get(0));
			actions.takeScreenshot(driver);
			log.info("Able to check if ADSS and ADIT rundates are same");
		} catch (Exception e) {
			log.error("Unable to check if ADSS and ADIT rundates are same");
			scenario.write("Unable to check if ADSS and ADIT rundates are same");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and ADIT rundates are not same");
			scenario.write("The ADSS and ADIT rundates are not same");
			throw e;
		}
	}

	@Then("^check if ADSS and ADIT total prices are same for Loc users$")
	public void check_if_ADSS_and_ADIT_total_prices_are_same_for_Loc_users() throws Throwable {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", adssRegressionSuitePgObjects.aditOrderPrice);
			String aditOrderPrice =  adssRegressionSuitePgObjects.aditOrderPrice.getText();
			try{
			TotalPriceAdit="$"+String.valueOf(Double.parseDouble(Utils.getPriceValue(aditOrderPrice)));
			}
			catch(NumberFormatException e){
				TotalPriceAdit="$"+String.valueOf((NumberFormat.getNumberInstance(java.util.Locale.US).parse(Utils.getPriceValue(aditOrderPrice))).doubleValue());
			}
			String TotalPriceADSS;
			try{
				log.info("Total Price:"+totalPrice);
			TotalPriceADSS="$"+(String.valueOf(Utils.getLOCprice(Double.parseDouble(Utils.getPriceValue(totalPrice)))));
			}
			catch(NumberFormatException e){
				TotalPriceADSS="$"+(String.valueOf(Utils.getLOCprice(NumberFormat.getNumberInstance(java.util.Locale.US).parse(Utils.getPriceValue(totalPrice)).doubleValue())));
			}
			//TotalPriceADSS="$"+(String.valueOf(Utils.getLOCprice(Double.parseDouble(Utils.getPriceValue(totalPrice)))));
			log.info("Adit price:"+TotalPriceAdit);
			log.info("Adss price:"+TotalPriceADSS);
			Assert.assertEquals(TotalPriceADSS,TotalPriceAdit);
			actions.takeScreenshot(driver);
			log.info("The ADSS and ADIT total prices are not the same");
			
			
		} catch (Exception e) {
			log.error("Unable to check if the ADSS and ADIT total prices are same");
			scenario.write("Unable to check if the ADSS and ADIT total prices are same");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and ADIT total prices are not same");
			scenario.write("The ADSS and ADIT total prices are not same");
			throw e;
		}
	}

	@Then("^change the order to processed, if price is less than one dollar, else reject$")
	public void change_the_order_to_processed_if_price_is_less_than_one_dollar_else_reject() throws Throwable {
		boolean ApprovalStatus=Utils.checkIsApprove(Double.parseDouble(Utils.getPriceValue(TotalPriceAdit)));
		if(ApprovalStatus)
		{
			log.info("Order is passed for Approval");

			clickApproveButton();
			log.info("Approve Button Clicked");
			clickConfirmApproveButton();
			log.info("Confirm-Approve Button Clicked");

			driver.navigate().back();

			String orderStatus=checkOrderStatus();
			try{
			Assert.assertEquals(orderStatus,"Processed");
			}
			catch (AssertionError e) {
				Thread.sleep(40000);
				driver.navigate().refresh();
				search_for_the_same_Order_Number_in_Adit();
				orderStatus=checkOrderStatus();
				Assert.assertEquals(orderStatus,"Processed");
			}
			scenario.write("Order Status Changed to Processed");
			log.info("Order Status Changed to Processed");
		}		
		else
		{
			log.info("Order is not passed for Approval");

			clickRejectButton();
			log.info("Reject Button Clicked");
			clickConfirmRejectButton();
			log.info("Confirm-Reject Button Clicked");

			driver.navigate().back();

			String orderStatus=checkOrderStatus();
			Assert.assertEquals(orderStatus,"Rejected");
			scenario.write("Order Status Changed to Rejected");
			log.info("Order Status Changed to Rejected");
		}
	}


	@Then("^change the order to changed, if price is less than one dollar, else reject$")
	public void change_the_order_to_changed_if_price_is_less_than_one_dollar_else_reject() throws Throwable {
		boolean ApprovalStatus=Utils.checkIsApprove(Double.parseDouble(Utils.getPriceValue(TotalPriceAdit)));
		if(ApprovalStatus)
		{
			log.info("Order is passed for Approval");

			clickApproveButton();
			log.info("Approve Button Clicked");
			clickConfirmApproveButton();
			log.info("Confirm-Approve Button Clicked");

			driver.navigate().back();

			String orderStatus=checkOrderStatus();
			try{
			Assert.assertEquals(orderStatus,"Changed");
			}
			catch (AssertionError e) {
				Thread.sleep(40000);
				driver.navigate().refresh();
				search_for_the_same_Order_Number_in_Adit();
				orderStatus=checkOrderStatus();
				Assert.assertEquals(orderStatus,"Changed");
			}
			log.info("Order Status Changed to Changed");
		}		
		else
		{
			log.info("Order is not passed for Approval");

			clickRejectButton();
			log.info("Reject Button Clicked");
			clickConfirmRejectButton();
			log.info("Confirm-Reject Button Clicked");

			driver.navigate().back();

			String orderStatus=checkOrderStatus();
			Assert.assertEquals(orderStatus,"Rejected");
			log.info("Order Status Changed to Rejected");
		}
	}

	public String checkOrderStatus() {    	
		try {	

			Thread.sleep(2000);
			if(!adssRegressionSuitePgObjects.orderStatus.getText().trim().equalsIgnoreCase("Processed")&&!adssRegressionSuitePgObjects.orderStatus.getText().trim().equalsIgnoreCase("Rejected"))
				Thread.sleep(20000);
			if(!adssRegressionSuitePgObjects.orderStatus.getText().trim().equalsIgnoreCase("Processed")&&!adssRegressionSuitePgObjects.orderStatus.getText().trim().equalsIgnoreCase("Rejected"))
				Thread.sleep(20000);
			if(!adssRegressionSuitePgObjects.orderStatus.getText().trim().equalsIgnoreCase("Processed")&&!adssRegressionSuitePgObjects.orderStatus.getText().trim().equalsIgnoreCase("Rejected"))
				Thread.sleep(20000);
			if(!adssRegressionSuitePgObjects.orderStatus.getText().trim().equalsIgnoreCase("Processed")&&!adssRegressionSuitePgObjects.orderStatus.getText().trim().equalsIgnoreCase("Rejected"))
				Thread.sleep(20000);


		}catch(Exception e)
		{
		}
		return adssRegressionSuitePgObjects.orderStatus.getText().trim();
	}

	public void clickRejectButton() {    	
		adssRegressionSuitePgObjects.rejectOrderBtn.click();

		try {Thread.sleep(2000);}catch(Exception e){}

	}

	//@Step("Click Order Confirm-Reject Button")
	public void clickConfirmRejectButton() {    	
		adssRegressionSuitePgObjects.rejectOrderConfBtn.click();

		try {Thread.sleep(20000);}catch(Exception e){}

	}

	public void clickApproveButton() {    	
		adssRegressionSuitePgObjects.approveOrderBtn.click();

		try {Thread.sleep(2000);}catch(Exception e){}

	}

	//@Step("Click Order Confirm-Approve Button")
	public void clickConfirmApproveButton() {    	
		adssRegressionSuitePgObjects.approveOrderConfBtn.click();

		try {Thread.sleep(25000);}catch(Exception e){}

	}

	@Then("^open the \"([^\"]*)\" link section in Adit list link$")
	public void open_the_link_section_in_Adit_list_link(String aditLinkSection) throws Throwable {
		try {
			Thread.sleep(3000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.linkSectionInAditLinkList(aditLinkSection));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.linkSectionInAditLinkList(aditLinkSection));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.linkSectionInAditLinkList(aditLinkSection));
			actions.click(adssRegressionSuitePgObjects.linkSectionInAditLinkList(aditLinkSection));
			actions.takeScreenshot(driver);
			Thread.sleep(3000);
			waitForPageToLoad();
			actions.takeScreenshot(driver);
			log.info("Able to click on in the Link Section " + aditLinkSection + " in Adit list link");
		} catch (Exception e) {
			log.error("Unable to click on in the Link Section " + aditLinkSection + " in Adit list link");
			scenario.write("Unable to click on in the Link Section " + aditLinkSection + " in Adit list link");
			throw e;
		}
	}

	@Then("^click on the same Order Number link from the table$")
	public void click_on_the_same_Order_Number_link_from_the_table() throws Throwable {
		try {
			Thread.sleep(1000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderNumberLinkInAditTable(orderId));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.orderNumberLinkInAditTable(orderId));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.orderNumberLinkInAditTable(orderId));
			actions.click(adssRegressionSuitePgObjects.orderNumberLinkInAditTable(orderId));
			actions.takeScreenshot(driver);
			waitForPageToLoad();
			try{
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderNrHeadingInOrderNrPg);
			}
			catch(TimeoutException ex){
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderNrHeadingInOrderNrPg);
				Thread.sleep(10000);
			}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderNrHeadingInOrderNrPg);
			actions.takeScreenshot(driver);
			log.info("Able to click on the same Order Number link from the table");
		} catch (Exception e) {
			log.error("Unable to click on the same Order Number link from the table");
			scenario.write("Unable to click on the same Order Number link from the table");
			throw e;
		}
	}

	@Then("^search for the same Order Number in Adit$")
	public void search_for_the_same_Order_Number_in_Adit() throws Throwable {
		try {
			String orderNrField = "Order Number";
			Thread.sleep(10000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInAditOrdersPg(orderNrField));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInAditOrdersPg(orderNrField));
			actions.click(adssRegressionSuitePgObjects.inputFieldInAditOrdersPg(orderNrField));
			adssRegressionSuitePgObjects.inputFieldInAditOrdersPg(orderNrField).clear();
			actions.type(adssRegressionSuitePgObjects.inputFieldInAditOrdersPg(orderNrField), orderId);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.searchBtnInOrdersPg);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.searchBtnInOrdersPg);
			actions.click(adssRegressionSuitePgObjects.searchBtnInOrdersPg);
			actions.takeScreenshot(driver);
			waitForPageToLoad();
			actions.takeScreenshot(driver);
			log.info("Able to search for the same Order Number from Adit");
		} catch (Exception e) {
			log.error("Unable to search for the same Order Number from Adit");
			scenario.write("Unable to search for the same Order Number from Adit");
			throw e;
		}
	}

	@Then("^set zones$")
	public void set_zones() throws Throwable {
		try {
			for(int i=0;i<adssRegressionSuitePgObjects.zones.size();i++)
			{
				try {Thread.sleep(2000);}catch(Exception e) {}
				adssRegressionSuitePgObjects.zones.get(i).click();
			}
			actions.takeScreenshot(driver);
			log.info("Able to set zones");
		} catch (Exception e) {
			log.error("Unable to set zones");
			scenario.write("Unable to set zones");
			throw e;
		}
	}

	@Then("^click on Accept and Continue button$")
	public void click_on_Accept_and_Continue_button() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.saveImage);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.saveImage);
			actions.click(adssRegressionSuitePgObjects.saveImage);

			try
			{
				Thread.sleep(3000);
			}
			catch(Exception e)
			{

			}
			actions.takeScreenshot(driver);
			log.info("Able to click on Accept and Continue button");
		} catch (Exception e) {
			log.error("Unable to click on Accept and Continue button");
			scenario.write("Unable to click on Accept and Continue button");
			throw e;
		}
	}

	@Then("^set click through URL for Ad$")
	public void set_click_through_URL_for_Ad() throws Throwable {
		try {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.clickThroughURLForAd);
			Utils.fillText(adssRegressionSuitePgObjects.clickThroughURLForAd, "https://www.test-automation.com");
			actions.takeScreenshot(driver);
			log.info("Able to set click through URL for Ad");
		} catch (Exception e) {
			log.error("Unable to set click through URL for Ad");
			scenario.write("Unable to set click through URL for Ad");
			throw e;
		}
	}

	@Then("^note the Draft Id$")
	public void note_the_Draft_Id() throws Throwable {
		try {
			draftId=Utils.getDraftId(driver.getCurrentUrl());
			actions.takeScreenshot(driver);
			log.info("Draft id is " + draftId);
		} catch (Exception e) {
			log.error("Unable to note the Draft Id");
			scenario.write("Unable to note the Draft Id");
			throw e;
		}
	}

	@Then("^click on the Save and Complete Purchase Later button$")
	public void click_on_the_Save_and_Complete_Purchase_Later_button() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.saveAndCompletePurchaseLaterLink);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.saveAndCompletePurchaseLaterLink);
			actions.click(adssRegressionSuitePgObjects.saveAndCompletePurchaseLaterLink);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Save and Complete Purchase Later button");
		} catch (Exception e) {
			log.error("Unable to click on the Save and Complete Purchase Later button");
			scenario.write("Unable to click on the Save and Complete Purchase Later button");
			throw e;
		}
	}

	@Then("^check if the Draft Id is available$")
	public void check_if_the_Draft_Id_is_available() throws Throwable {
		try {
			boolean checkResult=checkDraftId(draftId);
			Assert.assertTrue(checkResult);
			actions.takeScreenshot(driver);
			log.info("Draft id is available");
			scenario.write("Draft Id is " + draftId);
		} catch (Exception e) {
			log.error("Unable to check if Draft Id is available");
			scenario.write("Unable to check if Draft Id is available");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The Draft Id is not available");
			scenario.write("The Draft Id is not available");
			throw e;
		}
	}

	public boolean checkDraftId(String id) {
		try {Thread.sleep(10000);}catch(Exception e){}
		String xpath="//*[text()='"+id+"']";
		WebElement element=driver.findElement(By.xpath(xpath));
		if(element.isDisplayed())
		{
			element.click();
			return true;
		}

		return false;
	}


	@Then("^click on Signout$")
	public void click_on_Signout() throws Throwable {
		try {
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.usernameLink);
			adssRegressionSuitePgObjects.usernameLink.click();
			Thread.sleep(3000);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.signOutLink);
			adssRegressionSuitePgObjects.signOutLink.click();
			actions.takeScreenshot(driver);
			log.info("Able to signout");
			log.info(driver.getCurrentUrl());
		} catch (Exception e) {
			log.error("Unable to signout");
			scenario.write("Unable to signout");
			throw e;
		}
	}

	@Then("^click on Edit Account Info link$")
	public void click_on_Edit_Account_Info_link() throws Throwable {
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			//js.executeScript("arguments[0].scrollIntoView(true);",adssRegressionSuitePgObjects.editAccountInfoLink);
			js.executeScript("window.scrollBy(0,1500)");
			Thread.sleep(7000);
			action.moveToElement(adssRegressionSuitePgObjects.editAccountInfoLink).click().build().perform();
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.saveButton);
			actions.takeScreenshot(driver);
			log.info("Able to edit Account Info Link");
		} catch (Exception e) {
			log.error("Unable to edit Account Info Link");
			scenario.write("Unable to edit Account Info Link");
			throw e;
		}
	}



	public void changeFirstName(String string) {

		adssRegressionSuitePgObjects.firstName.clear();
		adssRegressionSuitePgObjects.firstName.sendKeys(string);

	}

	//@Step("changeLastName")
	public void changeLastName(String string) {

		adssRegressionSuitePgObjects.lastName.clear();
		adssRegressionSuitePgObjects.lastName.sendKeys(string);

	}

	//@Step("changePhoneNumber")
	public void changePhoneNumber(String string) {

		adssRegressionSuitePgObjects.phoneNumber.clear();
		adssRegressionSuitePgObjects.phoneNumber.sendKeys(string);

	}

	//@Step("changeStreetAddress")
	public void changeStreetAddress(String string) {

		adssRegressionSuitePgObjects.streetAddress.clear();
		adssRegressionSuitePgObjects.streetAddress.sendKeys(string);

	}

	//@Step("changeCity")
	public void changeCity(String string) {

		adssRegressionSuitePgObjects.city.clear();
		adssRegressionSuitePgObjects.city.sendKeys(string);

	}

	//@Step("Click Mobile menu")
	public void changeState(String string) {

		//Utilities.selectDropdown(state, string);
		try{
			adssRegressionSuitePgObjects.state.click();
		}
		catch (NoSuchElementException e) {
			adssRegressionSuitePgObjects.StateInEditAccountInfoWindow.click();
		}
		try {Thread.sleep(5000);}catch(Exception e) {}
		WebElement ele=driver.findElement(By.xpath("//*[text()='"+string+"']"));
		ele.click();
		/*action.moveToElement(ele).click().build().perform();*/


	}

	//@Step("changeZip")
	public void changeZipCode(String string) {

		adssRegressionSuitePgObjects.zipCode.clear();
		adssRegressionSuitePgObjects.zipCode.sendKeys(string);

	}

	@Then("^edit some info in the Account$")
	public void edit_some_info_in_the_Account() throws Throwable {
		try {
			changeFirstName("Shalu");
			changeLastName("Bansal");
			changePhoneNumber("8876875160");
			changeStreetAddress("777 W Chicago Ave"+System.currentTimeMillis());
			changeCity("Chicago");
			changeState("Illinois");
			changeZipCode("60654");
			actions.takeScreenshot(driver);
			log.info("Able to edit some info in the Account");
		} catch (Exception e) {
			log.error("Unable to edit some info in the Account");
			scenario.write("Unable to edit some info in the Account");
			throw e;
		}
	}

	@Then("^click on Save button in the edit window$")
	public void click_on_Save_button_in_the_edit_window() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.saveBtnInEditWindow);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.saveBtnInEditWindow);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.saveBtnInEditWindow);
			adssRegressionSuitePgObjects.saveBtnInEditWindow.click();
			actions.takeScreenshot(driver);
			Thread.sleep(50000);
			log.info("Able to click on the Save button in the edit window");
		} catch (Exception e) {
			log.error("Unable to click on the Save button in the edit window");
			scenario.write("Unable to click on the Save button in the edit window");
			throw e;
		}
	}

	@Then("^select the required Design Template$")
	public void select_the_required_Design_Template() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.Design_Template);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.Design_Template);
			actions.click(adssRegressionSuitePgObjects.Design_Template);
			actions.takeScreenshot(driver);
			waitForPageToLoad();
			log.info("Able to select the required Design Template");
		} catch (Exception e) {
			log.error("Unable to select the required Design Template");
			scenario.write("Unable to select the required Design Template");
			throw e;
		}
	}

	@Then("^fill in the text ctc$")
	public void fill_in_the_text_ctc() throws Throwable {
		try {
			Utils.fillText(adssRegressionSuitePgObjects.HeadLine, Utils.generateRandomString(10));
			Utils.fillText(adssRegressionSuitePgObjects.BodyCopy2, Utils.generateRandomString(13));
			Utils.fillText(adssRegressionSuitePgObjects.BodyCopy, Utils.generateRandomString(10));
			Utils.fillText(adssRegressionSuitePgObjects.CompanyName, Utils.generateRandomString(7));
			Utils.fillText(adssRegressionSuitePgObjects.PhoneNumberInDesignYourMaterialPg, "9903463256");
			Utils.fillText(adssRegressionSuitePgObjects.WebAddress, "https://www."+Utils.generateRandomString(7)+".com");
			actions.takeScreenshot(driver);
			log.info("Able to fill in the text ctc");
		} catch (Exception e) {
			log.error("Unable to fill in the text ctc");
			scenario.write("Unable to fill in the text ctc");
			throw e;
		}
	}

	@Then("^click on the Save and Continue button$")
	public void click_on_the_Save_and_Continue_button() throws Throwable {
		try {
			Thread.sleep(3000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.SaveAndContinueButton);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.SaveAndContinueButton);
			actions.javascriptClk(driver, adssRegressionSuitePgObjects.SaveAndContinueButton);
			try {
				Thread.sleep(10000);
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.ReviewandSubmitButton);
			}catch(Exception e){


				try {Thread.sleep(10000);
				adssRegressionSuitePgObjects.SaveAndContinueButton.click();}catch(Exception e1){}
			}
			actions.takeScreenshot(driver);
			log.info("Able to click on the Save and Continue button");
		} catch (Exception e) {
			log.error("Unable to click on the Save and Continue button");
			scenario.write("Unable to click on the Save and Continue button");
			throw e;
		}
	}

	@Then("^enter coupon code as \"([^\"]*)\"$")
	public void enter_coupon_code_as(String couponCode) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.couponInputField);
			actions.type(adssRegressionSuitePgObjects.couponInputField, couponCode);
			actions.takeScreenshot(driver);
			log.info("Able to enter the coupon code as " + couponCode);
		} catch (Exception e) {
			log.error("Unable to enter the coupon code as " + couponCode);
			scenario.write("Unable to enter the coupon code as " + couponCode);
			throw e;
		}
	}

	@Then("^apply the coupon$")
	public void apply_the_coupon() throws Throwable {
		try {
			int nrOfPricesShowingBeforeApplyingCoupon = adssRegressionSuitePgObjects.totalPriceInReviewOrderPg.size();
			Assert.assertTrue(nrOfPricesShowingBeforeApplyingCoupon>0);
			actions.click(adssRegressionSuitePgObjects.couponApplyField);
			int nrOfPricesShowingAfterApplyingCoupon = 0;
			int counter = 0;
			boolean boolFound = false;
			do {
				try {
					nrOfPricesShowingAfterApplyingCoupon = adssRegressionSuitePgObjects.totalPriceInReviewOrderPg.size();
					Thread.sleep(5000);
					Assert.assertEquals(2, (nrOfPricesShowingAfterApplyingCoupon-nrOfPricesShowingBeforeApplyingCoupon));
					boolFound = true;
				} catch (AssertionError e) {
					boolFound = false;
					counter++;
				}
				if(boolFound==true){
					break;
				}
			} while (counter<20);
			Assert.assertEquals(2, (nrOfPricesShowingAfterApplyingCoupon-nrOfPricesShowingBeforeApplyingCoupon));
			actions.takeScreenshot(driver);
			log.info("Able to apply the coupon");
		} catch (Exception e) {
			log.error("Error in applying the coupon");
			scenario.write("Error in applying the coupon");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to apply the coupon");
			scenario.write("Unable to apply the coupon");
			throw e;
		}
	}

	@Then("^select new credit card$")
	public void select_new_credit_card() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.newCC);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.newCC);
			actions.click(adssRegressionSuitePgObjects.newCC);
			actions.takeScreenshot(driver);
			log.info("Able to select new credit card");
		} catch (Exception e) {
			log.error("Unable to select new credit card");
			scenario.write("Unable to select new credit card");
			throw e;
		}
	}

	@Then("^select the credit card checkbox$")
	public void select_the_credit_card_checkbox() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.creditCardInformationCheckBox);
			actions.click(adssRegressionSuitePgObjects.creditCardInformationCheckBox);
			actions.takeScreenshot(driver);
			log.info("Able to select the credit card checkbox");
		} catch (Exception e) {
			log.error("Unable to select the credit card checkbox");
			scenario.write("Unable to select the credit card checkbox");
			throw e;
		}
	}

	@Then("^fill in the billing info$")
	public void fill_in_the_billing_info() throws Throwable {
		try {
			String environment=System.getProperty("environment");
			try{
				if(environment.trim().equalsIgnoreCase("PROD")){
					Utils.fillText(adssRegressionSuitePgObjects.cardNumber, System.getProperty("CCNumber"));
				}
				else {
					Utils.fillText(adssRegressionSuitePgObjects.cardNumber, "5105105105105100");
				}
			}
			catch (NullPointerException e) {
				Utils.fillText(adssRegressionSuitePgObjects.cardNumber, "5105105105105100");
			}

			//expirationMonth.click();
			//DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'February')]")).click();
			Thread.sleep(3000);
			Utils.selectDropdown(adssRegressionSuitePgObjects.expirationMonth, "December");
			Thread.sleep(3000);
			//expirationYear.click();
			//DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'2021')]")).click();
			
			Calendar now = Calendar.getInstance();   // Gets the current date and time
//			int year = now.get(Calendar.YEAR)+1; 
			int year = 2024;
			Utils.selectDropdown(adssRegressionSuitePgObjects.expirationYear, Integer.toString(year));
			Thread.sleep(3000);
			try{
				if(environment.trim().equalsIgnoreCase("PROD")){
					Utils.fillText(adssRegressionSuitePgObjects.verificationNumber, System.getProperty("CVV"));
				}
				else {
					Utils.fillText(adssRegressionSuitePgObjects.verificationNumber, "123");
				}
			}
			catch (NullPointerException e) {
				Utils.fillText(adssRegressionSuitePgObjects.verificationNumber, "123");
			}
			actions.takeScreenshot(driver);
			log.info("Able to fill in all the billing info");
		} catch (Exception e) {
			log.error("Error in filling in the billing info");
			scenario.write("Error in filling in the billing info");
			throw e;
		}
	}

	public ArrayList<String> getRunDatesOnlineAdOnlyOrderLine(int index, int numberofRunDates) {

		ArrayList<String> RunDates = new ArrayList<String>();

		for(int i=0;i<numberofRunDates;i++)
			RunDates.add(Utils.getExactOnlineRunDate(adssRegressionSuitePgObjects.orderDate.get(i).getText()));

		return RunDates;

	}

	public String getOrderPriceOrderLine(int index) {
		Assert.assertTrue(adssRegressionSuitePgObjects.orderPriceList.size()>0);
		String OrderPrice=adssRegressionSuitePgObjects.orderPriceList.get(index-1).getText().trim();
		return OrderPrice;

	}

	public String getOrderPriceOrderLineInAdss(int index) {
		Assert.assertTrue(adssRegressionSuitePgObjects.orderPrice.size()>0);
		String OrderPrice=adssRegressionSuitePgObjects.orderPrice.get(index-1).getText().trim();
		return OrderPrice;

	}

	@Then("^note all the run dates online and note the first order price line$")
	public void note_all_the_run_dates_online_and_note_the_first_order_price_line() throws Throwable {
		try {
			RunDatesList.clear();
			OrderPrice.clear();
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDate.size()>0);
			Assert.assertTrue(adssRegressionSuitePgObjects.orderPrice.size()>0);
			try{
				RunDatesList.add(new ArrayList(getRunDatesPrintAdOrderLine(1, adssRegressionSuitePgObjects.orderDate.size())));
			}
			catch (IllegalArgumentException e) {
				RunDatesList.add(new ArrayList(getRunDatesOnlineAdOnlyOrderLine(1, adssRegressionSuitePgObjects.orderDate.size())));
			}
			OrderPrice.add(adssRegressionSuitePgObjects.orderPrice.get(0).getText().trim());
			actions.takeScreenshot(driver);
			log.info("Able to note all the run dates online and note the first order price line");
		} catch (Exception e) {
			log.error("Error in noting all the run dates online and note the first order price line");
			scenario.write("Error in noting all the run dates online and note the first order price line");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to note all the run dates online and note the first order price line");
			scenario.write("Unable to note all the run dates online and note the first order price line");
			throw e;
		}
	}

	@Then("^note all the run dates online and note the first order price line for Ad Only Order Line$")
	public void note_all_the_run_dates_online_and_note_the_first_order_price_line_for_Ad_Only_Order_Line() throws Throwable {
		try {
			RunDatesList.clear();
			OrderPrice.clear();
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDate.size()>0);
			Assert.assertTrue(adssRegressionSuitePgObjects.orderPrice.size()>0);
			RunDatesList.add(new ArrayList(getRunDatesOnlineAdOnlyOrderLine(1, adssRegressionSuitePgObjects.orderDate.size())));
			OrderPrice.add(adssRegressionSuitePgObjects.orderPrice.get(0).getText().trim());
			actions.takeScreenshot(driver);
			log.info("Able to note all the run dates online and note the first order price line for Ad Only Order Line");
		} catch (Exception e) {
			log.error("Error in noting all the run dates online and note the first order price line for Ad Only Order Line");
			scenario.write("Error in noting all the run dates online and note the first order price line for Ad Only Order Line");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to note all the run dates online and note the first order price line for CTC");
			scenario.write("Unable to note all the run dates online and note the first order price line for CTC");
			throw e;
		}
	}

	@Then("^note all the run dates online and note the first order price line for CTC Order Edit$")
	public void note_all_the_run_dates_online_and_note_the_first_order_price_line_CTC_Order_Edit() throws Throwable {
		try {
			RunDatesList.clear();
			OrderPrice.clear();
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDate.size()>0);
			Assert.assertTrue(adssRegressionSuitePgObjects.orderPrice.size()>0);
			RunDatesList.add(new ArrayList(getRunDatesOnlineAdOnlyOrderLine(1, adssRegressionSuitePgObjects.orderDate.size())));
			OrderPrice.add(adssRegressionSuitePgObjects.orderPrice.get(0).getText().trim());
			actions.takeScreenshot(driver);
			log.info("Able to note all the run dates online and note the first order price line for CTC Order Edit");
		} catch (Exception e) {
			log.error("Error in noting all the run dates online and note the first order price line for CTC Order Edit");
			scenario.write("Error in noting all the run dates online and note the first order price line for CTC Order Edit");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to note all the run dates online and note the first order price line for CTC Order Edit");
			scenario.write("Unable to note all the run dates online and note the first order price line for CTC Order Edit");
			throw e;
		}
	}

	@Then("^note all the run dates online and note the first order price line for CTC Order Edit after edit$")
	public void note_all_the_run_dates_online_and_note_the_first_order_price_line_CTC_Order_Edit_after_edit$() throws Throwable {
		try {
			RunDatesListEdit.clear();
			OrderPrice.clear();
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDate.size()>0);
			Assert.assertTrue(adssRegressionSuitePgObjects.orderPrice.size()>0);
			RunDatesListEdit.add(new ArrayList(getRunDatesOnlineAdOnlyOrderLine(1, adssRegressionSuitePgObjects.orderDate.size())));
			OrderPrice.add(adssRegressionSuitePgObjects.orderPrice.get(0).getText().trim());
			actions.takeScreenshot(driver);
			log.info("Able to note all the run dates online and note the first order price line for CTC Order Edit after edit");
		} catch (Exception e) {
			log.error("Error in noting all the run dates online and note the first order price line for CTC Order Edit after edit");
			scenario.write("Error in noting all the run dates online and note the first order price line for CTC Order Edit after edit");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to note all the run dates online and note the first order price line for CTC Order Edit after edit");
			scenario.write("Unable to note all the run dates online and note the first order price line for CTC Order Edit after edit");
			throw e;
		}
	}

	@Then("^check if the ADSS and Adit orderlines price are same$")
	public void check_if_the_ADSS_and_Adit_orderlines_price_are_same() throws Throwable {
		try {
			Assert.assertEquals(OrderPrice.get(0),OrderLinesPriceAdit.get(0));
			actions.takeScreenshot(driver);
			log.info("The ADSS and Adit orderlines price are the same");
		} catch (Exception e) {
			log.error("Unable to check if the ADSS and Adit orderlines price are same");
			scenario.write("Unable to check if the ADSS and Adit totalPrice price are same");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and Adit orderlines price are not the same");
			scenario.write("The ADSS and Adit orderlines price are not the same");
			throw e;
		}
	}

	@Then("^check if the ADSS and Adit orderlines price are same for LOC Users$")
	public void check_if_the_ADSS_and_Adit_orderlines_price_are_same_for_LOC_Users() throws Throwable {
		try {
			check_if_ADSS_and_ADIT_total_prices_are_same_for_Loc_users();
			actions.takeScreenshot(driver);
			log.info("The ADSS and Adit orderlines price are the same for LOC Users");
		} catch (Exception e) {
			log.error("Unable to check if the ADSS and Adit orderlines price are same for LOC Users");
			scenario.write("Unable to check if the ADSS and Adit totalPrice price are same for LOC Users");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and Adit orderlines price are not the same for LOC Users");
			scenario.write("The ADSS and Adit orderlines price are not the same for LOC Users");
			throw e;
		}
	}

	@Then("^check if the ADSS and Adit totalPrice price are same for non-Loc users$")
	public void check_if_the_ADSS_and_Adit_totalPrice_price_are_same_for_non_Loc_users() throws Throwable {
		try {
			TotalPriceAdit=adssRegressionSuitePgObjects.aditOrderPrice.getText();
			
			String TotalPriceAditEditAd="$"+String.valueOf(Double.parseDouble(Utils.getPriceValue(TotalPriceAdit)));
			String TotalPriceADSS="$"+String.valueOf(Double.parseDouble(Utils.getPriceValue(OrderPrice.get(0))));
			Assert.assertEquals(TotalPriceAditEditAd,TotalPriceADSS);
			actions.takeScreenshot(driver);
			log.info("The ADSS and Adit totalPrice price are the same for non-Loc users");
		} catch (Exception e) {
			log.error("Unable to check if the ADSS and Adit totalPrice price are same for non-Loc users");
			scenario.write("Unable to check if the ADSS and Adit totalPrice price are same for non-Loc users");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and Adit totalPrice price are not the same for non-Loc users");
			scenario.write("The ADSS and Adit totalPrice price are not the same for non-Loc users");
			throw e;
		}
	}

	@Then("^note all the adit orderlines price$")
	public void note_all_the_adit_orderlines_price() throws Throwable {
		try{
			OrderLinesPriceAdit.clear();
			OrderLinesPriceAdit.add(getOrderPriceOrderLine(1));
			actions.takeScreenshot(driver);
			log.info("Able to note all the adit orderlines price");
		}
		catch (Exception e) {
			log.error("Unable to note all the addit orderlines price");
			throw e;
		}
	}

	@Then("^swicth to the (\\d+) tab$")
	public void swicth_to_the_tab(int tabNr) throws Throwable {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabNr-1));
			actions.takeScreenshot(driver);
			log.info("Able to switch to the " + tabNr + " tab");
		} catch (Exception e) {
			log.error("Unable to switch to the " + tabNr + " tab");
			scenario.write("Unable to switch to the " + tabNr + " tab");
			throw e;
		}
	}

	@Then("^click on Renew Link$")
	public void click_on_Renew_Link() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.renewLink);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.renewLink);
			actions.click(adssRegressionSuitePgObjects.renewLink);
			actions.takeScreenshot(driver);
			log.info("Able to click on Renew Link");
		} catch (Exception e) {
			log.error("Unable to click on Renew Link");
			scenario.write("Unable to click on Renew Link");
			throw e;
		}
	}

	@Then("^click on the same order Id$")
	public void click_on_the_same_order_Id() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderId);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.orderId);
			try{
			adssRegressionSuitePgObjects.orderId.click();
			}
			catch(WebDriverException e){
				actions.javascriptClk(driver, adssRegressionSuitePgObjects.orderId);
			}
			actions.takeScreenshot(driver);
			waitForPageToLoad();
			log.info("Able to click on the sme order id");
		} catch (Exception e) {
			log.error("Unable to click on the sme order id");
			scenario.write("Unable to click on the sme order id");
			throw e;
		}
	}

	@Then("^click on Edit Link in Order Detail Page$")
	public void click_on_Edit_Link_in_Order_Detail_Page() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.editLink);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.editLink);
			actions.click(adssRegressionSuitePgObjects.editLink);
			actions.takeScreenshot(driver);
			log.info("Able to click on the edit link in the order detail page");
		} catch (Exception e) {
			log.error("Unable to click on the edit link in the order detail page");
			scenario.write("Unable to click on the edit link in the order detail page");
			throw e;
		}
	}

	@Then("^click on Edit Link in Edit Order Page$")
	public void click_on_Edit_Link_in_Edit_Order_Page() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.editLink);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.editLink);
			actions.click(adssRegressionSuitePgObjects.editLink);
			actions.takeScreenshot(driver);
			log.info("Able to click on the edit link in the edit order page");
		} catch (Exception e) {
			log.error("Unable to click on the edit link in the edit order page");
			scenario.write("Unable to click on the edit link in the edit order page");
			throw e;
		}
	}

	@Then("^click on Edit Link in Edit Ad Material Image$")
	public void click_on_Edit_Link_in_Edit_Material_Image() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.editLinkInEditAdMaterialImg);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.editLinkInEditAdMaterialImg);
			try{
			actions.click(adssRegressionSuitePgObjects.editLinkInEditAdMaterialImg);
			}
			catch(WebDriverException e){
				Thread.sleep(10000);
				actions.javascriptClk(driver, adssRegressionSuitePgObjects.editLinkInEditAdMaterialImg);
			}
			actions.takeScreenshot(driver);
			log.info("Able to click on the edit link in the Edit Ad Material Image");
		} catch (Exception e) {
			log.error("Unable to click on the edit link in the Edit Ad Material Image");
			scenario.write("Unable to click on the edit link in the Edit Ad Material Image");
			throw e;
		}
	}

	@Then("^check if the New secion is displayed$")
	public void check_if_the_New_secion_is_displayed() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.newSection);
			actions.takeScreenshot(driver);
			log.info("The New section is displayed");
		} catch (Exception e) {
			log.error("The New section is not displayed");
			scenario.write("The New section is not displayed");
			throw e;
		}
	}

	@Then("^click on the Submit button$")
	public void click_on_the_Submit_button() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.submitButton);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.submitButton);
			actions.click(adssRegressionSuitePgObjects.submitButton);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Submit button");
		} catch (Exception e) {
			log.error("Unable to click on the Submit button");
			scenario.write("Unable to click on the Submit button");
			throw e;
		}
	}

	@Then("^check if the order number is generated after edit$")
	public void check_if_the_order_number_is_generated_after_edit() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderId);
			orderIdAfterEdit = adssRegressionSuitePgObjects.orderId.getText();
			Assert.assertNotNull(orderIdAfterEdit);
			actions.takeScreenshot(driver);
			log.info("Order number is generated after edit");
			scenario.write("The Order Number generated after edit is " + orderIdAfterEdit);
		} catch (Exception e) {
			log.error("Order number is not generated after edit");
			scenario.write("Order number is not generated after edit");
			throw e;
		}
	}

	@Then("^check if the order number after editing is the same as the order number earlier generated$")
	public void check_if_the_order_number_after_editing_is_the_same_as_the_order_number_earlier_generated() throws Throwable {
		try {
			Assert.assertTrue(!(orderIdAfterEdit.trim().equalsIgnoreCase("")));
			Assert.assertTrue(!(orderIdAfterEdit.trim().equalsIgnoreCase(null)));
			Assert.assertEquals(orderId.trim(), orderIdAfterEdit.trim());
			actions.takeScreenshot(driver);
			log.info("The order number after editing is the same as the order number earlier generated");
		} catch (Exception e) {
			log.error("Unable to check if the order number after editing is the same as the order number earlier generated");
			scenario.write("Unable to check if the order number after editing is the same as the order number earlier generated");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The order number after editing is not the same as the order number earlier generated");
			scenario.write("The order number after editing is not the same as the order number earlier generated");
			throw e;
		}
	}

	@Then("^logout of Adit application$")
	public void logout_of_Adit_application() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.aditUserName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.aditUserName);
			actions.click(adssRegressionSuitePgObjects.aditUserName);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.logoutOptionInAdit);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.logoutOptionInAdit);
			actions.click(adssRegressionSuitePgObjects.logoutOptionInAdit);
			actions.takeScreenshot(driver);
			log.info("Able to logout of Adit application");
		} catch (Exception e) {
			log.error("Unable to logout of Adit application");
			scenario.write("Unable to logout of Adit application");
			throw e;
		}
	}

	@Then("^insert order notes as \"([^\"]*)\" along with the order id$")
	public void insert_order_notes_as_along_with_the_order_id(String orderNotes) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.orderNotes);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.orderNotes);
			/*actions.scrollToElement(driver, adssRegressionSuitePgObjects.orderNotes);*/
			actions.takeScreenshot(driver);
			try{
			actions.type(adssRegressionSuitePgObjects.orderNotes, orderNotes + "-" + notes + orderId);
			}
			catch(WebDriverException e){
				actions.scrollDown(driver);
				actions.type(adssRegressionSuitePgObjects.orderNotes, orderNotes + "-" + notes + orderId);
			}
			log.info("Able to insert order notes as " + orderNotes + " along with the order id");
		} catch (Exception e) {
			log.error("Unable to insert order notes as " + orderNotes + " along with the order id");
			scenario.write("Unable to insert order notes as " + orderNotes + " along with the order id");
			throw e;
		}
	}

	@Then("^select Category as \"([^\"]*)\"$")
	public void select_Category_as(String category) throws Throwable {
		try{
			Thread.sleep(3000);
			Utils.selectDropdown(adssRegressionSuitePgObjects.SelectCategory(category),category);
			actions.takeScreenshot(driver);
			log.info("Able to select category as " + category);
		}
		catch (Exception e) {
			log.error("Unable to select category as " + category);
			scenario.write("Unable to select category as " + category);
			throw e;
		}
	}

	@Then("^select Category DPR as \"([^\"]*)\"$")
	public void select_Category_DPR_as(String category) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.CelebrationType);
			adssRegressionSuitePgObjects.CelebrationType.click();
			Utils.selectDropdown(adssRegressionSuitePgObjects.CelebrationType, category);
			actions.takeScreenshot(driver);
			log.info("Able to select the Category DPR as " + category);
		} catch (Exception e) {
			log.error("Unable to select the Category DPR as " + category);
			scenario.write("Unable to select the Category DPR as " + category);
			throw e;
		}
	}

	@Then("^fill in the Text DPR$")
	public void fill_in_the_Text_DPR() throws Throwable {
		try {
			Utils.fillText(adssRegressionSuitePgObjects.HeadlineDPR,Utils.generateRandomString(8));
			Utils.fillText(adssRegressionSuitePgObjects.AdTextHeaderDPR,Utils.generateRandomString(8));
			Utils.fillText(adssRegressionSuitePgObjects.FirstName,Utils.generateRandomString(8));
			Utils.fillText(adssRegressionSuitePgObjects.LastName,Utils.generateRandomString(8));
			actions.takeScreenshot(driver);
			log.info("Able to fill in the Text DPR");
		} catch (Exception e) {
			log.error("Unable to fill in the Text DPR");
			scenario.write("Unable to fill in the Text DPR");
			throw e;
		}
	}

	@Then("^click on the Other Info tab$")
	public void click_on_the_Other_Info_tab() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.otherInfoTab);
			try {Thread.sleep(3000);}catch(Exception e){};
			actions.click(adssRegressionSuitePgObjects.otherInfoTab);
			try {Thread.sleep(2000);}catch(Exception e){}
			actions.takeScreenshot(driver);
			log.info("Able to click on the Other Info tab");
		} catch (Exception e) {
			log.error("Unable to click on the Other Info tab");
			scenario.write("Unable to click on the Other Info tab");
			throw e;
		}
	}

	@Then("^set contact email as \"([^\"]*)\"$")
	public void set_contact_email_as(String contactEmail) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.contactEmail);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.contactEmail);
			actions.type(adssRegressionSuitePgObjects.contactEmail, contactEmail);
			actions.takeScreenshot(driver);
			log.info("Able to set the contact email as " + contactEmail);
		} catch (Exception e) {
			log.error("Unable to set the contact email as " + contactEmail);
			scenario.write("Unable to set the contact email as " + contactEmail);
			throw e;
		}
	}

	@Then("^fill in the text for MCALL Obitor SSC Obit$")
	public void fill_in_the_text_for_MCALL_Obitor_SSC_Obit() throws Throwable {
		try {
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(3000);
			/*WebElement firstname = driver.findElement(By.xpath("//div[@class='mat-input-wrapper mat-form-field-wrapper'][1]//input[@placeholder='First Name']"));
			firstname.click();
			Thread.sleep(2000);
			firstname.sendKeys(Utils.generateRandomString(8));*/
			//actions.scrollToElement(driver, adssRegressionSuitePgObjects.FirstName);
			//Thread.sleep(1000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.FirstName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.FirstName);
			Thread.sleep(3000);
			Utils.fillText(adssRegressionSuitePgObjects.FirstName,Utils.generateRandomString(8));
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.LastName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.LastName);
			Thread.sleep(3000);
			Utils.fillText(adssRegressionSuitePgObjects.LastName,Utils.generateRandomString(8));

			try {
				if(adssRegressionSuitePgObjects.ObitText.isDisplayed()){
					actions.scrollToElement(driver, adssRegressionSuitePgObjects.ObitText);
					Utils.fillText(adssRegressionSuitePgObjects.ObitText,Utils.getParagraph());
				}
			}catch(Exception e) {
				try{
				Utils.fillText(adssRegressionSuitePgObjects.ObitText1,Utils.getParagraph());
				}
				catch(NoSuchElementException ex){
					Utils.fillText(adssRegressionSuitePgObjects.ObitText2,Utils.getParagraph());
				}
			}
			actions.takeScreenshot(driver);
			log.info("Able to fill in the text for MCALL Obitor SSC Obit");
		} catch (Exception e) {
			log.error("Unable to fill in the text for MCALL Obitor SSC Obit");
			scenario.write("Unable to fill in the text for MCALL Obitor SSC Obit");
			throw e;
		}
	}

	@Then("^set contact name as \"([^\"]*)\"$")
	public void set_contact_name_as(String contactName) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.contactName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.contactName);
			actions.type(adssRegressionSuitePgObjects.contactName, contactName);
			actions.takeScreenshot(driver);
			log.info("Able to set the contact name as " + contactName);
		} catch (Exception e) {
			log.error("Unable to set the contact name as " + contactName);
			scenario.write("Unable to set the contact name as " + contactName);
			throw e;
		}
	}

	@Then("^fill in the Text DPR Obit$")
	public void fill_in_the_Text_DPR_Obit() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.FirstName);
			/*actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.FirstName);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.FirstName);*/
			Utils.fillText(adssRegressionSuitePgObjects.FirstName,Utils.generateRandomString(8));
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.LastName);
			/*actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.LastName);
			 actions.scrollToElement(driver, adssRegressionSuitePgObjects.LastName);*/
			Utils.fillText(adssRegressionSuitePgObjects.LastName,Utils.generateRandomString(8));
			/*WebElement lastname=driver.findElement(By.xpath("//div[@class='mat-input-infix mat-form-field-infix']//input[@placeholder='Last Name']"));
			lastname.sendKeys(Utils.generateRandomString(8));*/
			//adssRegressionSuitePgObjects.LastName.sendKeys("HDGYBCHJB");
			//actions.type(adssRegressionSuitePgObjects.LastName,Utils.generateRandomString(8));
			try{
			actions.waitUntilElementVisibleSmall(driver, adssRegressionSuitePgObjects.ObitText);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.ObitText);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.ObitText);
			Utils.fillText(adssRegressionSuitePgObjects.ObitText,Utils.getParagraph());
			}
			catch(NoSuchElementException | TimeoutException e){
				actions.waitUntilElementVisibleSmall(driver, adssRegressionSuitePgObjects.ObitText1);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.ObitText1);
				actions.scrollToElement(driver, adssRegressionSuitePgObjects.ObitText1);
				Utils.fillText(adssRegressionSuitePgObjects.ObitText1,Utils.getParagraph());
			}
			actions.takeScreenshot(driver);
			log.info("Able to fill in the Text DPR Obit");
		} catch (Exception e) {
			log.error("Unable to fill in the Text DPR Obit");
			scenario.write("Unable to fill in the Text DPR Obit");
			throw e;
		}
	}

	@Then("^fill in the text LAT Obit$")
	public void fill_in_the_text_LAT_Obit() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.FirstName);
			Utils.fillText(adssRegressionSuitePgObjects.FirstName,Utils.generateRandomString(8));
			Utils.fillText(adssRegressionSuitePgObjects.LastName,Utils.generateRandomString(8));
			Utils.fillText(adssRegressionSuitePgObjects.LATObitText,Utils.getParagraph());
			actions.takeScreenshot(driver);
			log.info("Able to fill in the text LAT Obit");
		} catch (Exception e) {
			log.error("Unable to fill in the text LAT Obit");
			scenario.write("Unable to fill in the text LAT Obit");
			throw e;
		}
	}

	@Then("^select the dates LAT Obit as \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void select_the_dates_LAT_Obit_as(String birthMonth,String birthYear,String birthDay,String deathMonth,String deathYear, String deathDay) throws Throwable {
		try {
			Utils.selectDropdown(adssRegressionSuitePgObjects.BirthMonth,birthMonth);
			Utils.selectDropdown(adssRegressionSuitePgObjects.BirthYear,birthYear);
			Utils.selectDropdown(adssRegressionSuitePgObjects.DayOfBirth,birthDay);
			Utils.selectDropdown(adssRegressionSuitePgObjects.DeathMonth,deathMonth);
			Utils.selectDropdown(adssRegressionSuitePgObjects.DeathYear,deathYear);
			Utils.selectDropdown(adssRegressionSuitePgObjects.DayOfDeath,deathDay);
			actions.takeScreenshot(driver);
			log.info("Able to select the dates LAT Obit as " + birthMonth + "," + birthYear + "," + birthDay + "," + deathMonth + "," + deathYear + ", " + deathDay);
		} catch (Exception e) {
			log.error("Unable to select the dates LAT Obit as " + birthMonth + "," + birthYear + "," + birthDay + "," + deathMonth + "," + deathYear + ", " + deathDay);
			scenario.write("Unable to select the dates LAT Obit as " + birthMonth + "," + birthYear + "," + birthDay + "," + deathMonth + "," + deathYear + ", " + deathDay);
			throw e;
		}
	}

	@Then("^upload the photo if the Logo Section is available$")
	public void upload_the_photo_if_the_Logo_Section_is_available() throws Throwable {
		try {
			try {
				clickLogoUploadButton();		
				uploadPhoto();
				clickSaveButton();
				log.info("Logo Uploaded");
			}catch(Exception e)
			{
				log.info("Logo Section not available");
			}
			actions.takeScreenshot(driver);
			log.info("Able to upload the photo if the Logo Section is available");
		} catch (Exception e) {
			log.error("Unable to upload the photo if the Logo Section is available");
			scenario.write("Unable to upload the photo if the Logo Section is available");
			throw e;
		}
	}

	public void clickSaveButton() {
		actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.saveImageButton);
		actions.click(adssRegressionSuitePgObjects.saveImageButton);
	}


	public void uploadPhoto() {

		adssRegressionSuitePgObjects.uploadLink.sendKeys(System.getProperty("user.dir") + "\\Images\\"+Utils.pickRandomIamge1());

	}


	public void clickLogoUploadButton() {

		try {
			adssRegressionSuitePgObjects.LogoUpload.click();
		}catch(Exception e) {
			adssRegressionSuitePgObjects.showUploadLogo.click();
			adssRegressionSuitePgObjects.LogoUpload.click();
		}
	}

	@Then("^click on the edit link in the Edit Order Page$")
	public void click_on_the_edit_link_in_the_Edit_Order_Page() throws Throwable {
		try {
			click_on_Edit_Link_in_Order_Detail_Page();
			actions.takeScreenshot(driver);
			log.info("Able to click on the edit link in the Edit Order Page");
		} catch (Exception e) {
			log.error("Unable to click on the edit link in the Edit Order Page");
			scenario.write("Unable to click on the edit link in the Edit Order Page");
			throw e;
		}
	}

	@Then("^click on the Edit link in the Design Template page$")
	public void click_on_the_Edit_link_in_the_Design_Template_page() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.editLinkInDesignTemplatePg);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.editLinkInDesignTemplatePg);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.editLinkInDesignTemplatePg);
			actions.javascriptClk(driver, adssRegressionSuitePgObjects.editLinkInDesignTemplatePg);
			actions.takeScreenshot(driver);
			waitForPageToLoad();
			log.info("Able to click on the edit link in the Design Template Page");
		} catch (Exception e) {
			log.error("Unable to click on the edit link in the Design Template Page");
			scenario.write("Unable to click on the edit link in the Design Template Page");
			throw e;
		}
	}

	@Then("^click on the Logo Upload button$")
	public void click_on_the_Logo_Upload_button() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.LogoUpload);
			adssRegressionSuitePgObjects.LogoUpload.click();
			actions.takeScreenshot(driver);
			log.info("Able to click on the Logo Upload button");
		} catch (Exception e) {
			log.error("Unable to click on the Logo Upload button");
			scenario.write("Unable to click on the Logo Upload button");
			throw e;
		}
	}

	@Then("^click on Save button$")
	public void click_on_Save_button() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.saveImageButton);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.saveImageButton);
			actions.click(adssRegressionSuitePgObjects.saveImageButton);
			actions.takeScreenshot(driver);
			log.info("Able to click on Save button");
		} catch (Exception e) {
			log.error("Unable to click on Save button");
			scenario.write("Unable to click on Save button");
			throw e;
		}
	}

	@Then("^fill in the text ctc obit$")
	public void fill_in_the_text_ctc_obit() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.FirstName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.FirstName);
			Utils.fillText(adssRegressionSuitePgObjects.FirstName,Utils.generateRandomString(8));
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.LastName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.LastName);
			Utils.fillText(adssRegressionSuitePgObjects.LastName,Utils.generateRandomString(8));
			try{
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.CTCObitText);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.CTCObitText);
			Utils.fillText(adssRegressionSuitePgObjects.CTCObitText,Utils.getParagraph());
			}
			catch (NoSuchElementException | TimeoutException e) {
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.ObitText2);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.ObitText2);
				Utils.fillText(adssRegressionSuitePgObjects.ObitText2,Utils.getParagraph());
			}
			/*actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.funeralHomeName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.funeralHomeName);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.funeralHomeName);
			Utils.fillText(adssRegressionSuitePgObjects.funeralHomeName,Utils.generateRandomString(8));*/
			actions.takeScreenshot(driver);
			log.info("Able to fill in the text ctc obit");
		} catch (Exception e) {
			log.error("Unable to fill in the text ctc obit");
			scenario.write("Unable to fill in the text ctc obit");
			throw e;
		}
	}

	@Then("^set city as \"([^\"]*)\"$")
	public void set_city_as(String cityName) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.city);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.city);
			actions.type(adssRegressionSuitePgObjects.city, cityName);
			actions.takeScreenshot(driver);
			log.info("Able to set city as " + cityName);
		} catch (Exception e) {
			log.error("Unable to set city as " + cityName);
			scenario.write("Unable to set city as " + cityName);
			throw e;
		}
	}

	@Then("^get the Run Dates Online Ad Print in Order Line$")
	public void get_the_Run_Dates_Online_Ad_Print_in_Order_Line() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDateOnline.size()>0);
			RunDatesList.add(new ArrayList(actions.getRunDatesOnlineAdOrderLine(1, adssRegressionSuitePgObjects.orderDateOnline)));
			actions.takeScreenshot(driver);
			log.info("Able to get the Run Dates Online Ad Print in Order Line");
		}catch (Exception e) {
			log.error("Error in getting the Run Dates Online Ad Print in Order Line");
			scenario.write("Error in getting the Run Dates Online Ad Print in Order Line");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to get the Run Dates Online Ad Print in Order Line");
			scenario.write("Unable to get the Run Dates Online Ad Print in Order Line");
			throw e;
		}
	}

	@Then("^get all the Run Dates List from Adit$")
	public void get_all_the_Run_Dates_List_from_Adit() throws Throwable {
		try {
			Thread.sleep(6000);
			Assert.assertTrue(adssRegressionSuitePgObjects.aditOrderLines.size()>0);

			for(int i=0;i<adssRegressionSuitePgObjects.aditOrderLines.size();i++)
			{
				RunDatesListAdit.add(getOrderPubDatesOrderLine(i+1, driver));
			}
			actions.takeScreenshot(driver);
			log.info("Able to get all the Run Dates List from Adit");
		} catch (Exception e) {
			log.error("Unable to get all the Run Dates List from Adit");
			scenario.write("Unable to get all the Run Dates List from Adit");
			throw e;
		}
	}

	@Then("^check if all the ADSS and ADIT rundates are same$")
	public void check_if_all_the_ADSS_and_ADIT_rundates_are_same() throws Throwable {
		try {
			Assert.assertTrue(RunDatesList.size()>0);
			Assert.assertTrue(RunDatesListAdit.size()>0);
			Assert.assertEquals(RunDatesList.get(0).get(0),RunDatesListAdit.get(0));
			Assert.assertEquals(RunDatesList.get(1).get(0),RunDatesListAdit.get(1));
			log.info("All the ADSS and ADIT rundates are same");
		} catch (Exception e) {
			log.error("Unable to check if all the ADSS and ADIT rundates are same");
			scenario.write("Unable to check if all the ADSS and ADIT rundates are same");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Not all the ADSS and ADIT rundates are same");
			scenario.write("Not all the ADSS and ADIT rundates are same");
			throw e;
		}
	}


	public String getOrderPubDatesOrderLine(int index, WebDriver driver) {

		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", aditOrderLinesPubDates);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String OrderPrice=adssRegressionSuitePgObjects.aditOrderLinesPubDates.get(index-1).getText();
		//logger.info(OrderPrice);
		return OrderPrice;

	}

	public ArrayList<String> getRunDatesPrintAdOrderLine(int index, int numberofRunDates) {

		ArrayList<String> RunDates = new ArrayList<String>();

		for(int i=0;i<numberofRunDates;i++)
			RunDates.add(Utils.getExactRunDate(adssRegressionSuitePgObjects.orderDate.get(i).getText().trim()));


		System.out.println(RunDates.get(RunDates.size()-1));
		return RunDates;

	}

	public ArrayList<String> getRunDatesOnlineAdOrderLine(int index, int numberofRunDates) {

		ArrayList<String> RunDates = new ArrayList<String>();

		for(int i=0;i<numberofRunDates;i++)
			RunDates.add(Utils.getExactOnlineRunDate(adssRegressionSuitePgObjects.orderDateOnline.get(i).getText()));

		System.out.println(RunDates.get(RunDates.size()-1));
		return RunDates;

	}

	@Then("^get the Run Dates Print In Order Line after Edit$")
	public void get_the_Run_Dates_Print_In_Order_Line_after_Edit() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDate.size()>0);
			RunDatesListEdit.add(new ArrayList(getRunDatesPrintAdOrderLine(1, adssRegressionSuitePgObjects.orderDate.size())));
			RunDatesListEdit.add(new ArrayList(getRunDatesOnlineAdOrderLine(1, adssRegressionSuitePgObjects.orderDateOnline.size())));
			Assert.assertTrue(RunDatesListEdit.size()>0);
			actions.takeScreenshot(driver);
			log.info("Able to get the Run Dates Print In Order Line after Edit");
		} catch (Exception e) {
			log.error("Error in getting the Run Dates Print in Order Line after Edit");
			scenario.write("Error in getting the Run Dates Print in Order Line after Edit");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to get the Run Dates Print In Order Line after Edit");
			scenario.write("Unable to get the Run Dates Print In Order Line after Edit");
			throw e;
		}
	}

	@Then("^get the Run Dates Online Ad Print in Order Line after Edit$")
	public void get_the_Run_Dates_Online_Ad_Print_in_Order_Line_after_Edit() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDateOnline.size()>0);
			RunDatesListEdit.add(new ArrayList(getRunDatesOnlineAdOrderLine(1, adssRegressionSuitePgObjects.orderDateOnline.size())));
			actions.takeScreenshot(driver);
			log.info("Able to get the Run Dates Online Ad Print in Order Line after Edit");
		} catch (Exception e) {
			log.error("Error in getting the Run Dates Online Ad Print in Order Line after Edit");
			scenario.write("Error in getting the Run Dates Online Ad Print in Order Line after Edit");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to get the Run Dates Online Ad Print in Order Line after Edit");
			scenario.write("Unable to get the Run Dates Online Ad Print in Order Line after Edit");
			throw e;
		}
	}

	@Then("^get all the Run Dates List after Edit from Adit$")
	public void get_all_the_Run_Dates_List_after_Edit_from_Adit() throws Throwable {
		try {
			Thread.sleep(5000);
			Assert.assertTrue(adssRegressionSuitePgObjects.aditOrderLines.size()>0);
			for(int i=0;i<adssRegressionSuitePgObjects.aditOrderLines.size();i++)
			{
				RunDatesListAditEditAd.add(getOrderPubDatesOrderLine((i+1), driver));
			}
			actions.takeScreenshot(driver);
			log.info("Able to get all the Run Dates List after Edit from Adit");
		} catch (Exception e) {
			log.error("Error in getting all the Run Dates List after Edit from Adit");
			scenario.write("Error in getting all the Run Dates List after Edit from Adit");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to get all the Run Dates List after Edit from Adit");
			scenario.write("Unable to get all the Run Dates List after Edit from Adit");
			throw e;
		}
	}

	@Then("^check if all the ADSS and ADIT rundates after Edit are same$")
	public void check_if_all_the_ADSS_and_ADIT_rundates_after_Edit_are_same() throws Throwable {
		try {
			Assert.assertTrue(RunDatesListEdit.size()>0);
			Assert.assertTrue(RunDatesListAditEditAd.size()>0);
			Assert.assertEquals(RunDatesListEdit.get(0).get(0),RunDatesListAditEditAd.get(0));
			Assert.assertEquals(RunDatesListEdit.get(1).get(0),RunDatesListAditEditAd.get(1));
			actions.takeScreenshot(driver);
			log.info("The ADSS and ADIT rundates after Edit are the same");
		} catch (Exception e) {
			log.error("Unable to check if all the ADSS and ADIT rundates after Edit are same");
			scenario.write("Unable to check if all the ADSS and ADIT rundates after Edit are same");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and ADIT rundates after Edit are not the same");
			scenario.write("The ADSS and ADIT rundates after Edit are not the same");
			throw e;
		}
	}

	@Then("^check if all the ADSS and ADIT rundates after Edit are same for Non-Obit Package$")
	public void check_if_all_the_ADSS_and_ADIT_rundates_after_Edit_are_same_for_Non_Obit_Package() throws Throwable {
		try {
			Assert.assertTrue(RunDatesListEdit.size()>0);
			Assert.assertTrue(RunDatesListAditEditAd.size()>0);
			Assert.assertEquals(RunDatesListEdit.get(0).get(0),RunDatesListAditEditAd.get(0));
			actions.takeScreenshot(driver);
			log.info("The ADSS and ADIT rundates after Edit are the same for Non-Obit Package");
		} catch (Exception e) {
			log.error("Unable to check if all the ADSS and ADIT rundates after Edit are same for Non-Obit Package");
			scenario.write("Unable to check if all the ADSS and ADIT rundates after Edit are same for Non-Obit Package");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and ADIT rundates after Edit are not the same for Non-Obit Package");
			scenario.write("The ADSS and ADIT rundates after Edit are not the same for Non-Obit Package");
			throw e;
		}
	}

	@Then("^check if ADSS and after Edit ADIT total prices are same for Loc users$")
	public void check_if_ADSS_and_after_Edit_ADIT_total_prices_are_same_for_Loc_users() throws Throwable {
		try {
			String TotalPriceAditEditAd="$"+String.valueOf(Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.aditOrderPrice.getText())));
			String TotalPriceADSS="$"+String.valueOf(Utils.getLOCprice(Double.parseDouble(Utils.getPriceValue(totalPrice))));
			Assert.assertEquals(TotalPriceAditEditAd,TotalPriceADSS);
			actions.takeScreenshot(driver);
			log.info("The ADSS and after Edit ADIT total prices are the same for Loc users");
		} catch (Exception e) {
			log.error("Unable to check if ADSS and after Edit ADIT total prices are same for Loc users");
			scenario.write("Unable to check if ADSS and after Edit ADIT total prices are same for Loc users");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and after Edit ADIT total prices are not the same for Loc users");
			scenario.write("The ADSS and after Edit ADIT total prices are not the same for Loc users");
			throw e;
		}
	}

	@Then("^get the Run Dates Print In Order Line after Renew$")
	public void get_the_Run_Dates_Print_In_Order_Line_after_Renew() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDate.size()>0);
			RunDatesListRenewAd.add(new ArrayList(getRunDatesPrintAdOrderLine(1, adssRegressionSuitePgObjects.orderDate.size())));
			actions.takeScreenshot(driver);
			log.info("Able to get the Run Dates Print In Order Line after Renew");
		} catch (Exception e) {
			log.error("Error in geting the Run Dates Print In Order Line after Renew");
			scenario.write("Error in geting the Run Dates Print In Order Line after Renew");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to get the Run Dates Print In Order Line after Renew");
			scenario.write("Unable to get the Run Dates Print In Order Line after Renew");
			throw e;
		}
	}

	@Then("^get the Run Dates Online Ad in Order Line after Renew$")
	public void get_the_Run_Dates_Online_Ad_in_Order_Line_after_Renew() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDateOnline.size()>0);
			RunDatesListRenewAd.add(new ArrayList(getRunDatesOnlineAdOrderLine(1, adssRegressionSuitePgObjects.orderDateOnline.size())));
			actions.takeScreenshot(driver);
			log.info("Able to get the Run Dates Online Ad in Order Line after Renew");
		} catch (Exception e) {
			log.error("Error  in getting the Run Dates Online Ad in Order Line after Renew");
			scenario.write("Error  in getting the Run Dates Online Ad in Order Line after Renew");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to get the Run Dates Online Ad in Order Line after Renew");
			scenario.write("Unable to get the Run Dates Online Ad in Order Line after Renew");
			throw e;
		}
	}

	@Then("^get the order pub dates in order line after renew$")
	public void get_the_order_pub_dates_in_order_line_after_renew() throws Throwable {
		try {
			Thread.sleep(5000);
			Assert.assertTrue(adssRegressionSuitePgObjects.aditOrderLines.size()>0);
			for(int i=0;i<adssRegressionSuitePgObjects.aditOrderLines.size();i++)
			{
				RunDatesListAditRenewAd.add(getOrderPubDatesOrderLine((i+1), driver));
			}
			actions.takeScreenshot(driver);
			log.info("Able to get the order pub dates in order line after renew");
		} catch (Exception e) {
			log.error("Error in getting the order pub dates in order line after renew");
			scenario.write("Error in getting the order pub dates in order line after renew");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to get the order pub dates in order line after renew");
			scenario.write("Unable to get the order pub dates in order line after renew");
			throw e;
		}
	}

	@Then("^check if all the ADSS and ADIT rundates after Renew are same$")
	public void check_if_all_the_ADSS_and_ADIT_rundates_after_Renew_are_same() throws Throwable {
		try {
			Assert.assertTrue(RunDatesListRenewAd.size()>0);
			Assert.assertTrue(RunDatesListAditRenewAd.size()>0);
			Assert.assertEquals(RunDatesListRenewAd.get(0).get(0),RunDatesListAditRenewAd.get(0));
			Assert.assertEquals(RunDatesListRenewAd.get(1).get(0),RunDatesListAditRenewAd.get(1));
			actions.takeScreenshot(driver);
			log.info("All the ADSS and ADIT rundates after Renew are same");
		} catch (Exception e) {
			log.error("Unable to check if all the ADSS and ADIT rundates after Renew are same");
			scenario.write("Unable to check if all the ADSS and ADIT rundates after Renew are same");
			throw e;
		}
		catch (AssertionError e) {
			log.error("All the ADSS and ADIT rundates after Renew are not the same");
			scenario.write("All the ADSS and ADIT rundates after Renew are not the same");
			throw e;
		}
	}

	@Then("^check if ADSS and after Renew ADIT total prices are same for Loc users$")
	public void check_if_ADSS_and_after_Renew_ADIT_total_prices_are_same_for_Loc_users() throws Throwable {
		try {
			TotalPriceAditRenewAd="$"+String.valueOf(Double.parseDouble(Utils.getPriceValue(getOrderPrice(driver))));
			String TotalPriceADSSRenewAd="$"+String.valueOf(Utils.getLOCprice(Double.parseDouble(Utils.getPriceValue(totalPrice))));
			Assert.assertEquals(TotalPriceAditRenewAd,TotalPriceADSSRenewAd);
			actions.takeScreenshot(driver);
			log.info("The ADSS and after Renew ADIT total prices are same for Loc users");
		} catch (Exception e) {
			log.error("Unable to check if ADSS and after Renew ADIT total prices are same for Loc users");
			scenario.write("Unable to check if ADSS and after Renew ADIT total prices are same for Loc users");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Then ADSS and after Renew ADIT total prices are not the same for Loc users");
			scenario.write("Then ADSS and after Renew ADIT total prices are not the same for Loc users");
			throw e;
		}
	}

	public String getOrderPrice(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adssRegressionSuitePgObjects.aditOrderPrice);
		return adssRegressionSuitePgObjects.aditOrderPrice.getText();
	}


	@Then("^change the order to processed, if the  renewed price is less than one dollar, else reject$")
	public void change_the_order_to_processed_if_the_renewed_price_is_less_than_one_dollar_else_reject() throws Throwable {
		boolean ApprovalStatusRenewAd=Utils.checkIsApprove(Double.parseDouble(Utils.getPriceValue(TotalPriceAditRenewAd)));
		if(ApprovalStatusRenewAd)
		{
			log.info("Order is passed for Approval");

			clickApproveButton();
			log.info("Approve Button Clicked");
			clickConfirmApproveButton();
			log.info("Confirm-Approve Button Clicked");

			driver.navigate().back();

			String orderStatus=checkOrderStatus();
			Assert.assertEquals(orderStatus,"Processed");
			log.info("Order Status Changed to Processed");
		}		
		else
		{
			log.info("Order is not passed for Approval");

			clickRejectButton();
			log.info("Reject Button Clicked");
			clickConfirmRejectButton();
			log.info("Confirm-Reject Button Clicked");

			driver.navigate().back();

			String orderStatus=checkOrderStatus();
			Assert.assertEquals(orderStatus,"Rejected");
			log.info("Order Status Changed to Rejected");
		}
	}

	@Then("^fill in the text cct obit$")
	public void fill_in_the_text_cct_obit() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.FirstName);
			Utils.fillText(adssRegressionSuitePgObjects.FirstName,Utils.generateRandomString(8));
			Utils.fillText(adssRegressionSuitePgObjects.LastName,Utils.generateRandomString(8));
			try{
				Utils.fillText(adssRegressionSuitePgObjects.ObitText1,Utils.getParagraph());
				}
				catch(NoSuchElementException ex){
					Utils.fillText(adssRegressionSuitePgObjects.ObitText2,Utils.getParagraph());
				}
			Utils.fillText(adssRegressionSuitePgObjects.Condolence,Utils.generateRandomString(8));
			actions.takeScreenshot(driver);
			log.info("Able to fill in the text cct obit");
		} catch (Exception e) {
			log.error("Unable to fill in the text cct obit");
			scenario.write("Unable to fill in the text cct obit");
			throw e;
		}
	}

	@Then("^set customer name as \"([^\"]*)\"$")
	public void set_customer_name_as(String customerName) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.CustomerName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.CustomerName);
			actions.type(adssRegressionSuitePgObjects.CustomerName, customerName);
			actions.takeScreenshot(driver);
			log.info("Able to set the customer name as " + customerName);
		} catch (Exception e) {
			log.error("Unable to set the customer name as " + customerName);
			scenario.write("Unable to set the customer name as " + customerName);
			throw e;
		}
	}

	@Then("^set adress as \"([^\"]*)\"$")
	public void set_adress_as(String adress) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.Address);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.Address);
			actions.type(adssRegressionSuitePgObjects.Address, adress);
			actions.takeScreenshot(driver);
			log.info("Able to set the adress as " + adress);
		} catch (Exception e) {
			log.error("Unable to set the adress as " + adress);
			scenario.write("Unable to set the adress as " + adress);
			throw e;
		}
	}

	@Then("^set state as \"([^\"]*)\"$")
	public void set_state_as(String statename) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.State(statename));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.State(statename));
			Utils.selectDropdown( adssRegressionSuitePgObjects.State(statename), statename);
			actions.takeScreenshot(driver);
			log.info("Able to set the state as " + statename);
		} catch (Exception e) {
			log.error("Unable to set the state as " + statename);
			scenario.write("Unable to set the state as " + statename);
			throw e;
		}
	}

	@Then("^set Zip Code as \"([^\"]*)\"$")
	public void set_Zip_Code_as(String zipCode) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.ZipCode);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.ZipCode);
			actions.type(adssRegressionSuitePgObjects.ZipCode, zipCode);
			actions.takeScreenshot(driver);
			log.info("Able to set the Zip Code as " + zipCode);
		} catch (Exception e) {
			log.error("Unable to set the Zip Code as " + zipCode);
			scenario.write("Unable to set the Zip Code as " + zipCode);
			throw e;
		}
	}

	@Then("^click on the Next Ad Config Link$")
	public void click_on_the_Next_Ad_Config_Link() throws Throwable {
		try {
			try {
				Thread.sleep(7000);
				action.moveToElement(adssRegressionSuitePgObjects.nextAdConfigLink.get(adssRegressionSuitePgObjects.nextAdConfigLink.size()-1))
				.click().build().perform();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				adssRegressionSuitePgObjects.nextAdConfigLink.get(adssRegressionSuitePgObjects.nextAdConfigLink.size()-1).click();
			}
			actions.takeScreenshot(driver);
			log.info("Able to click on the Next Ad Config Link");
		} catch (Exception e) {
			log.error("Unable to click on the Next Ad Config Link");
			scenario.write("Unable to click on the Next Ad Config Link");
			throw e;
		}
	}

	@Then("^click on the second Schedule Tab$")
	public void click_on_the_second_Schedule_Tab() throws Throwable {
		try {
			
			try {
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			//wait.until(ExpectedConditions.visibilityOf(scheduleTab));
			adssRegressionSuitePgObjects.scheduleTab2.click();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actions.takeScreenshot(driver);
			log.info("Able to clicck on the second Schedule Tab");
		} catch (Exception e) {
			log.error("Unable to clicck on the second Schedule Tab");
			scenario.write("Unable to clicck on the second Schedule Tab");
			throw e;
		}
	}

	@Then("^click on the second Layout tab$")
	public void click_on_the_second_Layout_tab() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.layoutTab2);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.layoutTab2);
			actions.click(adssRegressionSuitePgObjects.layoutTab2);
			actions.takeScreenshot(driver);
			try {Thread.sleep(5000);}catch(Exception e){}
			log.info("Able to click on the second Layout tab");
		} catch (Exception e) {
			log.error("Unable to click on the second Layout tab");
			scenario.write("Unable to click on the second Layout tab");
			throw e;
		}
	}

	@Then("^note the (\\d+) and (\\d+) order price order lines$")
	public void note_the_and_order_price_order_lines(int orderPriceOrderLineIdx1, int orderPriceOrderLineIdx2) throws Throwable {
		try {
			OrderPrice.clear();
			OrderPrice.add(getOrderPriceOrderLineInAdss(orderPriceOrderLineIdx1));
			OrderPrice.add(getOrderPriceOrderLineInAdss(orderPriceOrderLineIdx2));
			actions.takeScreenshot(driver);
			log.info("Able to note the " + orderPriceOrderLineIdx1 + " and " + orderPriceOrderLineIdx2 + " order price order lines");
		} catch (Exception e) {
			log.error("Unable to note the " + orderPriceOrderLineIdx1 + " and " + orderPriceOrderLineIdx2 + " order price order lines");
			scenario.write("Unable to note the " + orderPriceOrderLineIdx1 + " and " + orderPriceOrderLineIdx2 + " order price order lines");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Error in noting the " + orderPriceOrderLineIdx1 + " and " + orderPriceOrderLineIdx2 + " order price order lines");
			scenario.write("Error in noting the " + orderPriceOrderLineIdx1 + " and " + orderPriceOrderLineIdx2 + " order price order lines");
			throw e;
		}
	}

	public String getOrderPubDatesOrderLine(int index) {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		actions.waitUntilListOfElementsVisible(driver, adssRegressionSuitePgObjects.aditOrderLinesPubDates);
		String OrderPrice=adssRegressionSuitePgObjects.aditOrderLinesPubDates.get(index-1).getText();
		return OrderPrice;

	}

	@Then("^capture the Rundates Value for LAT$")
	public void capture_the_Rundates_Value_for_LAT() throws Throwable {
		try {
			RunDatesListAdit.clear();
			RunDatesListAdit.add(getOrderPubDatesOrderLine(1));
			RunDatesListAdit.add(getOrderPubDatesOrderLine(2));
			actions.takeScreenshot(driver);
			log.info("Able to capture the Rundates Value for LAT");
		} catch (Exception e) {
			log.error("Unable to capture the Rundates Value for LAT");
			scenario.write("Unable to capture the Rundates Value for LAT");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Error in capturing the Rundates Value for LAT");
			scenario.write("Error in capturing the Rundates Value for LAT");
			throw e;
		}
	}

	@Then("^check if ADSS and ADIT rundates are same for LAT$")
	public void check_if_ADSS_and_ADIT_rundates_are_same_for_LAT() throws Throwable {
		try {
			Assert.assertEquals(RunDatesList.get(0).get(0),RunDatesListAdit.get(0));
			Assert.assertEquals(RunDatesList.get(1).get(0),RunDatesListAdit.get(1));
			actions.takeScreenshot(driver);
			log.info("The ADSS and ADIT rundates are same for LAT");
		} catch (Exception e) {
			log.error("Unable to check if ADSS and ADIT rundates are same for LAT");
			scenario.write("Unable to check if ADSS and ADIT rundates are same for LAT");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and ADIT rundates are not the same for LAT");
			scenario.write("The ADSS and ADIT rundates are not the same for LAT");
			throw e;
		}
	}

	@Then("^check if the ADSS and Adit orderlines price are same for LAT$")
	public void check_if_the_ADSS_and_Adit_orderlines_price_are_same_for_LAT() throws Throwable {
		try {
			OrderLinesPriceAdit.add(getOrderPriceOrderLine(1));
			OrderLinesPriceAdit.add(getOrderPriceOrderLine(2));
			Assert.assertEquals(OrderPrice.get(0),OrderLinesPriceAdit.get(0));
			Assert.assertEquals(OrderPrice.get(1),OrderLinesPriceAdit.get(1));
			actions.takeScreenshot(driver);
			log.info("The ADSS and Adit orderlines price are same for LAT");
		} catch (Exception e) {
			log.error("Unable to check if the ADSS and Adit orderlines price are same for LAT");
			scenario.write("Unable to check if the ADSS and Adit orderlines price are same for LAT");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and Adit orderlines price are not the same for LAT");
			scenario.write("The ADSS and Adit orderlines price are not the same for LAT");
			throw e;
		}
	}

	public String getOrderPrice() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adssRegressionSuitePgObjects.aditOrderPrice);
		return adssRegressionSuitePgObjects.aditOrderPrice.getText();

	}

	@Then("^check if ADSS and ADIT total prices are same for Non-Loc users for LAT$")
	public void check_if_ADSS_and_ADIT_total_prices_are_same_for_Non_Loc_users_for_LAT() throws Throwable {
		try {
			TotalPriceAdit=getOrderPrice();
			String TotalPriceADSS="$"+String.valueOf(Double.parseDouble(Utils.getPriceValue(OrderPrice.get(0)))+Double.parseDouble(Utils.getPriceValue(OrderPrice.get(1))));
			try{
			Assert.assertEquals(TotalPriceAdit,TotalPriceADSS);
			}
			catch(AssertionError e){
				TotalPriceADSS = TotalPriceADSS + "0";
				Assert.assertEquals(TotalPriceAdit,TotalPriceADSS);
			}
			actions.takeScreenshot(driver);
			log.info("The ADSS and ADIT total prices are same for Non-Loc users for LAT");
		} catch (Exception e) {
			log.error("Unable to check if ADSS and ADIT total prices are same for Non-Loc users for LAT");
			scenario.write("Unable to check if ADSS and ADIT total prices are same for Non-Loc users for LAT");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and ADIT total prices are not the same for Loc users for LAT");
			scenario.write("The ADSS and ADIT total prices are not the same for Loc users for LAT");
			throw e;
		}
	}

	@Then("^fill in the text SDT$")
	public void fill_in_the_text_SDT() throws Throwable {
		try {
			Utils.selectDropdown(adssRegressionSuitePgObjects.PetType, "Birds");
			try {Thread.sleep(2000);}catch(Exception e) {}
			Utils.selectDropdown(adssRegressionSuitePgObjects.Breed, "Parrot");
			Utils.fillText(adssRegressionSuitePgObjects.AdText, Utils.generateRandomString(10));
			actions.takeScreenshot(driver);
			log.info("Able to fill in the text SDT");
		} catch (Exception e) {
			log.error("Unable to fill in the text SDT");
			scenario.write("Unable to fill in the text SDT");
			throw e;
		}
	}

	@Then("^set phone number as \"([^\"]*)\"$")
	public void set_phone_number_as(String phoneNr) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.PhoneNumber);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.PhoneNumber);
			actions.type(adssRegressionSuitePgObjects.PhoneNumber, phoneNr);
			actions.takeScreenshot(driver);
			log.info("Able to set phone number as " + phoneNr);
		} catch (Exception e) {
			log.error("Unable to set phone number as " + phoneNr);
			scenario.write("Unable to set phone number as " + phoneNr);
			throw e;
		}
	}

	@Then("^set price as \"([^\"]*)\"$")
	public void set_price_as(String price) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.Price);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.Price);
			actions.type(adssRegressionSuitePgObjects.Price, price);
			actions.takeScreenshot(driver);
			log.info("Able to set price as " + price);
		} catch (Exception e) {
			log.error("Unable to set price as " + price);
			scenario.write("Unable to set price as " + price);
			throw e;
		}
	}

	@Then("^set enhancement email as \"([^\"]*)\"$")
	public void set_enhancement_email_as(String enhancementEmail) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.EnhancementEmail);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.EnhancementEmail);
			actions.type(adssRegressionSuitePgObjects.EnhancementEmail, enhancementEmail);
			actions.takeScreenshot(driver);
			log.info("Able to set enhancement email as " + enhancementEmail);
		} catch (Exception e) {
			log.error("Unable to set enhancement email as " + enhancementEmail);
			scenario.write("Unable to set enhancement email as " + enhancementEmail);
			throw e;
		}
	}

	@Then("^set Ad highlight$")
	public void set_Ad_highlight() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.adHighlight);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.adHighlight);
			actions.click(adssRegressionSuitePgObjects.adHighlight);
			log.info("Able to set Add highlight");
		} catch (Exception e) {
			log.error("Unable to set Add highlight");
			scenario.write("Unable to set Add highlight");
			throw e;
		}
	}

	@Then("^click Ad Upsells$")
	public void click_Ad_Upsells() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.adUpsells);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.adUpsells);
			action.moveToElement(adssRegressionSuitePgObjects.adUpsells);
			actions.click(adssRegressionSuitePgObjects.adUpsells);
			actions.takeScreenshot(driver);
			log.info("Able to click Ad Upsells");
		} catch (Exception e) {
			log.error("Unable to click Ad Upsells");
			scenario.write("Unable to click Ad Upsells");
			throw e;
		}
	}

	@Then("^upload Ad Upsells$")
	public void upload_Ad_Upsells() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.uploadUpsells.size()>0);
			for(int i=0;i<adssRegressionSuitePgObjects.uploadUpsells.size();i++)
			{
				adssRegressionSuitePgObjects.uploadUpsells.get(i).sendKeys(System.getProperty("user.dir") + "\\Images\\"+Utils.pickRandomIamge1());
				try {Thread.sleep(10000);}catch(Exception e){}
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.saveImage);
				actions.click(adssRegressionSuitePgObjects.saveImage);
				try
				{
					Thread.sleep(3000);
				}
				catch(Exception e)
				{

				}
				try {Thread.sleep(4000);}catch(Exception e){}
				i=adssRegressionSuitePgObjects.uploadUpsells.size();

			}
			actions.takeScreenshot(driver);
			log.info("Able to upload Ad Upsells");
		} catch (Exception e) {
			log.error("Unable to upload Ad Upsells");
			scenario.write("Unable to upload Ad Upsells");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Error in uploading Ad Upsells");
			scenario.write("Error in uploading Ad Upsells");
			throw e;
		}
	}

	public ArrayList<String> getRunDatesPrintAdOrderLine1(int index, int numberofRunDates) {

		ArrayList<String> RunDates = new ArrayList<String>();

		for(int i=0;i<numberofRunDates;i++)
			RunDates.add(Utils.getExactRunDate(adssRegressionSuitePgObjects.orderDate1.get(i).getText().trim()));

		System.out.println(RunDates.get(RunDates.size()-1));
		return RunDates;

	}

	public ArrayList<String> getRunDatesOnlineAdOrderLine1(int index, int numberofRunDates) {

		ArrayList<String> RunDates = new ArrayList<String>();

		for(int i=0;i<numberofRunDates;i++)
			RunDates.add(Utils.getExactOnlineRunDate(adssRegressionSuitePgObjects.orderDateOnline1.get(i).getText()));

		System.out.println(RunDates.get(RunDates.size()-1));
		return RunDates;

	}

	@Then("^capture all the ADSS RunDates Value for SDTObit$")
	public void capture_all_the_ADSS_RunDates_Value_for_SDTObit() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDate.size()>0);
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDateOnline.size()>0);
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDateOnline1.size()>0);
			Assert.assertTrue(adssRegressionSuitePgObjects.orderDate1.size()>0);
			RunDatesList.add(new ArrayList(getRunDatesPrintAdOrderLine(1, adssRegressionSuitePgObjects.orderDate.size())));
			RunDatesList.add(new ArrayList(getRunDatesOnlineAdOrderLine(1, adssRegressionSuitePgObjects.orderDateOnline.size())));
			RunDatesList.add(new ArrayList(getRunDatesOnlineAdOrderLine1(1, adssRegressionSuitePgObjects.orderDateOnline1.size())));
			RunDatesList.add(new ArrayList(getRunDatesPrintAdOrderLine1(1, adssRegressionSuitePgObjects.orderDate1.size())));
			actions.takeScreenshot(driver);
			log.info("Able to capture all the ADSS RunDates Value for SDTObit");
		} catch (Exception e) {
			log.error("Error in capturing all the ADSS RunDates Value for SDTObit");
			scenario.write("Error in capturing all the ADSS RunDates Value for SDTObit");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to capture all the ADSS RunDates Value for SDTObit");
			scenario.write("Unable to capture all the ADSS RunDates Value for SDTObit");
			throw e;
		}
	}

	@Then("^capture all the Adit RunDates Value for SDTObit$")
	public void capture_all_the_Adit_RunDates_Value_for_SDTObit() throws Throwable {
		try {
			Thread.sleep(5000);
			Assert.assertTrue(adssRegressionSuitePgObjects.aditOrderLines.size()>0);
			for(int i=0;i<adssRegressionSuitePgObjects.aditOrderLines.size();i++)
			{
				RunDatesListAdit.add(getOrderPubDatesOrderLine(i+1));
			}
			actions.takeScreenshot(driver);
			log.info("Able to capture all the Adit RunDates Value for SDTObit");
		} catch (Exception e) {
			log.error("Unable to capture all the Adit RunDates Value for SDTObit");
			scenario.write("Unable to capture all the Adit RunDates Value for SDTObit");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Error in capturing all the Adit RunDates Value for SDTObit");
			scenario.write("Error in capturing all the Adit RunDates Value for SDTObit");
			throw e;
		}
	}

	@Then("^check for SDTObit, if the ADSS RunDates match with the Adit RunDates$")
	public void check_for_SDTObit_if_the_ADSS_RunDates_match_with_the_Adit_RunDates() throws Throwable {
		try {
			Assert.assertTrue(RunDatesList.size()>0);
			Assert.assertTrue(RunDatesListAdit.size()>0);
			Assert.assertEquals(RunDatesList.get(0).get(0),RunDatesListAdit.get(0));
			Assert.assertEquals(RunDatesList.get(1).get(0),RunDatesListAdit.get(1));
			Assert.assertEquals(RunDatesList.get(2).get(0),RunDatesListAdit.get(2));
			Assert.assertEquals(RunDatesList.get(3).get(0),RunDatesListAdit.get(3));
			actions.takeScreenshot(driver);
			log.info("For SDTObit, the ADSS RunDates match with the Adit RunDates");
		} catch (Exception e) {
			log.error("Unable to check for SDTObit, if the ADSS RunDates match with the Adit RunDates");
			scenario.write("Unable to check for SDTObit, if the ADSS RunDates match with the Adit RunDates");
			throw e;
		}
		catch (AssertionError e) {
			log.error("For SDTObit, the ADSS RunDates does not match with the Adit RunDates");
			scenario.write("For SDTObit, the ADSS RunDates does not match with the Adit RunDates");
			throw e;
		}
	}

	public String getOrderLinePriceAditforADSSLOC(int index, WebDriver driver) {

		String aditOrderPrice;
		double price=0;
		for(int i=0;i<index;i++)
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", adssRegressionSuitePgObjects.orderLineExpandButton.get(i));
			adssRegressionSuitePgObjects.orderLineExpandButton.get(i).click();

			if(adssRegressionSuitePgObjects.orderLineAgencyCommission.get(i).getAttribute("value").equals("15.00"))
			{
				double expectedCommission=Utils.getPriceFormattedValue(Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineSalesPrice.get(i).getAttribute("value")))*0.15);
				double actualcommission=Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineAgencyCommissionAmount.get(i).getAttribute("value")));

				if(expectedCommission==actualcommission)
				{
					price=price+Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineSalesPrice.get(i).getAttribute("value")))
					+Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineAddOn.get(i).getAttribute("value")))
					+Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineCharges.get(i).getAttribute("value")));
					price=price-Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineAgencyCommissionAmount.get(i).getAttribute("value")));
				}
			}
			adssRegressionSuitePgObjects.orderLineExpandButton.get(i).click();
		}
		aditOrderPrice="$"+String.valueOf(Utils.getPriceFormattedValue(price));
		return aditOrderPrice;

	}


	@Then("^check if the ADSS Adit Order Price is same with LOC Commission$")
	public void check_if_the_ADSS_Adit_Order_Price_Validation_is_same_with_LOC_Commission() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.aditOrderLines.size()>0);
			TotalOrderPriceAdit=getOrderPrice();
			String TotalOrderLinePriceAditWithCommission=getOrderLinePriceAditforADSSLOC(adssRegressionSuitePgObjects.aditOrderLines.size(), driver);
			Assert.assertEquals(TotalOrderLinePriceAditWithCommission,TotalOrderPriceAdit);
			actions.takeScreenshot(driver);
			log.info("The ADSS Adit Order Price is the same with LOC Commission");
		} catch (Exception e) {
			log.error("Unable to check if the ADSS Adit Order Price is same with LOC Commission");
			scenario.write("Unable to check if the ADSS Adit Order Price is same with LOC Commission");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS Adit Order Price is not the same with LOC Commission");
			scenario.write("The ADSS Adit Order Price is not the same with LOC Commission");
			throw e;
		}
	}

	public String getOrderLinePrice(int index) {

		String aditOrderPrice;
		double price=0;
		for(int i=0;i<index;i++)
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", adssRegressionSuitePgObjects.orderLineExpandButton.get(i));
			adssRegressionSuitePgObjects.orderLineExpandButton.get(i).click();

			price=price+Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineSalesPrice.get(i).getAttribute("value")))
			+Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineAddOn.get(i).getAttribute("value")))
			+Double.parseDouble(Utils.getPriceValue(adssRegressionSuitePgObjects.orderLineCharges.get(i).getAttribute("value")));

			adssRegressionSuitePgObjects.orderLineExpandButton.get(i).click();
		}

		aditOrderPrice="$"+String.valueOf(price);
		return aditOrderPrice;

	}


	@Then("^check if the ADSS Adit Order Price is same without LOC Commission$")
	public void check_if_the_ADSS_Adit_Order_Price_is_same_without_LOC_Commission() throws Throwable {
		try {
			String TotalOrderLinePriceAditWithoutCommssion=getOrderLinePrice(adssRegressionSuitePgObjects.aditOrderLines.size());
			String TotalPriceADSS=totalPrice;
			Assert.assertEquals(TotalOrderLinePriceAditWithoutCommssion,TotalPriceADSS);
			actions.takeScreenshot(driver);
			log.info("The ADSS Adit Order Price is the same without LOC Commission");
		} catch (Exception e) {
			log.error("Unable to check if the ADSS Adit Order Price is same without LOC Commission");
			scenario.write("Unable to check if the ADSS Adit Order Price is same without LOC Commission");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS Adit Order Price is not the same without LOC Commission");
			scenario.write("The ADSS Adit Order Price is not the same without LOC Commission");
			throw e;
		}
	}

	@Then("^change the order to processed, if price is less than one dollar for SDTObit, else reject$")
	public void change_the_order_to_processed_if_price_is_less_than_one_dollar_for_SDTObit_else_reject() throws Throwable {
		boolean ApprovalStatus=Utils.checkIsApprove(Double.parseDouble(Utils.getPriceValue(TotalOrderPriceAdit)));
		if(ApprovalStatus)
		{
			clickApproveButton();
			clickConfirmApproveButton();
			driver.navigate().back();
			String orderStatus=checkOrderStatus();
			Assert.assertEquals(orderStatus,"Processed");
		}		
		else
		{
			clickRejectButton();
			clickConfirmRejectButton();
			driver.navigate().back();
			String orderStatus=checkOrderStatus();
			Assert.assertEquals(orderStatus,"Rejected");
		}
	}

	@Then("^click on Forgot Passsword link in the new login window$")
	public void click_on_Forgot_Passsword_link_in_the_new_login_window() throws Throwable {
		try {
			try {Thread.sleep(5000);}catch(Exception e) {}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.forgetPasswordLink);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.forgetPasswordLink);
			action.moveToElement(adssRegressionSuitePgObjects.forgetPasswordLink).click().build().perform();
			try {Thread.sleep(5000);}catch(Exception e) {}
			actions.takeScreenshot(driver);
			log.info("Able to click on Forgot Passsword link in the new login window");
		} catch (Exception e) {
			log.error("Unable to click on Forgot Passsword link in the new login window");
			scenario.write("Unable to click on Forgot Passsword link in the new login window");
			throw e;
		}
	}

	@Then("^enter the registered user email in the forgot password window$")
	public void enter_the_registered_user_email_in_the_forgot_password_window() throws Throwable {
		try {
			try {Thread.sleep(2000);}catch(Exception e) {}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.emailBoxForgotPassword);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.emailBoxForgotPassword);
			actions.type(adssRegressionSuitePgObjects.emailBoxForgotPassword, data.get("gmailId"));
			actions.takeScreenshot(driver);
			log.info("Able to enter the registered user email in the forgot password window");
		} catch (Exception e) {
			log.error("Unable to enter the registered user email in the forgot password window");
			scenario.write("Unable to enter the registered user email n the forgot password window");
			throw e;
		}
	}

	@Then("^click on the Send Link link button the forgot password window$")
	public void click_on_the_Send_Link_link_button_the_forgot_password_window() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.sendLinkButton);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.sendLinkButton);
			actions.click(adssRegressionSuitePgObjects.sendLinkButton);
			actions.takeScreenshot(driver);
			try {Thread.sleep(2000);
			}catch(Exception e){}
			log.info("Able to click on the Send Link link button the forgot password window");
		} catch (Exception e) {
			log.error("Unable to click on the Send Link link button the forgot password window");
			scenario.write("Unable to click on the Send Link link button the forgot password window");
			throw e;
		}
	}

	@Then("^check if the Reset Link is sent$")
	public void check_if_the_Reset_Link_is_sent() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.resetLinkSentConfirmation);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.resetLinkSentConfirmation);
			actions.takeScreenshot(driver);
			log.info("Able to check if the Reset Link is sent");
		} catch (Exception e) {
			log.error("Unable to check if the Reset Link is sent");
			scenario.write("Unable to check if the Reset Link is sent");
			throw e;
		}
	}

	@Then("^close the Rest Sent Confirmation popup$")
	public void close_the_Rest_Sent_Confirmation_popup() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.closeRestSentConfirmationPopupbutton);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.closeRestSentConfirmationPopupbutton);
			action.moveToElement(adssRegressionSuitePgObjects.closeRestSentConfirmationPopupbutton).click().build().perform();
			actions.takeScreenshot(driver);
			log.info("Able to close the Rest Sent Confirmation popup");
		} catch (Exception e) {
			log.error("Unable to close the Rest Sent Confirmation popup");
			scenario.write("Unable to close the Rest Sent Confirmation popup");
			throw e;
		}
	}

	@Then("^open a new tab and open the Gmail URL$")
	public void open_a_new_tab_and_open_the_Gmail_URL() throws Throwable {
		try {
			JavascriptExecutor je1 = (JavascriptExecutor) driver;
			je1.executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get(data.getProperty("GMAILURL"));
			waitForPageToLoad();
			actions.takeScreenshot(driver);
			log.info("Able to open a new tab and open the Gmail URL");
		} catch (Exception e) {
			log.error("Unable to open a new tab and open the Gmail URL");
			scenario.write("Unable to open a new tab and open the Gmail URL");
			throw e;
		}
	}

	@Then("^set the email id as the user email id$")
	public void set_the_email_id_as_the_user_email_id() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.emailField);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.emailField);
			actions.type(adssRegressionSuitePgObjects.emailField, data.getProperty("gmailId"));
			actions.takeScreenshot(driver);
			log.info("Able to set the email id as the user email id");
		} catch (Exception e) {
			log.error("Unable to set the email id as the user email id");
			scenario.write("Unable to set the email id as the user email id");
			throw e;
		}
	}

	@Then("^click on Next$")
	public void click_on_Next() throws Throwable {
		try {
			try {
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.nextButtonId);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.nextButtonId);
				actions.click(adssRegressionSuitePgObjects.nextButtonId);
			}catch(Exception e)
			{
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.nextButtonPassword);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.nextButtonPassword);
				actions.click(adssRegressionSuitePgObjects.nextButtonPassword);
			}
			actions.takeScreenshot(driver);
			log.info("Able to click on Next");
		} catch (Exception e) {
			log.error("Unable to click on Next");
			scenario.write("Unable to click on Next");
			throw e;
		}
	}

	@Then("^set the email password as the user email password$")
	public void set_the_email_password_as_the_user_email_password() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.passwordField);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.passwordField);
			Thread.sleep(5000);
			actions.type(adssRegressionSuitePgObjects.passwordField, data.getProperty("gmailPassword"));
			actions.takeScreenshot(driver);
			log.info("Able to set the email password as the user email password");
		} catch (Exception e) {
			log.error("Unable to set the email password as the user email password");
			scenario.write("Unable to set the email password as the user email password");
			throw e;
		}
	}

	@Then("^check if gmail login is successful$")
	public void check_if_gmail_login_is_successful() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inbox);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inbox);
			actions.takeScreenshot(driver);
			log.info("Gmail Login is successful");
		} catch (Exception e) {
			log.error("Gmail Login is not successful");
			scenario.write("Gmail Login is not successful");
			throw e;
		}
	}

	@Then("^open the Reset Email$")
	public void open_the_Reset_Email() throws Throwable {
		try {
			try {
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.resetPasswordEmail1);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.resetPasswordEmail1);
				actions.click(adssRegressionSuitePgObjects.resetPasswordEmail1);
			}catch(Exception e)
			{
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.resetPasswordEmail2);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.resetPasswordEmail2);
				actions.click(adssRegressionSuitePgObjects.resetPasswordEmail2);
			}
			actions.takeScreenshot(driver);
			log.info("Able to open the Reset Email");
		} catch (Exception e) {
			log.error("Unable to open the Reset Email");
			scenario.write("Unable to open the Reset Email");
			throw e;
		}
	}

	@Then("^click on the Reset Link$")
	public void click_on_the_Reset_Link() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.resetPasswordLink);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.resetPasswordLink);
			actions.click(adssRegressionSuitePgObjects.resetPasswordLink);
			swicth_to_the_tab(3);
			try {
				waitForPageToLoad();
			} catch (WebDriverException e) {
				// do nothing
			}
			/*try {
				Thread.sleep(10000);
				adssRegressionSuitePgObjects.resetPasswordEmail1.click();
			}catch(Exception e)
			{
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.resetPasswordEmail2);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.resetPasswordEmail2);
				actions.click(adssRegressionSuitePgObjects.resetPasswordEmail2);
			}*/
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.resetPasswordButton);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Reset Link");
		} catch (Exception e) {
			log.error("Unable to click on the Reset Link");
			scenario.write("Unable to click on the Reset Link");
			throw e;
		}
	}

	@Then("^upload photo having dimension of \"([^\"]*)\"$")
	public void upload_photo_having_dimension_of(String photoDimensions) throws Throwable {
		try {
			adssRegressionSuitePgObjects.uploadLink.sendKeys(System.getProperty("user.dir") + "\\Images\\image_"+photoDimensions + ".jpg");
			actions.takeScreenshot(driver);
			log.info("Able to upload photo having dimensions of " + photoDimensions);
		} catch (Exception e) {
			log.error("Unable to upload photo having dimensions of " + photoDimensions);
			scenario.write("Unable to upload photo having dimensions of " + photoDimensions);
			throw e;
		}
	}

	@Then("^check if the Material Error message is displayed$")
	public void check_if_the_Material_Error_message_is_displayed() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.materialError);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.materialError);
			actions.takeScreenshot(driver);
			log.info("The Material Error message is displayed");
		} catch (Exception e) {
			log.error("The Material Error message is not displayed");
			scenario.write("The Material Error message is not displayed");
			throw e;
		}
	}

	@Then("^check if the order config error is shown$")
	public void check_if_the_order_config_error_is_shown() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.configError);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.configError);
			actions.takeScreenshot(driver);
			log.info("The order config error is shown");
		} catch (Exception e) {
			log.error("The order config error is not shown");
			scenario.write("The order config error is not shown");
			throw e;
		}
	}

	@Then("^fill in the text LATCAL$")
	public void fill_in_the_text_LATCAL() throws Throwable {
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {

			}
			Utils.fillText(adssRegressionSuitePgObjects.HeadLine, Utils.generateRandomString(10));
			Utils.fillText(adssRegressionSuitePgObjects.BodyCopy2, Utils.generateRandomString(13));
			Utils.fillText(adssRegressionSuitePgObjects.BodyCopy, Utils.generateRandomString(10));
			Utils.fillText(adssRegressionSuitePgObjects.CompanyName, Utils.generateRandomString(7));
			Utils.fillText(adssRegressionSuitePgObjects.Address, "123 Park Avenue");
			Utils.fillText(adssRegressionSuitePgObjects.PhoneNumberInDesignYourMaterialPg, "9903463256");
			Utils.fillText(adssRegressionSuitePgObjects.WebAddress, "https://www."+Utils.generateRandomString(7)+".com");

			adssRegressionSuitePgObjects.WebAddress.sendKeys(Keys.TAB);
			actions.takeScreenshot(driver);
			log.info("Able to fill in the text LATCAL");
		} catch (Exception e) {
			log.error("Unable to fill in the text LATCAL");
			scenario.write("Unable to fill in the text LATCAL");
			throw e;
		}
	}

	@Then("^note the material preview source link$")
	public void note_the_material_preview_source_link() throws Throwable {
		try {
			try {Thread.sleep(2000);}catch(Exception e){}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.materialPreviewImage);
			String sourceLink = adssRegressionSuitePgObjects.materialPreviewImage.getAttribute("src").toString().toLowerCase();
			designAdMaterialPreviewSourceLink = sourceLink.substring(0, sourceLink.indexOf("&"));
			actions.takeScreenshot(driver);
			log.info("Able to note the material preview source link");
		} catch (Exception e) {
			log.error("Unable to note the material preview source link");
			scenario.write("Unable to note the material preview source link");
			throw e;
		}
	}

	@Then("^check if the content source link is the same as the design material preview source link$")
	public void check_if_the_content_source_link_is_the_same_as_the_design_material_preview_source_link() throws Throwable {
		try {
			try {Thread.sleep(2000);}catch(Exception e){}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.materialPreviewImageAfterConfiguringImg);
			String sourceLink = adssRegressionSuitePgObjects.materialPreviewImageAfterConfiguringImg.getAttribute("src").toString().toLowerCase();
			String designAdMaterialSourceLink = sourceLink.substring(0, sourceLink.indexOf("&"));
			Assert.assertEquals(designAdMaterialPreviewSourceLink, designAdMaterialSourceLink);
			actions.takeScreenshot(driver);
			log.info("The content source link is the same as the design material preview source link");
		} catch (Exception e) {
			log.error("Unable to check if the content source link is the same as the design material preview source link");
			scenario.write("Unable to check if the content source link is the same as the design material preview source link");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The content source link is not the same as the design material preview source link");
			scenario.write("The content source link is not the same as the design material preview source link");
			throw e;
		}
	}

	@Then("^click on the edit icon$")
	public void click_on_the_edit_icon() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.editMaterial);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.editMaterial);
			actions.click(adssRegressionSuitePgObjects.editMaterial);
			actions.takeScreenshot(driver);
			log.info("Able to click on the edit icon");
		} catch (Exception e) {
			log.error("Unable to click on the edit icon");
			scenario.write("Unable to click on the edit icon");
			throw e;
		}
	}

	@Then("^select multiple template$")
	public void select_multiple_template() throws Throwable {
		try {
			Assert.assertTrue(adssRegressionSuitePgObjects.availableTemplates.size()>0);
			for(int i=4;i>=0;i--)
			{
				try {Thread.sleep(1000);}catch(Exception e){}
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.availableTemplates.get(i));
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.availableTemplates.get(i));
				actions.click(adssRegressionSuitePgObjects.availableTemplates.get(i));
			}
			try {Thread.sleep(2000);}catch(Exception e){}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.availableTemplates.get(0));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.availableTemplates.get(0));
			actions.click(adssRegressionSuitePgObjects.availableTemplates.get(0));
			actions.takeScreenshot(driver);
			log.info("Able to select multiple templates");
		} catch (Exception e) {
			log.error("Unable to select multiple templates");
			scenario.write("Unable to select multiple templates");
			throw e;
		}
		catch (AssertionError e) {
			log.error("No templates are present");
			scenario.write("No templates are present");
			throw e;
		}
	}

	@Then("^upload a photo of type TIFF$")
	public void upload_a_photo_of_type_TIFF() throws Throwable {
		try {
			adssRegressionSuitePgObjects.uploadLink.sendKeys(System.getProperty("user.dir") + "\\Images\\Image_TIFF.TIF");
			actions.takeScreenshot(driver);
			log.info("Able to upload a photo of type TIFF");
		} catch (Exception e) {
			log.error("Unable to upload a photo of type TIFF");
			scenario.write("Unable to upload a photo of type TIFF");
			throw e;
		}
	}

	@Then("^check if the invalid coupon code error message is displayed$")
	public void check_if_the_invalid_coupon_code_error_message_is_displayed() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.invalidCouponErrorMessage);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.invalidCouponErrorMessage);
			actions.takeScreenshot(driver);
			log.info("The invalid coupon code error message is displayed");
		} catch (Exception e) {
			log.error("The invalid coupon code error message is not displayed");
			scenario.write("The invalid coupon code error message is not displayed");
			throw e;
		}
	}

	@When("^click on the \"([^\"]*)\" button in My Dashboard$")
	public void click_on_the_button_in_My_Dashboard(String myDashboardBtn) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.myDashboardBtn(myDashboardBtn));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.myDashboardBtn(myDashboardBtn));
			actions.click(adssRegressionSuitePgObjects.myDashboardBtn(myDashboardBtn));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + myDashboardBtn + " button in My Dashboard");
		} catch (Exception e) {
			log.error("Unable to click on the " + myDashboardBtn + " button in My Dashboard");
			scenario.write("Unable to click on the " + myDashboardBtn + " button in My Dashboard");
			throw e;
		}
	}

	@Then("^change the first name as \"([^\"]*)\"$")
	public void change_the_first_name_as(String firstName) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.firstName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.firstName);
			adssRegressionSuitePgObjects.firstName.clear();
			actions.type(adssRegressionSuitePgObjects.firstName, firstName);
			actions.takeScreenshot(driver);
			log.info("Able to change the first name as " + firstName);
		} catch (Exception e) {
			log.error("Unable to change the first name as " + firstName);
			scenario.write("Unable to change the first name as " + firstName);
			throw e;
		}
	}

	@Then("^change the last name as \"([^\"]*)\"$")
	public void change_the_last_name_as(String lastName) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.lastName);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.lastName);
			adssRegressionSuitePgObjects.lastName.clear();
			actions.type(adssRegressionSuitePgObjects.lastName, lastName);
			actions.takeScreenshot(driver);
			log.info("Able to change the last name as " + lastName);
		} catch (Exception e) {
			log.error("Unable to change the last name as " + lastName);
			scenario.write("Unable to change the last name as " + lastName);
			throw e;
		}
	}

	@Then("^change the phone number as \"([^\"]*)\"$")
	public void change_the_phone_number_as(String phoneNr) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.contactPhoneNumber);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.contactPhoneNumber);
			adssRegressionSuitePgObjects.contactPhoneNumber.clear();
			actions.type(adssRegressionSuitePgObjects.contactPhoneNumber, phoneNr);
			actions.takeScreenshot(driver);
			log.info("Able to change the phhone number as " + phoneNr);
		} catch (Exception e) {
			log.error("Unable to change the phhone number as " + phoneNr);
			scenario.write("Unable to change the phhone number as " + phoneNr);
			throw e;
		}
	}

	@Then("^change the street adress as \"([^\"]*)\"$")
	public void change_the_street_adress_as(String streetAdress) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.streetAddress);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.streetAddress);
			adssRegressionSuitePgObjects.streetAddress.clear();
			actions.type(adssRegressionSuitePgObjects.streetAddress, streetAdress);
			actions.takeScreenshot(driver);
			log.info("Able to change the street adress as " + streetAdress);
		} catch (Exception e) {
			log.error("Unable to change the street adress as " + streetAdress);
			scenario.write("Unabel to change the street adress as " + streetAdress);
			throw e;
		}
	}

	@Then("^change the city as \"([^\"]*)\"$")
	public void change_the_city_as(String city) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.city);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.city);
			adssRegressionSuitePgObjects.city.clear();
			actions.type(adssRegressionSuitePgObjects.city, city);
			actions.takeScreenshot(driver);
			log.info("Able to change the city as " + city);
		} catch (Exception e) {
			log.error("Unable to change the city as " + city);
			scenario.write("Unable to change the city as " + city);
			throw e;
		}
	}

	@Then("^change the state as \"([^\"]*)\"$")
	public void change_the_state_as(String state) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.state);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.state);
			adssRegressionSuitePgObjects.state.click();
			try {Thread.sleep(5000);}catch(Exception e) {}
			WebElement ele=driver.findElement(By.xpath("//*[text()='"+state+"']"));
			action.moveToElement(ele).click().build().perform();
			actions.takeScreenshot(driver);
			log.info("Able to change the state as " + state);
		} catch (Exception e) {
			log.error("Unable to change the state as " + state);
			scenario.write("Unable to change the state as " + state);
			throw e;
		}
	}

	@Then("^clear the zipcode$")
	public void clear_the_zipcode() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.zipCode);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.zipCode);
			adssRegressionSuitePgObjects.zipCode.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			adssRegressionSuitePgObjects.zipCode.sendKeys(Keys.BACK_SPACE);
			actions.takeScreenshot(driver);
			log.info("Able to clear the zipcode");
		} catch (Exception e) {
			log.error("Unable to clear the zipcode");
			scenario.write("Unable to clear the zipcode");
			throw e;
		}
	}

	@Then("^check if the error message is displayed after editing in the my account section$")
	public void check_if_the_error_message_is_displayed_after_editing_in_the_my_account_section() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.errorMessage);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.errorMessage);
			actions.takeScreenshot(driver);
			log.info("The error message is displayed after editing in the my account section");
		} catch (Exception e) {
			log.error("The error message is not displayed after editing in the my account section");
			scenario.write("The error message is not displayed after editing in the my account section");
			throw e;
		}
	}

	@Then("^click on the Change Password link$")
	public void click_on_the_Change_Password_link() throws Throwable {
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {

			}
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.changePasswordButton);
			action.moveToElement(adssRegressionSuitePgObjects.changePasswordButton).click().build().perform();
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.changePasswordSaveButton);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Change Password link");
		} catch (Exception e) {
			log.error("Unable to click on the Change Password link");
			scenario.write("Unable to click on the Change Password link");
			throw e;
		}
	}

	@Then("^type the old \"([^\"]*)\" in the password field$")
	public void type_the_old_in_the_password_field(String oldPwd) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.OldPasswordField);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.OldPasswordField);
			actions.click(adssRegressionSuitePgObjects.OldPasswordField);
			adssRegressionSuitePgObjects.OldPasswordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			adssRegressionSuitePgObjects.OldPasswordField.sendKeys(Keys.BACK_SPACE);
			String environment=System.getProperty("environment");
			String dataToEnter = null;
			String dataToType = null;
			if(environment==null){
				dataToEnter = oldPwd + "QA";
				dataToType = data.getProperty(dataToEnter);
			}
			else if(environment.trim().equalsIgnoreCase("QA") || environment.trim().equalsIgnoreCase("STAGE")){
				if(oldPwd.trim().equalsIgnoreCase("LocPassword")){
					dataToType = System.getProperty("ADSSLOCPASSWORD");
					if(dataToType==null){
						dataToType = data.getProperty(dataToEnter);
					}
				}
				else if (oldPwd.trim().equalsIgnoreCase("NonLocPassword")) {
					dataToType = System.getProperty("ADSSNONLOCPASSWORD");
					if(dataToType==null){
						dataToType = data.getProperty(dataToEnter);
					}
				}
			}
			else if (environment.trim().equalsIgnoreCase("PROD")){
				if(oldPwd.trim().equalsIgnoreCase("LocPassword")){
					dataToType = System.getProperty("ADSSLOCPASSWORD");
				}
				else if (oldPwd.trim().equalsIgnoreCase("NonLocPassword")) {
					dataToType = System.getProperty("ADSSNONLOCPASSWORD");
				}
			}
			else {
				Assert.fail("Correct environment is not provided");
			}
			Assert.assertNotEquals(null, dataToType);
			actions.type(adssRegressionSuitePgObjects.OldPasswordField, dataToType);
			actions.takeScreenshot(driver);
			log.info("Able to type the old password " + data.getProperty(oldPwd) + " in the password field");
		} catch (Exception e) {
			log.error("Unable to type the old password " + data.getProperty(oldPwd) + " in the password field");
			scenario.write("Unable to type the old password " + data.getProperty(oldPwd) + " in the password field");
			throw e;
		}
	}

	@Then("^set the new password as \"([^\"]*)\"$")
	public void set_the_new_password_as(String newPwd) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.NewPasswordField);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.NewPasswordField);
			adssRegressionSuitePgObjects.NewPasswordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			adssRegressionSuitePgObjects.NewPasswordField.sendKeys(Keys.BACK_SPACE);
			actions.type(adssRegressionSuitePgObjects.NewPasswordField, newPwd);
		} catch (Exception e) {
			log.error("Unable to set the new password as " + newPwd);
			scenario.write("Unable to set the new password as " + newPwd);
			throw e;
		}
	}

	@Then("^confirm the password as \"([^\"]*)\"$")
	public void confirm_the_password_as(String newPwd) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.ConfirmPasswordField);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.ConfirmPasswordField);
			adssRegressionSuitePgObjects.ConfirmPasswordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			adssRegressionSuitePgObjects.ConfirmPasswordField.sendKeys(Keys.BACK_SPACE);
			actions.type(adssRegressionSuitePgObjects.ConfirmPasswordField, newPwd);
			actions.takeScreenshot(driver);
			log.info("able to confirm the password as " + newPwd);
		} catch (Exception e) {
			log.error("Unable to confirm the password as " + newPwd);
			scenario.write("Unable to confirm the password as " + newPwd);
			throw e;
		}
	}

	@Then("^check if appropriate error message is displayed$")
	public void check_if_appropriate_error_message_is_displayed() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.errorMessage);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.errorMessage);
			actions.takeScreenshot(driver);
			log.info("Appropriate error message is displayed");
		} catch (Exception e) {
			log.error("Appropriate error message is not displayed");
			scenario.write("Appropriate error message is not displayed");
			throw e;
		}
	}

	@Then("^set the Full Run Zone$")
	public void set_the_Full_Run_Zone() throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.zoneFullRun);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.zoneFullRun);
			actions.click(adssRegressionSuitePgObjects.zoneFullRun);
			actions.takeScreenshot(driver);
			log.info("Able to set the Full Run Zone");
		} catch (Exception e) {
			log.error("Unable to set the Full Run Zone");
			scenario.write("Unable to set the Full Run Zone");
			throw e;
		}
	}

	@Then("^capture the Rundates Value for SSCST$")
	public void capture_the_Rundates_Value_for_SSCST() throws Throwable {
		try {
			capture_the_Rundates_Value_for_LAT();
			actions.takeScreenshot(driver);
			log.info("Able to capture the Rundates Value for SSCST");
		} catch (Exception e) {
			log.error("Error in capturing the Rundates Value for SSCST");
			scenario.write("Error in capturing the Rundates Value for SSCST");
			throw e;
		}
		catch (AssertionError e) {
			log.error("Unable to capture the Rundates Value for SSCST");
			scenario.write("Unable to capture the Rundates Value for SSCST");
			throw e;
		}
	}

	@Then("^check if ADSS and ADIT rundates are same for SSCST$")
	public void check_if_ADSS_and_ADIT_rundates_are_same_for_SSCST() throws Throwable {
		try {
			check_if_ADSS_and_ADIT_rundates_are_same_for_LAT();
			actions.takeScreenshot(driver);
			log.info("The ADSS and ADIT rundates are the same for SSCST");
		} catch (Exception e) {
			log.error("Unable to check if ADSS and ADIT rundates are same for SSCST");
			scenario.write("Unable to check if ADSS and ADIT rundates are same for SSCST");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and ADIT rundates are not the same for SSCST");
			scenario.write("The ADSS and ADIT rundates are not the same for SSCST");
			throw e;
		}
	}

	@Then("^check if the ADSS and Adit orderlines price are same for SSCST$")
	public void check_if_the_ADSS_and_Adit_orderlines_price_are_same_for_SSCST() throws Throwable {
		try {
			check_if_the_ADSS_and_Adit_orderlines_price_are_same_for_LAT();
			actions.takeScreenshot(driver);
			log.info("The ADSS and Adit orderlines price are the same for SSCST");
		} catch (Exception e) {
			log.error("Unable to check if the ADSS and Adit orderlines price are same for SSCST");
			scenario.write("Unable to check if the ADSS and Adit orderlines price are same for SSCST");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and Adit orderlines price are not the same for SSCST");
			scenario.write("The ADSS and Adit orderlines price are not the same for SSCST");
			throw e;
		}
	}

	@Then("^check if ADSS and ADIT total prices are same for SSCST$")
	public void check_if_ADSS_and_ADIT_total_prices_are_same_for_SSCST() throws Throwable {
		try {
			check_if_ADSS_and_ADIT_total_prices_are_same_for_Non_Loc_users_for_LAT();
			actions.takeScreenshot(driver);
			log.info("The ADSS and ADIT total prices are same for Loc users for SSCST");
		} catch (Exception e) {
			log.error("Unable to check if ADSS and ADIT total prices are same for Loc users for SSCST");
			scenario.write("Unable to check if ADSS and ADIT total prices are same for Loc users for SSCST");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and ADIT total prices are not the same for Loc users for SSCST");
			scenario.write("The ADSS and ADIT total prices are not the same for Loc users for SSCST");
			throw e;
		}
	}

	@Then("^check if the ADSS and Adit total price are same for LATCAL No CC Purchase Test$")
	public void check_if_the_ADSS_and_Adit_total_price_are_same_for_LATCAL_No_CC_Purchase_Test() throws Throwable {
		try {
			String TotalPriceAdit="$"+String.valueOf(Double.parseDouble(Utils.getPriceValue(getOrderPrice())));
			String TotalPriceADSS="$"+String.valueOf(Utils.getLOCprice(Double.parseDouble(Utils.getPriceValue(OrderPrice.get(0)))));
			Assert.assertEquals(TotalPriceAdit,TotalPriceADSS);
			actions.takeScreenshot(driver);
			log.info("The ADSS and Adit total price are not the same for LATCAL No CC Purchase Test");
		} catch (Exception e) {
			log.error("Unable to check if the ADSS and Adit total price are same for LATCAL No CC Purchase Test");
			scenario.write("Unable to check if the ADSS and Adit total price are same for LATCAL No CC Purchase Test");
			throw e;
		}
		catch (AssertionError e) {
			log.error("The ADSS and Adit total price are not the same for LATCAL No CC Purchase Test");
			scenario.write("The ADSS and Adit total price are not the same for LATCAL No CC Purchase Test");
			throw e;
		}
	}

	@Then("^click on only the Save and Continue button$")
	public void click_on_only_the_Save_and_Continue_button() throws Throwable {
		try {
			Thread.sleep(5000);
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.SaveAndContinueButton);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.SaveAndContinueButton);
			actions.javascriptClk(driver, adssRegressionSuitePgObjects.SaveAndContinueButton);
			actions.takeScreenshot(driver);
			log.info("Able to click on only the Save and Continue button");
		} catch (Exception e) {
			log.error("Unable to click on only the Save and Continue button");
			scenario.write("Unable to click on only the Save and Continue button");
			throw e;
		}
	}

	@Then("^set Pet Type dropdown as \"([^\"]*)\"$")
	public void set_Pet_Type_dropdown_as(String petTypeDropdown) throws Throwable {
		try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.petTypeDropdown);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.petTypeDropdown);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.petTypeDropdown);
			actions.selectElementFromDropdownByVisibleText(driver, adssRegressionSuitePgObjects.petTypeDropdown, petTypeDropdown);
			actions.takeScreenshot(driver);
			log.info("Able to set Pet Type dropdown as " + petTypeDropdown);
		} catch (Exception e) {
			log.error("Unable to set Pet Type dropdown as " + petTypeDropdown);
			scenario.write("Unable to set Pet Type dropdown as " + petTypeDropdown);
			throw e;
		}
	}
	
	@Then("^check if the button \"([^\"]*)\" is present just below the Review Order Section heading$")
	public void check_if_the_button_is_present_just_below_the_Review_Order_Section_heading(String btnName) throws Throwable {
	    try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.headingBelowReviewOrderSectionHeading(btnName));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.headingBelowReviewOrderSectionHeading(btnName));
			actions.takeScreenshot(driver);
			log.info("The buttonn " + btnName + " is present just below the Review Order Section heading");
		} catch (Exception e) {
			log.error("The buttonn " + btnName + " is not present just below the Review Order Section heading");
			scenario.write("The buttonn " + btnName + " is not present just below the Review Order Section heading");
			throw e;
		}
	}
	
	@Then("^check if the register window opens$")
	public void check_if_the_register_window_opens() throws Throwable {
	    try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.registerWindow);
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.registerWindow);
			actions.takeScreenshot(driver);
			log.info("The register window opens");
		} catch (Exception e) {
			log.error("The register window does not open");
			scenario.write("The register window does not open");
			throw e;
		}
	}


	@Then("^enter an email in the Email Address field in the register window$")
	public void enter_an_email_in_the_Email_Address_field_in_the_register_window() throws Throwable {
	    try {
			String emailId = "test" + System.currentTimeMillis() + "@gmail.com";
			String inputPlaceholder = "Email Address";
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).click();
			actions.type(adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder), emailId.trim());
			Assert.assertEquals(emailId.trim(), adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).getAttribute("value").trim());
			actions.takeScreenshot(driver);
			log.info("Able to enter an email in the Email Address field in the register window");
		} catch (Exception e) {
			log.error("Problem in entering an email in the Email Address field in the register window");
			scenario.write("Problem in entering an email in the Email Address field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
			log.error("Unable to enter an email in the Email Address field in the register window");
			scenario.write("Unable to enter an email in the Email Address field in the register window");
			throw e;
		}
	}

	@Then("^enter a password not containing any special characters or capital letters in the Create Your Password field in the register window$")
	public void enter_a_password_not_containing_any_special_characters_or_capital_letters_in_the_Create_Your_Password_field_in_the_register_window() throws Throwable {
	    try {
			enter_a_password_in_the_Create_Your_Password_field_in_the_register_window();
			actions.takeScreenshot(driver);
			log.info("Able to enter a password not containing any special characters or capital letters in the Create Your Password field in the register window");
		} catch (Exception e) {
			log.error("Problem in entering a password not containing any special characters or capital letters in the Create Your Password field in the register window");
			scenario.write("Problem in entering a password not containing any special characters or capital letters in the Create Your Password field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to enter a password not containing any special characters or capital letters in the Create Your Password field in the register window");
	    	scenario.write("Unable to enter a password not containing any special characters or capital letters in the Create Your Password field in the register window");
	    	throw e;
		}
	}

	@Then("^enter a username in the Create Your Username field in the register window$")
	public void enter_a_username_in_the_Create_Your_Username_field_in_the_register_window() throws Throwable {
	    try {
			String userName = "test" + System.currentTimeMillis();
			String inputPlaceholder = "Create Your Username";
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).click();
			actions.type(adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder), userName.trim());
			Assert.assertEquals(userName.trim(), adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).getAttribute("value").trim());
			actions.takeScreenshot(driver);
		} catch (Exception e) {
			log.error("Problem in entering a a username in the Create Your Username field in the register window");
			scenario.write("Problem in entering a a username in the Create Your Username field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
			log.error("Unable to enter a username in the Create Your Username field in the register window");
			scenario.write("Unable to enter a username in the Create Your Username field in the register window");
			throw e;
		}
	}

	@Then("^enter data as \"([^\"]*)\" in the \"([^\"]*)\" field in the register window$")
	public void enter_data_as_in_the_field_in_the_register_window(String dataToEnter, String inputfieldInRegisterWindow) throws Throwable {
	    try {
	    	actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputfieldInRegisterWindow));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputfieldInRegisterWindow));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputfieldInRegisterWindow));
			adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputfieldInRegisterWindow).click();
			actions.type(adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputfieldInRegisterWindow), dataToEnter.trim());
			Assert.assertEquals(dataToEnter.trim(), adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputfieldInRegisterWindow).getAttribute("value").trim());
			actions.takeScreenshot(driver);
		} catch (Exception e) {
			log.error("Problem in entering data as " + dataToEnter + " in the " + inputfieldInRegisterWindow + " field in the register window");
			scenario.write("Problem in entering data as " + dataToEnter + " in the " + inputfieldInRegisterWindow + " field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to enter data as " + dataToEnter + " in the " + inputfieldInRegisterWindow + " field in the register window");
	    	scenario.write("Unable to enter data as " + dataToEnter + " in the " + inputfieldInRegisterWindow + " field in the register window");
	    	throw e;
		}
	}

	@Then("^check if registration is successful, and the dashboard page opens properly$")
	public void check_if_registration_is_successful_and_the_dashboard_page_opens_properly() throws Throwable {
	    try {
	    	String menuOption = "ORDERS";
	    	ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.menuInMydashboardPgList(menuOption));
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 30000);
			Assert.assertEquals(true, executeCommand.execute());
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.menuInMydashboardPg(menuOption));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.menuInMydashboardPg(menuOption));
			actions.takeScreenshot(driver);
			log.info("Registration is successful, and the dashboard page opens properly");
		} catch (Exception e) {
			log.error("Unable to check if the registration is successful, and the Dashboard page opens properly or not");
			scenario.write("Unable to check if the registration is successful, and the Dashboard page opens properly or not");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Registration is not successful, and the Dashboard page does not open properly");
			scenario.write("Registration is not successful, and the Dashboard page does not open properly");
			throw e;
		}
	}
	
	@Then("^enter a password in the Create Your Password field in the register window$")
	public void enter_a_password_in_the_Create_Your_Password_field_in_the_register_window() throws Throwable {
	    try {
	    	String password = "test123";
	    	String inputPlaceholder = "Create Your Password";
	    	actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).click();
			actions.type(adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder), password.trim());
			Assert.assertEquals(password.trim(), adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).getAttribute("value").trim());
			actions.takeScreenshot(driver);
			log.info("Able to enter a password in the Create Your Password field in the register window");
		} catch (Exception e) {
			log.error("Problem in entering a password in the Create Your Password field in the register window");
			scenario.write("Problem in entering a password in the Create Your Password field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to enter a password in the Create Your Password field in the register window");
	    	scenario.write("Unable to enter a password in the Create Your Password field in the register window");
	    	throw e;
		}
	}

	
	@Then("^check the checkbox beside the \"([^\"]*)\" section in the register window$")
	public void check_the_checkbox_beside_the_section_in_the_register_window(String checkboxSectionInregisterWindow) throws Throwable {
	    try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.checkboxInRegisterWindow(checkboxSectionInregisterWindow));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.checkboxInRegisterWindow(checkboxSectionInregisterWindow));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.checkboxInRegisterWindow(checkboxSectionInregisterWindow));
			actions.javascriptClk(driver, adssRegressionSuitePgObjects.checkboxInRegisterWindow(checkboxSectionInregisterWindow));
			Assert.assertEquals(true, adssRegressionSuitePgObjects.checkboxInRegisterWindow(checkboxSectionInregisterWindow).isSelected());
			actions.takeScreenshot(driver);
			log.info("Able to check the checkbox beside the " + checkboxSectionInregisterWindow + " section in the register window");
		} catch (Exception e) {
			log.error("Error in checking the checkbox beside the " + checkboxSectionInregisterWindow + " section in the register window");
			scenario.write("Error in checking the checkbox beside the " + checkboxSectionInregisterWindow + " section in the register window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to check the checkbox beside the " + checkboxSectionInregisterWindow + " section in the register window");
	    	scenario.write("Unable to check the checkbox beside the " + checkboxSectionInregisterWindow + " section in the register window");
	    	throw e;
		}
	}

	@Then("^click on the \"([^\"]*)\" button in the register window$")
	public void click_on_the_button_in_the_register_window(String inputBtnInRegisterWindow) throws Throwable {
	    try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.buttonInRegisterWindow(inputBtnInRegisterWindow));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.buttonInRegisterWindow(inputBtnInRegisterWindow));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.buttonInRegisterWindow(inputBtnInRegisterWindow));
			actions.click(adssRegressionSuitePgObjects.buttonInRegisterWindow(inputBtnInRegisterWindow));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + inputBtnInRegisterWindow + " button in the register window");
		} catch (Exception e) {
			log.error("Unable to click on the " + inputBtnInRegisterWindow + " button in the register window");
			scenario.write("Unable to click on the " + inputBtnInRegisterWindow + " button in the register window");
			throw e;
		}
	}
	
	@Then("^enter a password not containing any capital letters, but containing one or more special characters in the Create Your Password field in the register window$")
	public void enter_a_password_not_containing_any_capital_letters_but_containing_one_or_more_special_characters_in_the_Create_Your_Password_field_in_the_register_window() throws Throwable {
		try {
	    	String password = "test123@@";
	    	String inputPlaceholder = "Create Your Password";
	    	actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).click();
			actions.type(adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder), password.trim());
			Assert.assertEquals(password.trim(), adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).getAttribute("value").trim());
			actions.takeScreenshot(driver);
			log.info("Able to enter a password not containing any capital letters, but containing one or more special characters in the Create Your Password field in the register window");
		} catch (Exception e) {
			log.error("Problem in entering a password not containing any capital letters, but containing one or more special characters in the Create Your Password field in the register window");
			scenario.write("Problem in entering a password not containing any capital letters, but containing one or more special characters in the Create Your Password field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to enter a password not containing any capital letters, but containing one or more special characters in the Create Your Password field in the register window");
	    	scenario.write("Unable to enter a password not containing any capital letters, but containing one or more special characters in the Create Your Password field in the register window");
	    	throw e;
		}
	}
	
	@Then("^enter a password not containing any special characters, but containing one or more capital letters in the Create Your Password field in the register window$")
	public void enter_a_password_not_containing_any_special_characters_but_containing_one_or_more_capital_letters_in_the_Create_Your_Password_field_in_the_register_window() throws Throwable {
		try {
	    	String password = "TTest123";
	    	String inputPlaceholder = "Create Your Password";
	    	actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).click();
			actions.type(adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder), password.trim());
			Assert.assertEquals(password.trim(), adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).getAttribute("value").trim());
			actions.takeScreenshot(driver);
			log.info("Able to enter a password not containing any special characters, but containing one or more capital letters in the Create Your Password field in the register window");
		} catch (Exception e) {
			log.error("Problem in entering a password not containing any special characters, but containing one or more capital letters in the Create Your Password field in the register window");
			scenario.write("Problem in entering a password not containing any special characters, but containing one or more capital letters in the Create Your Password field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to enter a password not containing any special characters, but containing one or more capital letters in the Create Your Password field in the register window");
	    	scenario.write("Unable to enter a password not containing any special characters, but containing one or more capital letters in the Create Your Password field in the register window");
	    	throw e;
		}
	}
	
	@Then("^enter a password containing one or more special characters and one or more capital letters in the Create Your Password field in the register window$")
	public void enter_a_password_containing_one_or_more_special_characters_and_one_or_more_capital_letters_in_the_Create_Your_Password_field_in_the_register_window() throws Throwable {
		try {
	    	String password = "TTest@@123";
	    	String inputPlaceholder = "Create Your Password";
	    	actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			actions.scrollToElement(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
			adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).click();
			actions.type(adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder), password.trim());
			Assert.assertEquals(password.trim(), adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).getAttribute("value").trim());
			actions.takeScreenshot(driver);
			log.info("Able to enter a password containing one or more special characters and one or more capital letters in the Create Your Password field in the register window");
		} catch (Exception e) {
			log.error("Problem in entering a password containing one or more special characters and one or more capital letters in the Create Your Password field in the register window");
			scenario.write("Problem in entering a password containing one or more special characters and one or more capital letters in the Create Your Password field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to enter a password containing one or more special characters and one or more capital letters in the Create Your Password field in the register window");
	    	scenario.write("Unable to enter a password containing one or more special characters and one or more capital letters in the Create Your Password field in the register window");
	    	throw e;
		}
	}
	
	@Then("^in the password field in the registration window, enter password as \"([^\"]*)\"$")
	public void in_the_password_field_in_the_registration_window_enter_password_as(String pwdToEnter) throws Throwable {
	    try {
	    	String inputPlaceholder = "Create Your Password";
	    	adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).clear();
	    	actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
	    	actions.type(adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder), pwdToEnter);
	    	Assert.assertEquals(pwdToEnter, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).getAttribute("value").trim());
	    	actions.takeScreenshot(driver);
	    	log.info("Able to enter password as " + pwdToEnter + " in the password field in the registration window");
		} catch (Exception e) {
			log.error("Error in entering password as " + pwdToEnter + " in the password field in the registration window");
			scenario.write("Error in entering password as " + pwdToEnter + " in the password field in the registration window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to enter password as " + pwdToEnter + " in the password field in the registration window");
	    	scenario.write("Unable to enter password as " + pwdToEnter + " in the password field in the registration window");
	    	throw e;
		}
	}

	@Then("^click on the in the Create Your Username input field in the registration window$")
	public void click_on_the_in_the_Create_Your_Username_input_field_in_the_registration_window() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.createYourUsernameInRegistrationWindow);
		} catch (Exception e) {
			log.error("Unable to click on the in the Create Your Username input field in the registration window");
			scenario.write("Unable to click on the in the Create Your Username input field in the registration window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the \"([^\"]*)\" comes in the registration window$")
	public void check_if_the_comes_in_the_registration_window(String errorMsg) throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.errorMsgInRegistrationWindow(errorMsg));
			actions.takeScreenshot(driver);
			log.info("The " + errorMsg + " error message has come in the registration window");
		} catch (Exception e) {
			log.error("The " + errorMsg + " error message has not come in the registration window");
			scenario.write("The " + errorMsg + " error message has not come in the registration window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" button present in the forgot password popup$")
	public void click_on_the_button_present_in_the_forgot_password_popup(String btnName) throws Throwable {
	    try {
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.btnInForgotPwdPopUpList(btnName));
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 10000);
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.btnInForgotPwdPopUp(btnName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + btnName + " button present in the forgot password popup");
		} catch (Exception e) {
			log.error("Unable to click on the " + btnName + " button present in the forgot password popup");
			scenario.write("Unable to click on the " + btnName + " button present in the forgot password popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the Close icon in the Register popup window$")
	public void click_on_the_Close_icon_in_the_Register_popup_window() throws Throwable {
	    try {
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.closeIconInRegisterPopupWindowList);
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 10000);
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.closeIconInRegisterPopupWindow);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Close icon in the Register popup window");
		} catch (Exception e) {
			log.error("Unable to click on the Close icon in the Register popup window");
			scenario.write("Unable to click on the Close icon in the Register popup window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" link in the Registration popup window$")
	public void click_on_the_link_in_the_Registration_popup_window(String linkName) throws Throwable {
	    try {
	    	ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.closeIconInRegisterPopupWindowList);
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 10000);
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.linkInRegistrationPopupWindow(linkName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + linkName + " link in the Registration popup window");
		} catch (Exception e) {
			log.error("Unable to click on the " + linkName + " link in the Registration popup window");
			scenario.write("Unable to click on the " + linkName + " link in the Registration popup window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^switch to the window containing the title \"([^\"]*)\"$")
	public void switch_to_the_window_containing_the_title(String titleName) throws Throwable {
	    try {
	    	Boolean boolVal = false;
	    	for(String winHandle : driver.getWindowHandles()){
	    		if (driver.switchTo().window(winHandle).getTitle().contains(titleName)) {
	    			boolVal = true;
	    			break;
	    		}
	    	}
	    	Assert.assertEquals(true, boolVal);
	    	try {
				waitForPageToLoad();
			} catch (Exception e) {
				// do nothing
			}
	    	actions.takeScreenshot(driver);
	    	log.info("Able to switch to the new window containing the title " + titleName);
	    } catch (Exception e) {
	    	log.error("Error in switching to the new window containing the title " + titleName);
	    	scenario.write("Error in switching to the new window containing the title " + titleName);
	    	throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to switch to the new window containing the title " + titleName);
	    	scenario.write("Unable to switch to the new window containing the title " + titleName);
	    	throw e;
		}
	}

	@Then("^check if the text of the page title \"([^\"]*)\" is also showing in the page header$")
	public void check_if_the_text_of_the_page_title_is_also_showing_in_the_page_header(String pgTitle) throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.pgHeaderSameAsPgTitle(pgTitle));
			actions.takeScreenshot(driver);
			log.info("The text of the page title " + pgTitle + " is also showing the page header");
		} catch (Exception e) {
			log.error("The text of the page title " + pgTitle + " is not showing the page header");
			scenario.write("The text of the page title " + pgTitle + " is not showing the page header");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^close the window which has the current focus$")
	public void close_the_window_which_has_the_current_focus() throws Throwable {
	    try {
			driver.close();
			log.info("Able to close the window which has the current focus");
		} catch (Exception e) {
			log.error("Unable to close the window which has the current focus");
			scenario.write("Unable to close the window which has the current focus");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^click on the View Ad Creation Guidelines page in the Configure page$")
	public void click_on_the_View_Ad_Creation_Guidelines_page_in_the_Configure_page() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.viewAdCreationGuidelinesInConfigPg);
			actions.takeScreenshot(driver);
			log.info("Able to click on the View Ad Creation Guidelines page in the Configure page");
		} catch (Exception e) {
			log.error("Unable to click on the View Ad Creation Guidelines page in the Configure page");
			scenario.write("Unable to click on the View Ad Creation Guidelines page in the Configure page");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the Ad Creation Guidelines popup is showing$")
	public void check_if_the_Ad_Creation_Guidelines_popup_is_showing() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.adCreationGuidelinesPopup);
			actions.takeScreenshot(driver);
			log.info("The Ad Creation Guidelines popup is showing");
		} catch (Exception e) {
			log.error("The Ad Creation Guidelines popup is not showing");
			scenario.write("The Ad Creation Guidelines popup is not showing");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the close icon is present in the upload popup$")
	public void check_if_the_close_icon_is_present_in_the_upload_popup() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.closeIconInRegisterPopupWindow);
			actions.takeScreenshot(driver);
			log.info("The close icon is present in the upload popup");
		} catch (Exception e) {
			log.error("The close icon is not present in the upload popup");
			scenario.write("The close icon is not present in the upload popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the \"([^\"]*)\" button is present in the upload popup$")
	public void check_if_the_button_is_present_in_the_upload_popup(String btnName) throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.btnInUploadPopup(btnName));
			actions.takeScreenshot(driver);
			log.info("The " + btnName + " button is present in the upload popup");
		} catch (Exception e) {
			log.error("The " + btnName + " button is not present in the upload popup");
			scenario.write("The " + btnName + " button is not present in the upload popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^upload a file other than Jpg, Jpeg, Pdf, Png or Gif format$")
	public void upload_a_file_other_than_Jpg_Jpeg_Pdf_Png_or_Gif_format() throws Throwable {
	    try {
	    	adssRegressionSuitePgObjects.uploadLink.sendKeys(System.getProperty("user.dir") + "\\Images\\Image_TIFF.TIF");
	    	log.info("Able to upload a file other than Jpg, Jpeg, Pdf, Png or Gif format");
		} catch (Exception e) {
			log.error("Unable to upload a file other than Jpg, Jpeg, Pdf, Png or Gif format");
			scenario.write("Unable to upload a file other than Jpg, Jpeg, Pdf, Png or Gif format");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the appropriate error message \"([^\"]*)\" comes in the Upload PopUp in the Design your material page$")
	public void check_if_the_appropriate_error_message_comes_in_the_Upload_PopUp_in_the_Design_your_material_page(String errorMsg) throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.errorMsgInUploadPopupInDesignMaterialPg(errorMsg));
			actions.takeScreenshot(driver);
			log.info("The appropriate error message " + errorMsg + " comes in the Upload PopUp in the Design your material page");
		} catch (Exception e) {
			log.error("The appropriate error message " + errorMsg + " does not come in the Upload PopUp in the Design your material page");
			scenario.write("The appropriate error message " + errorMsg + " does not come in the Upload PopUp in the Design your material page");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^upload an image other than Jpeg, Tiff or Pdf format$")
	public void upload_an_image_other_than_Jpeg_Tiff_or_Pdf_format() throws Throwable {
		try {
	    	adssRegressionSuitePgObjects.uploadLink.sendKeys(System.getProperty("user.dir") + "\\Images\\Image 1.gif");
	    	log.info("Able to upload a file other than Jpeg, Tiff or Pdf format");
		} catch (Exception e) {
			log.error("Unable to upload a file other than Jpeg, Tiff or Pdf format");
			scenario.write("Unable to upload a file other than Jpeg, Tiff or Pdf format");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^check if the \"([^\"]*)\" button is present in the Confirmation page$")
	public void check_if_the_button_is_present_in_the_Confirmation_page(String btnName) throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.btnInConfirmationPg(btnName));
			actions.takeScreenshot(driver);
			log.info("The " + btnName + " button is present in the Confirmation page");
		} catch (Exception e) {
			log.error("The " + btnName + " button is not present in the Confirmation page");
			scenario.write("The " + btnName + " button is not present in the Confirmation page");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the show or hide your password icon beside the password field in the register window$")
	public void click_on_the_show_or_hide_your_password_icon_beside_the_password_field_in_the_register_window() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.showOrHidePwdIconInRegisterWindow);
			actions.takeScreenshot(driver);
			log.info("Able to click on the show or hide your password icon beside the password field in the register window");
		} catch (Exception e) {
			log.error("Unable to click on the show or hide your password icon beside the password field in the register window");
			scenario.write("Unable to click on the show or hide your password icon beside the password field in the register window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the password is displayed in the register window$")
	public void check_if_the_password_is_displayed_in_the_register_window() throws Throwable {
	    try {
	    	String inputPlaceholder = "Create Your Password";
	    	actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder));
	    	Assert.assertEquals("text", adssRegressionSuitePgObjects.inputFieldInRegisterWindow(inputPlaceholder).getAttribute("ng-reflect-type").trim());
	    	actions.takeScreenshot(driver);
	    	log.info("The password is displayed in the register window");
		} catch (Exception e) {
			log.error("Unable to check if the password is displayed in the register window");
			scenario.write("Unable to check if the password is displayed in the register window");
			throw e;
		}
	   catch (AssertionError e) {
		log.error("The password is not displayed in the register window");
		scenario.write("The password is not displayed in the register window");
		throw e;
	}
	}
	
	@Then("^check if the total price is not showing as \"([^\"]*)\"$")
	public void check_if_the_total_price_is_not_showing_as(String priceNotShown) throws Throwable {
	    try {
			Assert.assertEquals(1, adssRegressionSuitePgObjects.totalPriceInReviewOrderPg.size());
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.totalPriceInReviewOrderPg.get(0));
			Assert.assertNotEquals(priceNotShown, adssRegressionSuitePgObjects.totalPriceInReviewOrderPg.get(0).getText().trim());
			actions.takeScreenshot(driver);
			log.info("The total price is not showing as " + priceNotShown);
		} catch (Exception e) {
			log.error("Unable to check if the total price is not showing as " + priceNotShown);
			scenario.write("Unable to check if the total price is not showing as " + priceNotShown);
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("The total price is showing as " + priceNotShown);
	    	scenario.write("The total price is showing as " + priceNotShown);
	    	throw e;
		}
	}

	@Then("^check if the discounted price is now showing as \"([^\"]*)\"$")
	public void check_if_the_discounted_price_is_now_showing_as(String priceShown) throws Throwable {
	    try {
	    	Assert.assertEquals(3, adssRegressionSuitePgObjects.totalPriceInReviewOrderPg.size());
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.totalPriceInReviewOrderPg.get(2));
			Assert.assertEquals(priceShown, adssRegressionSuitePgObjects.totalPriceInReviewOrderPg.get(2).getText().trim());
			actions.takeScreenshot(driver);
			log.info("The discounted price is now showing as " + priceShown);
		} catch (Exception e) {
			log.error("Unable to check if the discounted price is now showing as " + priceShown);
			scenario.write("Unable to check if the discounted price is now showing as " + priceShown);
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("The discounted price is not showing as " + priceShown);
	    	scenario.write("The discounted price is not showing as " + priceShown);
	    	throw e;
		}
	}
	
	@Then("^in the Other Info tab, set the \"([^\"]*)\" input field value as \"([^\"]*)\"$")
	public void in_the_Other_Info_tab_set_the_input_field_value_as(String inputFld, String inputFldVal) throws Throwable {
	    try {
	    	ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfClickable(adssRegressionSuitePgObjects.inputFieldWithPlaceholder(inputFld));
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 10000);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.inputFieldWithPlaceholder(inputFld));
			actions.click(adssRegressionSuitePgObjects.inputFieldWithPlaceholder(inputFld));
			actions.type(adssRegressionSuitePgObjects.inputFieldWithPlaceholder(inputFld), inputFldVal);
			Assert.assertEquals(inputFldVal, adssRegressionSuitePgObjects.inputFieldWithPlaceholder(inputFld).getAttribute("value").trim());
			actions.takeScreenshot(driver);
			log.info("Able to set the " + inputFld + " input field value as " + inputFldVal + " in the Other Info tab");
		} catch (Exception e) {
			log.error("Error in setting the " + inputFld + " input field value as " + inputFldVal + " in the Other Info tab");
			scenario.write("Error in setting the " + inputFld + " input field value as " + inputFldVal + " in the Other Info tab");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to set the " + inputFld + " input field value as " + inputFldVal + " in the Other Info tab");
	    	scenario.write("Unable to set the " + inputFld + " input field value as " + inputFldVal + " in the Other Info tab");
	    	throw e;
		}
	}
	
	@Then("^check the image shown in the update Material section$")
	public void check_the_image_shown_in_the_update_Material_section() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.imgInUpdateMaterialSection);
			dataToNote = adssRegressionSuitePgObjects.imgInUpdateMaterialSection.getAttribute("src").trim();
			Assert.assertNotNull(dataToNote);
			Assert.assertNotEquals("", dataToNote);
			actions.takeScreenshot(driver);
			log.info("Able to check the image shown in the update Material section");
		} catch (Exception e) {
			log.error("Error in checking the image shown in the update Material section");
			scenario.write("Error in checking the image shown in the update Material section");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to check the image shown in the update Material section");
			scenario.write("Unable to check the image shown in the update Material section");
			throw e;
		}
	}

	@Then("^click on the \"([^\"]*)\" button in the update Material section$")
	public void click_on_the_button_in_the_update_Material_section(String btnName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.btnInUpdateMaterialSection(btnName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + btnName + " button in the update Material section");
		} catch (Exception e) {
			log.error("Unable to click on the " + btnName + " button in the update Material section");
			scenario.write("Unable to click on the " + btnName + " button in the update Material section");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the image is same in the Preview Ad Material popup$")
	public void check_if_the_image_is_same_in_the_Preview_Ad_Material_popup() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.imgInPreviewAdMaterialPopup);
			String imgSrc = adssRegressionSuitePgObjects.imgInPreviewAdMaterialPopup.getAttribute("src").substring(0, adssRegressionSuitePgObjects.imgInPreviewAdMaterialPopup.getAttribute("src").indexOf("&ad")).trim();
			Assert.assertEquals(dataToNote, imgSrc);
			actions.takeScreenshot(driver);
			log.info("The image is same in the Preview Ad Material popup");
		} catch (Exception e) {
			log.error("Unable to check if the image is same in the Preview Ad Material popup");
			scenario.write("Unable to check if the image is same in the Preview Ad Material popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("The image is not same in the Preview Ad Material popup");
	    	scenario.write("The image is not same in the Preview Ad Material popup");
	    	throw e;
		}
	}

	@Then("^click on the close icon in the Preview Ad Material popup$")
	public void click_on_the_close_icon_in_the_Preview_Ad_Material_popup() throws Throwable {
	    try {
	    	click_on_the_Close_icon_in_the_Register_popup_window();
	    	actions.takeScreenshot(driver);
	    	log.info("Able to click on the close icon in the Preview Ad Material popup");
		} catch (Exception e) {
			log.error("Unable to click on the close icon in the Preview Ad Material popup");
			scenario.write("Unable to click on the close icon in the Preview Ad Material popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^click on the \"([^\"]*)\" button link in the Start Over or Please Confirm popup$")
	public void click_on_the_button_link_in_the_Start_Over_or_Please_Confirm_popup(String btnName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.btnLinkInStartOverPopup(btnName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + btnName + " button link in the Start Over or Please Confirm popup");
		} catch (Exception e) {
			log.error("Unable to click on the " + btnName + " button link in the Start Over or Please Confirm popup");
			scenario.write("Unable to click on the " + btnName + " button link in the Start Over or Please Confirm popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" button in the Edit Ad Material$")
	public void click_on_the_button_in_the_Edit_Ad_Material(String btnName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.btnInEditAdMaterialSection(btnName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + btnName + " button in the Edit Material");
		} catch (Exception e) {
			log.error("Unable to click on the " + btnName + " button in the Edit Material");
			scenario.write("Unable to click on the " + btnName + " button in the Edit Material");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the Close icon in the Edit Account Info window$")
	public void click_on_the_Close_icon_in_the_Edit_Account_Info_window() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.closeIconInRegisterPopupWindow);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Close icon in the Edit Account Info window");
		} catch (Exception e) {
			log.error("Unable to click on the Close icon in the Edit Account Info window");
			scenario.write("Unable to click on the Close icon in the Edit Account Info window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the User Profile link$")
	public void click_on_the_User_Profile_link() throws Throwable {
	    try {
	    	try{
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.usernameLink);
	    	}
	    	catch(WebDriverException e){
	    		Thread.sleep(5000);
	    		actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.usernameLink);
	    	}
			actions.takeScreenshot(driver);
			log.info("Able to click on the User Profile link");
		} catch (Exception e) {
			log.error("Unable to click on the User Profile link");
			scenario.write("Unable to click on the User Profile link");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^wait for (\\d+) seconds$")
	public void wait_for_seconds(int nrOfSeconds) throws Throwable {
	    try {
			int nrOfSecondsToWait = nrOfSeconds*1000;
			Thread.sleep(nrOfSecondsToWait);
			try{actions.takeScreenshot(driver);
			}
			catch(NoSuchWindowException e){
				// do nothing
			}
			
			log.info("Able to wait for " + nrOfSeconds + " seconds");
		} catch (Exception e) {
			log.error("Unable to wait for " + nrOfSeconds + " seconds");
			scenario.write("Unable to wait for " + nrOfSeconds + " seconds");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^click on the \"([^\"]*)\" link from the User Profile options$")
	public void click_on_the_link_from_the_User_Profile_options(String linkToClick) throws Throwable {
	    try {
	    	try{
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.linkToClickFromUserProfileOptions(linkToClick));
	    	}
	    	catch(WebDriverException e){
	    		actions.javascriptClk(driver, adssRegressionSuitePgObjects.linkToClickFromUserProfileOptions(linkToClick));
	    	}
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + linkToClick + " link from the User Profile options");
		} catch (Exception e) {
			log.error("Unable to click on the " + linkToClick + " link from the User Profile options");
			scenario.write("Unable to click on the " + linkToClick + " link from the User Profile options");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^click on \"([^\"]*)\" menu option in My Dashboard$")
	public void click_on_menu_option_in_My_Dashboard(String optionToClick) throws Throwable {
	    try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.menuInMydashboardPg(optionToClick));
			actions.javascriptClk(driver, adssRegressionSuitePgObjects.menuInMydashboardPg(optionToClick));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + optionToClick + " menu option in My Dashboard");
		} catch (Exception e) {
			log.error("Unable to click on the " + optionToClick + " menu option in My Dashboard");
			scenario.write("Unable to click on the " + optionToClick + " menu option in My Dashboard");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if datas are present under the menu option in My Dashboard$")
	public void check_if_datas_are_present_under_the_menu_option_in_My_Dashboard() throws Throwable {
	    try {
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.datasUnderMenuOptionInMyDashboard);
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 20000);
			Assert.assertTrue(adssRegressionSuitePgObjects.datasUnderMenuOptionInMyDashboard.size()>0);
			try{
			Thread.sleep(5000);
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.datasUnderMenuOptionInMyDashboard.get(0));
			}
			catch(TimeoutException | StaleElementReferenceException e){
				actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.datasUnderMenuOptionInMyDashboard1.get(0));
			}
			actions.takeScreenshot(driver);
			log.info("Datas are present under the menu option in My Dashboard");
		} catch (Exception e) {
			log.error("Unable to check if datas are present under the menu option in My Dashboard");
			scenario.write("Unable to check if datas are present under the menu option in My Dashboard");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("No datas are present under the menu option in My Dashboard");
	    	scenario.write("No datas are present under the menu option in My Dashboard");
	    	throw e;
		}
	}
	
	@Then("^check the image shown in the Material Preview Section in the Design Your Material$")
	public void check_the_image_shown_in_the_Material_Preview_Section_in_the_Design_Your_Material() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.imgInMaterialPreviewSectionInDesignYourMaterial);
			dataToNote = adssRegressionSuitePgObjects.imgInMaterialPreviewSectionInDesignYourMaterial.getAttribute("src").trim();
			dataToNote = dataToNote.substring(0, (dataToNote.indexOf("&ts"))+3).trim();
			Assert.assertNotEquals("", dataToNote);
			Assert.assertNotNull(dataToNote);
			actions.takeScreenshot(driver);
			log.info("Able to check the image shown in the Material Preview Section in the Design Your Material");
		} catch (Exception e) {
			log.error("Error in checking the image shown in the Material Preview Section in the Design Your Material");
			scenario.write("Error in checking the image shown in the Material Preview Section in the Design Your Material");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to check the image shown in the Material Preview Section in the Design Your Material");
	    	scenario.write("Unable to check the image shown in the Material Preview Section in the Design Your Material");
	    	throw e;
		} 
	}

	@Then("^click on the \"([^\"]*)\" icon in the Material Preview Section in the Design Your Material$")
	public void click_on_the_icon_in_the_Material_Preview_Section_in_the_Design_Your_Material(String iconName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.iconInMaterialPreviewSectionInDesignYourMaterialPg(iconName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + iconName + " icon in the Material Preview Section in the Design Your Material");
		} catch (Exception e) {
			log.error("Unable to click on the " + iconName + " icon in the Material Preview Section in the Design Your Material");
			scenario.write("Unable to click on the " + iconName + " icon in the Material Preview Section in the Design Your Material");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the same image is shown in the Material Preview Section in the Design Your Material$")
	public void check_if_the_same_image_is_shown_in_the_Material_Preview_Section_in_the_Design_Your_Material() throws Throwable {
	    try {
	    	actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.imgInMaterialPreviewSectionInDesignYourMaterial);
	    	String imgSrc = adssRegressionSuitePgObjects.imgInMaterialPreviewSectionInDesignYourMaterial.getAttribute("src").trim();
	    	imgSrc = imgSrc.substring(0, (imgSrc.indexOf("&ts"))+3).trim();
	    	Assert.assertEquals(dataToNote, imgSrc);
	    	actions.takeScreenshot(driver);
	    	log.info("The same image is shown in the Material Preview Section in the Design Your Material");
		} catch (Exception e) {
			log.error("Unable to check if the same image is shown in the Material Preview Section in the Design Your Material");
			scenario.write("Unable to check if the same image is shown in the Material Preview Section in the Design Your Material");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("The same image is not shown in the Material Preview Section in the Design Your Material");
	    	scenario.write("The same image is not shown in the Material Preview Section in the Design Your Material");
	    	throw e;
		}
	}
	
	@Then("^note the next available date$")
	public void note_the_next_available_date() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.calendarDates.get(adssRegressionSuitePgObjects.calendarDates.size()-2));
			dataToNote = adssRegressionSuitePgObjects.calendarDates.get(adssRegressionSuitePgObjects.calendarDates.size()-2).getText().trim();
			Assert.assertNotNull(dataToNote);
			Assert.assertNotEquals("", dataToNote);
			actions.takeScreenshot(driver);
			log.info("Able to note the next available date");
		} catch (Exception e) {
			log.error("Error in noting the next available date");
			scenario.write("Error in noting the next available date");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to note the next available date");
	    	scenario.write("Unable to note the next available date");
	    	throw e;
		}
	}

	@Then("^check if the earlier noted date is selected and highlighted in yellow$")
	public void check_if_the_earlier_noted_date_is_selected_and_highlighted_in_yellow() throws Throwable {
	    try {
	    	actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.calendarDates.get(adssRegressionSuitePgObjects.calendarDates.size()-2));
	    	Assert.assertEquals(dataToNote, adssRegressionSuitePgObjects.calendarDates.get(adssRegressionSuitePgObjects.calendarDates.size()-2).getText().trim());
	    	Assert.assertEquals("rgba(255, 193, 7, 1)", adssRegressionSuitePgObjects.calendarDates.get(adssRegressionSuitePgObjects.calendarDates.size()-2).getCssValue("background-color"));
	    	actions.takeScreenshot(driver);
	    	log.info("The earlier noted date is selected and highlighted in yellow");
		} catch (Exception e) {
			log.error("Unable to check if the earlier noted date is selected and highlighted in yellow");
			scenario.write("Unable to check if the earlier noted date is selected and highlighted in yellow");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("The earlier noted date is not selected and highlighted in yellow");
	    	scenario.write("The earlier noted date is not selected and highlighted in yellow");
	    	throw e;
		}
	}
	
	@Then("^click on the expand icon in the Current Total section$")
	public void click_on_the_expand_icon_in_the_Current_Total_section() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.expandIconInCurrentTotalDetailsSection);
			actions.takeScreenshot(driver);
			log.info("Able to click on the expand icon in the Current Total section");
		} catch (Exception e) {
			log.error("Unable to click on the expand icon in the Current Total section");
			scenario.write("Unable to click on the expand icon in the Current Total section");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if on expanding the icon in the Current Total section, details are displayed$")
	public void check_if_on_expanding_the_icon_in_the_Current_Total_section_details_are_displayed() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.detailsAvailableAfterExpandingIconInCurrentTotalSection);
			actions.takeScreenshot(driver);
			log.info("On expanding the icon in the Current Total section, details are displayed");
		} catch (Exception e) {
			log.error("Oon expanding the icon in the Current Total section, details are not displayed");
			scenario.write("Oon expanding the icon in the Current Total section, details are not displayed");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^select the Payment option as \"([^\"]*)\"$")
	public void select_the_Payment_option_as(String paymentOption) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.paymentOptionDropdownIcon);
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.paymentOptionToChoose(paymentOption));
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.paymentOptionDropdownIcon);
			Assert.assertEquals(paymentOption, adssRegressionSuitePgObjects.paymentOptionDropdownIcon.getText().trim());
			actions.takeScreenshot(driver);
			log.info("Able to select the Payment option as " + paymentOption);
		} catch (Exception e) {
			log.error("Error in selecting the Payment option as " + paymentOption);
			scenario.write("Error in selecting the Payment option as " + paymentOption);
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to select the Payment option as " + paymentOption);
	    	scenario.write("Unable to select the Payment option as " + paymentOption);
	    	throw e;
		}
	}

	@Then("^check if the Billing options are displayed$")
	public void check_if_the_Billing_options_are_displayed() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.billingOptions);
			actions.takeScreenshot(driver);
			log.info("The Billing options are displayed");
		} catch (Exception e) {
			log.error("The Billing options are not displayed");
			scenario.write("The Billing options are not displayed");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the Credit Card options are displayed$")
	public void check_if_the_Credit_Card_options_are_displayed() throws Throwable {
	    try {
	    	actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.creditCardOptions);
			actions.takeScreenshot(driver);
			log.info("The Credit Card options are displayed");
		} catch (Exception e) {
			log.error("The Credit Card options are not displayed");
			scenario.write("The Credit Card options are not displayed");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^choose the \"([^\"]*)\" Credit Card option$")
	public void choose_the_Credit_Card_option(String credictCardOption) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.creditCardOptionToSelect(credictCardOption));
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.selectedCreditCard(adssRegressionSuitePgObjects.creditCardOptionToSelect(credictCardOption)));
			Assert.assertTrue(adssRegressionSuitePgObjects.selectedCreditCard(adssRegressionSuitePgObjects.creditCardOptionToSelect(credictCardOption)).getAttribute("class").contains("cdk-program-focused"));
			actions.takeScreenshot(driver);
			log.info("Able to choose the " + credictCardOption + " Credit card option");
		} catch (Exception e) {
			log.error("Error in choosing the " + credictCardOption + " Credit card option");
	    	scenario.write("Error in choosing the " + credictCardOption + " Credit card option");
	    	throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to choose the " + credictCardOption + " Credit card option");
	    	scenario.write("Unable to choose the " + credictCardOption + " Credit card option");
	    	throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" button in the Confirmation page$")
	public void click_on_the_button_in_the_Confirmation_page(String btnName) throws Throwable {
	    try {
	    	JavascriptExecutor js= (JavascriptExecutor)driver;
	    	js.executeScript("arguments[0].scrollIntoView();",adssRegressionSuitePgObjects.btnInConfirmationPg(btnName));
	    	check_if_the_button_is_present_in_the_Confirmation_page(btnName);
	    	actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.btnInConfirmationPg(btnName));
	    	actions.takeScreenshot(driver);
	    	log.info("Able to click on the " + btnName + " button in the Confirmation page");
		} catch (Exception e) {
			log.error("Unable to click on the " + btnName + " button in the Confirmation page");
			scenario.write("Unable to click on the " + btnName + " button in the Confirmation page");
			throw e;
		} 
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if (\\d+) windows are present$")
	public void check_if_windows_are_present(int nrOfWindows) throws Throwable {
	    try {
			Set<String> totalNrOfWindows = driver.getWindowHandles();
			Assert.assertEquals(nrOfWindows, totalNrOfWindows.size());
			try{
			actions.takeScreenshot(driver);
			}
			catch(NoSuchWindowException e){
				// do nothing
			}
			log.info(nrOfWindows + " windows are present");
		} catch (Exception e) {
			log.error("Unable to check if " + nrOfWindows + " windows are present");
			scenario.write("Unable to check if " + nrOfWindows + " windows are present");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(nrOfWindows + " windows are not present");
	    	scenario.write(nrOfWindows + " windows are not present");
	    	throw e;
		}
	}

	@Then("^switch to the (\\d+) tab$")
	public void switch_to_the_tab(int tabnr) throws Throwable {
	    try {
			ArrayList<String> arrayList = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(arrayList.get(tabnr-1));
			Thread.sleep(12000);
			log.info("Able to switch to the " + tabnr + " tab");
		} catch (Exception e) {
			log.error("Unable to switch to the " + tabnr + " tab");
			scenario.write("Unable to switch to the " + tabnr + " tab");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^close the focussed tab$")
	public void close_the_focussed_tab() throws Throwable {
	    try {
	    	close_the_window_which_has_the_current_focus();
	    	log.info("Able to close the focussed tab");
		} catch (Exception e) {
			log.error("Unable to close the focussed tab");
			scenario.write("Unable to close the focussed tab");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the (\\d+) three dot icon from the list of Orders$")
	public void click_on_the_three_dot_icon_from_the_list_of_Orders(int indexNr) throws Throwable {
	    try {
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.threeDotIconListFromOrdersList);
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 20000);
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.threeDotIconListFromOrdersList.get(indexNr-1));
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.editIconInOrdersList);
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + indexNr + " three dot icon from the list of orders");
		} catch (Exception e) {
			log.error("Error in clicking on the " + indexNr + " three dot icon from the list of orders");
			scenario.write("Error in clicking on the " + indexNr + " three dot icon from the list of orders");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to click on the " + indexNr + " three dot icon from the list of orders");
	    	scenario.write("Unable to click on the " + indexNr + " three dot icon from the list of orders");
	    	throw e;
		}
	}

	@Then("^click on the Edit icon from the clicked three dot icon in the list of Orders$")
	public void click_on_the_Edit_icon_from_the_clicked_three_dot_icon_in_the_list_of_Orders() throws Throwable {
	    try {
	    	Thread.sleep(2000);
	    	actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.editIconInOrdersList);
	    	try {
	    		waitForPageToLoad();
	    	} catch (WebDriverException e) {
	    		// do nothing
	    	}
	    	//check_if_the_button_is_present_in_the_Confirmation_page("Receipt");
	    	actions.takeScreenshot(driver);
	    	log.info("Able to click on the Edit icon from the clicked three dot icon in the list of Orders");
		} catch (Exception e) {
			log.error("Unable to click on the Edit icon from the clicked three dot icon in the list of Orders");
			scenario.write("Unable to click on the Edit icon from the clicked three dot icon in the list of Orders");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Given("^Open the URL to test the \"([^\"]*)\" Purchase Functionality having \"([^\"]*)\" package$")
	public void open_the_URL_to_test_the_Purchase_Functionality_having_package(String buName, String pkgName) throws Throwable {
	    try {
	    	String environment;
			String urlToTest;
			String url = buName + pkgName;
			data = PropertiesFile.readInputPropertiesFile();
			try {
				environment = System.getProperty("environment");
				urlToTest = url + environment + "URL";
				driver.get(data.getProperty(urlToTest));
			} catch (NullPointerException e) {
				environment = "QA";
				urlToTest = url + environment + "URL";
				driver.get(data.getProperty(urlToTest));
			}
			waitForPageToLoad();
			actions.takeScreenshot(driver);
		} catch (Exception e) {
			log.error("Unable to open the URL to test the " + buName + " Purchase Functionality having " + pkgName + " package");
			scenario.write("Unable to open the URL to test the " + buName + " Purchase Functionality having " + pkgName + " package");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the not opened error messages are showing in the page$")
	public void check_if_the_not_opened_error_messages_are_showing_in_the_page(List<String> errorMsgs) throws Throwable {
	    try {
			for(String errorMsg: errorMsgs){
				ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

					@Override
					public Boolean execute() {
						return actions.elementIfDisplayed(adssRegressionSuitePgObjects.errorMsgInHomePgList(errorMsg));
					}
				};
				actions.waitUntilValueIsTrue(executeCommand, 20000);
				actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.errorMsgInHomePg(errorMsg));
			}
			actions.takeScreenshot(driver);
			log.info("All the mentioned not opened error messages are showing in the page");
		} catch (Exception e) {
			log.error("The mentioned not opened error messages are not showing in the page");
			scenario.write("The mentioned not opened error messages are not showing in the page");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^fill in the billing info having credit card number starting with two, and some additional details in the expiry date and CVV$")
	public void fill_in_the_billing_info_having_credit_card_number_starting_with_two_and_some_additional_details_in_the_expiry_date_and_CVV() throws Throwable {
		try {
			String environment=System.getProperty("environment");
			try{
				if(environment.trim().equalsIgnoreCase("PROD")){
					Utils.fillText(adssRegressionSuitePgObjects.cardNumber, System.getProperty("CCNumber"));
				}
				else {
					Utils.fillText(adssRegressionSuitePgObjects.cardNumber, "2226470000082999");
				}
			}
			catch (NullPointerException e) {
				Utils.fillText(adssRegressionSuitePgObjects.cardNumber, "2226470000082999");
			}

			//expirationMonth.click();
			//DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'February')]")).click();
			try {Thread.sleep(3000);}catch(Exception e){}
			Utils.selectDropdown(adssRegressionSuitePgObjects.expirationMonth, "February");

			//expirationYear.click();
			//DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'2021')]")).click();
			try {Thread.sleep(2000);}catch(Exception e){}
//			Calendar now = Calendar.getInstance();   // Gets the current date and time
//			int year = now.get(Calendar.YEAR)+1; 
			Utils.selectDropdown(adssRegressionSuitePgObjects.expirationYear, "2021");
			try{
				if(environment.trim().equalsIgnoreCase("PROD")){
					Utils.fillText(adssRegressionSuitePgObjects.verificationNumber, System.getProperty("CVV"));
				}
				else {
					Utils.fillText(adssRegressionSuitePgObjects.verificationNumber, "923");
				}
			}
			catch (NullPointerException e) {
				Utils.fillText(adssRegressionSuitePgObjects.verificationNumber, "923");
			}
			actions.takeScreenshot(driver);
			log.info("Able to fill in all the billing info");
		} catch (Exception e) {
			log.error("Error in filling in the billing info");
			scenario.write("Error in filling in the billing info");
			throw e;
		}
		catch (AssertionError e) {
			log.error(e);
			scenario.write(e.getMessage());
			throw e;
		}
	}
	
	@Then("^check if the yellow color highlighted error message is showing on bottom$")
	public void check_if_the_yellow_color_highlighted_error_message_is_showing_on_bottom() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.yellowColorErrorMsgInBottomOfConfigPg);
			Assert.assertEquals("rgba(255, 193, 7, 1)", adssRegressionSuitePgObjects.yellowColorErrorMsgInBottomOfConfigPg.getCssValue("background-color").trim());
			actions.takeScreenshot(driver);
			log.info("The yellow color highlighted error message is showing on bottom");
		} catch (Exception e) {
			log.error("The yellow color highlighted error message is not showing on bottom");
			scenario.write("The yellow color highlighted error message is not showing on bottom");
			throw e;
		}
	    catch (AssertionError e) {
			log.error("The highlighted error message is not colored yellow, which is showing on bottom");
			scenario.write("The highlighted error message is not colored yellow, which is showing on bottom");
			throw e;
		}
	}

	@Then("^check if the yellow color highlighted error message is not showing on bottom$")
	public void check_if_the_yellow_color_highlighted_error_message_is_not_showing_on_bottom() throws Throwable {
		try {
			Boolean boolFound = false;
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", adssRegressionSuitePgObjects.yellowColorErrorMsgInBottomOfConfigPg);
			Thread.sleep(3000);
			try {
				actions.waitUntilElementVisibleSmall(driver, adssRegressionSuitePgObjects.yellowColorErrorMsgInBottomOfConfigPg);
				boolFound = true;
			} catch (NoSuchElementException | TimeoutException e) {
				boolFound = false;
			}
			Assert.assertEquals(false, boolFound);
			actions.takeScreenshot(driver);
			log.info("The yellow color highlighted error message is not showing on bottom");
		} catch (Exception e) {
			log.error("The yellow color highlighted error message is showing on bottom");
			scenario.write("The yellow color highlighted error message is showing on bottom");
			throw e;
		}
		catch (AssertionError e) {
			log.error(e);
			scenario.write(e.getMessage());
			throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" tab in the Order page$")
	public void click_on_the_tab_in_the_Order_page(String tabName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.tabInOrderTbl(tabName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + tabName + " tab in the Order table");
		} catch (Exception e) {
			log.error("Unable to click on the " + tabName + " tab in the Order table");
			scenario.write("Unable to click on the " + tabName + " tab in the Order table");
			throw e;
		}
	    catch (AssertionError e) {
			log.error(e);
			scenario.write(e.getMessage());
			throw e;
		}
	}
	
	@Then("^check if only (\\d+) dollar amounts are showing in the Payments tab$")
	public void check_if_only_dollar_amounts_are_showing_in_the_Payments_tab(int nrOfAmntsShown) throws Throwable {
	    try {
			Assert.assertEquals(nrOfAmntsShown, adssRegressionSuitePgObjects.nrOfDollarAmtsShownInPaymentsTab.size());
			actions.takeScreenshot(driver);
			log.info("Only " + nrOfAmntsShown + " dollar amounts are showing in the Payments tab");
		} catch (Exception e) {
			log.error("Unable to check if only " + nrOfAmntsShown + " dollar amounts are showing in the Payments tab");
			scenario.write("Unable to check if only " + nrOfAmntsShown + " dollar amounts are showing in the Payments tab");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Only " + nrOfAmntsShown + " dollar amounts are not showing in the Payments tab");
	    	scenario.write("Only " + nrOfAmntsShown + " dollar amounts are not showing in the Payments tab");
	    	throw e;
		}
	}
	
	@Then("^click on the three dot icon in the order page$")
	public void click_on_the_three_dot_icon_in_the_order_page() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.threeDotIconInOrderPg);
			actions.takeScreenshot(driver);
			log.info("Able to click on the three dot icon in the order table");
		} catch (Exception e) {
			log.error("Unable to click on the three dot icon in the order table");
			scenario.write("Unable to click on the three dot icon in the order table");
			throw e;
		}
	    catch (AssertionError e) {
			log.error(e);
			scenario.write(e.getMessage());
			throw e;
		}
	}
	
	@Then("^select \"([^\"]*)\" from the list of options appearing after clicking on the three dot icon in the order page$")
	public void select_from_the_list_of_options_appearing_after_clicking_on_the_three_dot_icon_in_the_order_page(String optionName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.optionToSelectAfterClickingOnTheThreeDotIconInTheOrderPg(optionName));
			actions.takeScreenshot(driver);
			log.info("Able to select " + optionName + " from the list of options appearing after clicking on the three dot icon in the order page");
		} catch (Exception e) {
			log.error("Unable to select " + optionName + " from the list of options appearing after clicking on the three dot icon in the order page");
			scenario.write("Unable to select " + optionName + " from the list of options appearing after clicking on the three dot icon in the order page");
			throw e;
		}
	    catch (AssertionError e) {
			log.error(e);
			scenario.write(e.getMessage());
			throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" option from the popup appearing in the Order page$")
	public void click_on_the_option_from_the_popup_appearing_in_the_Order_page(String optionName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.optionToClickFromPopupInOrderPg(optionName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + optionName+ " option from the popup appearing in the Order page");
		} catch (Exception e) {
			log.error("Unable to click on the " + optionName+ " option from the popup appearing in the Order page");
			scenario.write("Unable to click on the " + optionName+ " option from the popup appearing in the Order page");
			throw e;
		}
	    catch (AssertionError e) {
			log.error(e);
			scenario.write(e.getMessage());
			throw e;
		}
	}
	
	@Then("^refresh the page and wait for (\\d+) seconds$")
	public void refresh_the_page_and_wait_for_seconds(int nrOfSecondsToWait) throws Throwable {
	    try {
			driver.navigate().refresh();
			wait_for_seconds(nrOfSecondsToWait);
			actions.takeScreenshot(driver);
			log.info("Able to refresh the page, and wait for " + nrOfSecondsToWait + " seconds");
		} catch (Exception e) {
			log.error("Unable to refresh the page, and wait for " + nrOfSecondsToWait + " seconds");
			scenario.write("Unable to refresh the page, and wait for " + nrOfSecondsToWait + " seconds");
			throw e;
		}
	    catch (AssertionError e) {
			log.error(e);
			scenario.write(e.getMessage());
			throw e;
		}
	}
	
	@Then("^check if the \"([^\"]*)\" is not checked$")
	public void check_if_the_is_not_checked(String credictCardOption) throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.selectedCreditCard(adssRegressionSuitePgObjects.creditCardOptionToSelect(credictCardOption)));
			Assert.assertFalse(adssRegressionSuitePgObjects.selectedCreditCard(adssRegressionSuitePgObjects.creditCardOptionToSelect(credictCardOption)).getAttribute("class").contains("cdk-program-focused"));
			actions.takeScreenshot(driver);
			log.info("The " + credictCardOption + " is not checked");
		} catch (Exception e) {
			log.error("Unable to check if the " + credictCardOption + " is not checked");
			scenario.write("Unable to check if the " + credictCardOption + " is not checked");
			throw e;
		}
	    catch (AssertionError e) {
			log.error("The " + credictCardOption + " is checked");
			scenario.write("The " + credictCardOption + " is checked");
			throw e;
		}
	}
	
	@Then("^open the bookmarked page$")
	public void open_the_bookmarked_page() throws Throwable {
	    try {
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("javascript:void(window.open('','','toolbar=0,scrollbars=1,location=0,status=1,menubar=0,resizable=1,width=550,height=510').document.write('<html><head><script src=\"//dss.p2p.stage.trbdevcloud.com/assets/inspector.js\"></script></head><body>Please wait while the DSS Inspector initializes.</body></html>'));");
	        log.info("Able to open the bookmarked page");
		} catch (Exception e) {
			log.error("Unable to open the bookmarked page");
			scenario.write("Unable to open the bookmarked page");
			throw e;
		}
	}
	
	@Then("^maximize the focussed window$")
	public void maximize_the_focussed_window() throws Throwable {
	    try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			log.error("Unable to maximize the focussed window");
			scenario.write("Unable to maximize the focussed window");
			throw e;
		}
	}
	
	@Then("^check if \"([^\"]*)\" is showing in the new tab$")
	public void check_if_is_showing_in_the_new_tab(String fieldName) throws Throwable {
	    try {
	    	driver.switchTo().activeElement();
	    	adssRegressionSuitePgObjects.fieldInNewTab(fieldName).click();
			actions.takeScreenshot(driver);
			log.info("The " + fieldName + " field is showing in the new tab");
		} catch (Exception e) {
			log.error("The " + fieldName + " field is not showing in the new tab");
			scenario.write("The " + fieldName + " field is not showing in the new tab");
			throw e;
		}
	}
	
	@Then("^select the \"([^\"]*)\" section above the textarea paragraph field$")
	public void select_the_section_above_the_textarea_paragraph_field(String sectionType) throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.sectionAboveTextAreaParagraph(sectionType));
			Assert.assertFalse(adssRegressionSuitePgObjects.sectionAboveTextAreaParagraph(sectionType).getAttribute("class").contains("active"));
			actions.javascriptClk(driver, adssRegressionSuitePgObjects.sectionAboveTextAreaParagraph(sectionType));
			Assert.assertTrue(adssRegressionSuitePgObjects.sectionAboveTextAreaParagraph(sectionType).getAttribute("class").contains("active"));
			actions.takeScreenshot(driver);
			log.info("Able to select the " + sectionType + " section above the textarea paragraph field");
		} catch (Exception e) {
			log.error("Error in selecting the " + sectionType + " section above the textarea paragraph field");
			scenario.write("Error in selecting the " + sectionType + " section above the textarea paragraph field");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to select the " + sectionType + " section above the textarea paragraph field");
	    	scenario.write("Unable to select the " + sectionType + " section above the textarea paragraph field");
	    	throw e;
		}
	}

	@Then("^enter text as \"([^\"]*)\" in the textarea paragraph field, &, hit Enter$")
	public void enter_text_as_in_the_textarea_paragraph_field_hit_Enter(String textToEnter) throws Throwable {
	    try {
			actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.CTCObitText);
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.CTCObitText);
			adssRegressionSuitePgObjects.CTCObitText.sendKeys(textToEnter);
			/*catch(WebDriverException e){
				actions.setClipboardData(textToEnter);
				actions.keyboardPaste();
			} */
			actions.keyboardEnter();
			Assert.assertTrue(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.size()>0);
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.get(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.size()-2));
			Assert.assertEquals(textToEnter.trim(), adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.get(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.size()-2).getText().trim());
			actions.takeScreenshot(driver);
			log.info("Able to enter text as " + textToEnter + " in the textarea paragraph field, &, hit Enter");
		} catch (Exception e) {
			log.error("Error in entering text as " + textToEnter + " in the textarea paragraph field, &, hit Enter");
			scenario.write("Error in entering text as " + textToEnter + " in the textarea paragraph field, &, hit Enter");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to enter text as " + textToEnter + " in the textarea paragraph field, &, hit Enter");
	    	scenario.write("Unable to enter text as " + textToEnter + " in the textarea paragraph field, &, hit Enter");
	    	throw e;
		}
	}

	@Then("^check if the entered text is highlighted in bold$")
	public void check_if_the_entered_text_is_highlighted_in_bold() throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.boldTextInTextArea(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.get(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.size()-2)));
			actions.takeScreenshot(driver);
			log.info("The entered text is highlighted in bold");
		} catch (Exception e) {
			log.error("The entered text is not highlighted in bold");
			scenario.write("The entered text is not highlighted in bold");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^unselect the \"([^\"]*)\" section above the textarea paragraph field$")
	public void unselect_the_section_above_the_textarea_paragraph_field(String sectionType) throws Throwable {
	    try {
	    	actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.sectionAboveTextAreaParagraph(sectionType));
			Assert.assertTrue(adssRegressionSuitePgObjects.sectionAboveTextAreaParagraph(sectionType).getAttribute("class").contains("active"));
			actions.javascriptClk(driver, adssRegressionSuitePgObjects.sectionAboveTextAreaParagraph(sectionType));
			Assert.assertFalse(adssRegressionSuitePgObjects.sectionAboveTextAreaParagraph(sectionType).getAttribute("class").contains("active"));
			actions.takeScreenshot(driver);
			log.info("Able to unselect the " + sectionType + " section above the textarea paragraph field");
		} catch (Exception e) {
			log.error("Error in unselecting the " + sectionType + " section above the textarea paragraph field");
			scenario.write("Error in unselecting the " + sectionType + " section above the textarea paragraph field");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to unselect the " + sectionType + " section above the textarea paragraph field");
	    	scenario.write("Unable to unselect the " + sectionType + " section above the textarea paragraph field");
	    	throw e;
		}
	}

	@Then("^check if the entered text is highlighted in italic$")
	public void check_if_the_entered_text_is_highlighted_in_italic() throws Throwable {
	    try {
	    	actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.italicTextInTextArea(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.get(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.size()-2)));
			actions.takeScreenshot(driver);
			log.info("The entered text is highlighted in italic");
		} catch (Exception e) {
			log.error("The entered text is not highlighted in italic");
			scenario.write("The entered text is not highlighted in italic");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^check if the entered text is highlighted in underline$")
	public void check_if_the_entered_text_is_highlighted_in_underline() throws Throwable {
		try {
	    	actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.underlinedTextInTextArea(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.get(adssRegressionSuitePgObjects.textAreaParagrphFldWithChars.size()-2)));
			actions.takeScreenshot(driver);
			log.info("The entered text is highlighted in underline");
		} catch (Exception e) {
			log.error("The entered text is not highlighted in underline");
			scenario.write("The entered text is not highlighted in underline");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^click the clean button above the textarea paragraph field$")
	public void click_the_clean_button_above_the_textarea_paragraph_field() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.cleanBtnAboveTextAreaParagraphFld);
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.confirmTextFormattingPopUp);
			actions.takeScreenshot(driver);
			log.info("Able to click on the clean button in aboove the textarea paragraph field");
		} catch (Exception e) {
			log.error("Unable to click on the clean button in aboove the textarea paragraph field");
			scenario.write("Unable to click on the clean button in aboove the textarea paragraph field");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" option from the Confirm Text Formatting popup$")
	public void click_on_the_option_from_the_Confirm_Text_Formatting_popup(String optionName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.optionFromConfirmTextFormattingPopUp(adssRegressionSuitePgObjects.confirmTextFormattingPopUp, optionName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + optionName + " option from the Confirm Text Formatting popup");
		} catch (Exception e) {
			log.error("Unable to click on the " + optionName + " option from the Confirm Text Formatting popup");
			scenario.write("Unable to click on the " + optionName + " option from the Confirm Text Formatting popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	

	@Then("^check if none of the texts are highlighted in bold, italic or underline$")
	public void check_if_none_of_the_texts_are_highlighted_in_bold_italic_or_underline() throws Throwable {
	    try {
			Assert.assertTrue(adssRegressionSuitePgObjects.textsPresentNotHighlightedInAnyFormat.size()>0);
			actions.takeScreenshot(driver);
			log.info("None of the texts are highlighted in bold, italic or underline");
		} catch (Exception e) {
			log.error("Unable to check if none of the texts are highlighted in bold, italic or underline");
			scenario.write("Unable to check if none of the texts are highlighted in bold, italic or underline");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("The clear formating has not been proper");
	    	scenario.write("The clear formating has not been proper");
	    	throw e;
		}
	}
	
	@Then("^check if the Layout tab is not visible$")
	public void check_if_the_Layout_tab_is_not_visible() throws Throwable {
	    try {
			boolean boolFound = false;
			try {
				actions.waitUntilElementVisibleSmall(driver, adssRegressionSuitePgObjects.layoutTab);
				boolFound = true;
			} catch (NoSuchElementException | TimeoutException e) {
				boolFound = false;
			}
			Assert.assertEquals(false, boolFound);
			actions.takeScreenshot(driver);
			log.info("The Layout tab is not visible");
		} catch (Exception e) {
			log.error("Unable to check if the Layout tab is not visible");
			scenario.write("Unable to check if the Layout tab is not visible");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("The Layout tab is visible");
	    	scenario.write("The Layout tab is visible");
	    	throw e;
		}
	}
	
	@Then("^open a new incognito window$")
	public void open_a_new_incognito_window() throws Throwable {
	    try {
	    	ArrayList<String> arrayList = new ArrayList<>(driver.getWindowHandles());
			int nrOfWindowsBefore = arrayList.size();
			Thread.sleep(5000);
			System.out.println("Nr of windows before " + nrOfWindowsBefore);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String scr = "window.open('https://www.google.com','_blank');";
			js.executeScript(scr);
			ArrayList<String> newArrayList = new ArrayList<>(driver.getWindowHandles());
			int nrOfWindowsAfter = newArrayList.size();
			System.out.println("Nr of windows after " + nrOfWindowsAfter);
			try{
			Assert.assertEquals(1, (nrOfWindowsAfter-nrOfWindowsBefore));
			}
			catch(AssertionError e){
				Thread.sleep(5000);
				Assert.assertEquals(1, (nrOfWindowsAfter-nrOfWindowsBefore));
			}
			actions.takeScreenshot(driver);
			log.info("Able to open a new incognito window");
		} catch (Exception e) {
			log.error("Error in opening the new incognito window");
			scenario.write("Error in opening the new incognito window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to open the new incognito window");
	    	scenario.write("Unable to open the new incognito window");
	    	throw e;
		}
	}

	@Given("^login to the \"([^\"]*)\" page$")
	public void login_to_the_page(String urlToOpen) throws Throwable {
	    try {
			String url;
			String environment;
			try{
			environment = System.getProperty("environment");
			if(environment.trim().equalsIgnoreCase("qa")){
			url = "https://adssv2.tribqa.com/admin/" + urlToOpen;
			}
			else if (environment.trim().equalsIgnoreCase("stage")) {
				url = "https://adssv2.tribstage.com/admin/" + urlToOpen;
			}
			else {
				url = "https://adssv2.tribpub.com/admin/" + urlToOpen;
			}
			}
			catch(NullPointerException e){
				url = "https://adssv2.tribqa.com/admin/" + urlToOpen;
			}
			driver.get(url);
			try {
				waitForPageToLoad();
			} catch (WebDriverException e) {
				// do nothing
			}
			actions.takeScreenshot(driver);
			log.info("Able to login to the " + urlToOpen + " in the new incognito window");
		} catch (Exception e) {
			log.error("Unable to login to the " + urlToOpen + " in the new incognito window");
			scenario.write("Unable to login to the " + urlToOpen + " in the new incognito window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^enter new credentials in the windows authentication popup in the new incognito window$")
	public void enter_new_credentials_in_the_windows_authentication_popup_in_the_new_incognito_window() throws Throwable {
	    try {
	    	data = PropertiesFile.readInputPropertiesFile();
			String userIdToEnter = data.getProperty("usernameAdit2");
			String pwdToEnter = data.getProperty("passwordAdit2");
			actions.setClipboardData(userIdToEnter);
			actions.keyboardPaste();
			Thread.sleep(5000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			actions.setClipboardData(pwdToEnter);
			actions.keyboardPaste();
			Thread.sleep(5000);
			actions.keyboardEnter();
			actions.takeScreenshot(driver);
			log.info("Able to enter new credentials in the windows authentication popup in the new incognito window");
		} catch (Exception e) {
			log.error("Unable to enter new credentials in the windows authentication popup in the new incognito window");
			scenario.write("Unable to enter new credentials in the windows authentication popup in the new incognito window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the expand icon beside the \"([^\"]*)\" field in the Digital Settings page$")
	public void click_on_the_expand_icon_beside_the_field_in_the_Digital_Settings_page(String fieldName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.expandIconBesideMentionedFieldInDigitalSettingsPg(fieldName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the expand icon beside the " + fieldName + " field in the Digital Settings page");
		} catch (Exception e) {
			log.error("Unable to click on the expand icon beside the " + fieldName + " field in the Digital Settings page");
			scenario.write("Unable to click on the expand icon beside the " + fieldName + " field in the Digital Settings page");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the dropdown in the \"([^\"]*)\" section, &, choose value as \"([^\"]*)\"$")
	public void click_on_the_dropdown_in_the_section_choose_value_as(String sectionName, String dropdownVal) throws Throwable {
	    try {
	    	ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.dropdownFldInMentionedSectionInDigitalSettingsPgList(sectionName));
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 15000);
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.dropdownFldInMentionedSectionInDigitalSettingsPg(sectionName));
			//actions.selectElementFromDropdownByVisibleText(driver, adssRegressionSuitePgObjects.dropdownFldInMentionedSectionInDigitalSettingsPg(sectionName), dropdownVal);
			actions.click(adssRegressionSuitePgObjects.dropdownFldInMentionedSectionInDigitalSettingsPg(sectionName));
			Utils.selectDropdown(adssRegressionSuitePgObjects.dropdownFldInMentionedSectionInDigitalSettingsPg(sectionName),dropdownVal);
			actions.takeScreenshot(driver);
			log.info("Able to click on the dropdown in the " + sectionName + " section, & choose value as " + dropdownVal);
		} catch (Exception e) {
			log.error("Unable to choose value as " + dropdownVal + " in the dropdown in the " + sectionName + " section");
			scenario.write("Unable to choose value as " + dropdownVal + " in the dropdown in the " + sectionName + " section");
			throw e;
		}
	    catch (AssertionError e) {
			log.error(e);
			scenario.write(e.getMessage());
			throw e;
		}
	}
	
	@Then("^check the mentioned checkboxes if not checked$")
	public void check_the_mentioned_checkboxes_if_not_checked(List<String> checkboxes) throws Throwable {
	    try {
	    	SoftAssert softAssert = new SoftAssert();
			for(String checkbox: checkboxes){
				actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.checkboxesInDigitalSettingsPg(checkbox));
				if(adssRegressionSuitePgObjects.checkboxesInDigitalSettingsPg(checkbox).getAttribute("aria-checked").trim().equalsIgnoreCase("false")){
				actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.checkboxesInDigitalSettingsPg(checkbox));
				softAssert.assertTrue(adssRegressionSuitePgObjects.checkboxesInDigitalSettingsPg(checkbox).getAttribute("aria-checked").trim().equalsIgnoreCase("true"), checkbox + " checkbox is not checked");
				}
			}
			softAssert.assertAll();
			actions.takeScreenshot(driver);
			log.info("Able to check all of the mentioned checkboxes " + checkboxes + " if not checked");
		} catch (Exception e) {
			log.error("Error in checking if all the mentioned checkboxes " + checkboxes + " are not checked");
			scenario.write("Error in checking if all the mentioned checkboxes " + checkboxes + " are not checked");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to check all of the mentioned checkboxes " + checkboxes + " if not checked");
	    	scenario.write("Unable to check all of the mentioned checkboxes " + checkboxes + " if not checked");
	    	throw e;
		}
	}

	@Then("^clear the textareas beside the mentioned checkboxes$")
	public void clear_the_textareas_beside_the_mentioned_checkboxes(List<String> checkboxes) throws Throwable {
	    try {
	    	SoftAssert softAssert = new SoftAssert();
	    	for(String checkbox: checkboxes){
	    		actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.textAreaBesideMentionedCheckbox(checkbox));
	    		adssRegressionSuitePgObjects.textAreaBesideMentionedCheckbox(checkbox).clear();
	    		softAssert.assertEquals("", adssRegressionSuitePgObjects.textAreaBesideMentionedCheckbox(checkbox).getAttribute("value").trim());
	    	}
	    	softAssert.assertAll();
	    	actions.takeScreenshot(driver);
	    	log.info("Able to clear the textareas beside the mentioned checkboxes");
		} catch (Exception e) {
			log.error("Error in clearing all of the mentioned checkboxes " + checkboxes +  ", if not empty");
			scenario.write("Error in clearing all of the mentioned checkboxes " + checkboxes +  ", if not empty");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to clear all of the mentioned checkboxes " + checkboxes + ", if not empty");
	    	scenario.write("Unable to clear all of the mentioned checkboxes " + checkboxes + ", if not empty");
	    	throw e;
		}
	}

	@Then("^click on the \"([^\"]*)\" button in the Digital Settings page$")
	public void click_on_the_button_in_the_Digital_Settings_page(String btnName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.btnInDigitalSettingsPg(btnName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + btnName + " button in the Digital Settings page");
		} catch (Exception e) {
			log.error("Unable to click on the " + btnName + " button in the Digital Settings page");
			scenario.write("Unable to click on the " + btnName + " button in the Digital Settings page");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" button in the Confirmation popup in the Digital Settings page$")
	public void click_on_the_button_in_the_Confirmation_popup_in_the_Digital_Settings_page(String btnName) throws Throwable {
	    try {
	    	ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.btnInConfirmationPopupInDigitalSettingsPgList(btnName));
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 15000);
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.btnInConfirmationPopupInDigitalSettingsPg(btnName));
			actions.takeScreenshot(driver);
			log.info("Able to click on the " + btnName + " button in the Confirmation popup in the Digital Settings page");
		} catch (Exception e) {
			log.error("Unable to click on the " + btnName + " button in the Confirmation popup in the Digital Settings page");
			scenario.write("Unable to click on the " + btnName + " button in the Confirmation popup in the Digital Settings page");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^check if the mentioned symbols are present in the Template Page$")
	public void check_if_the_mentioned_symbols_are_present_in_the_Template_Page(List<String> symbolNames) throws Throwable {
	    try {
			for(String symbol: symbolNames){
				ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

					@Override
					public Boolean execute() {
						return actions.elementIfDisplayed(adssRegressionSuitePgObjects.symbolInTemplateDesignPgList(symbol));
					}
				};
				actions.waitUntilValueIsTrue(executeCommand, 10000);
				actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.symbolInTemplateDesignPg(symbol));
			}
			actions.takeScreenshot(driver);
			log.info("All the mentioned symbols " + symbolNames + " are present in the Template Page");
		} catch (Exception e) {
			log.error("Not all of the menitoned symbols " + symbolNames + " are present in the Template Page");
			scenario.write("Not all of the menitoned symbols " + symbolNames + " are present in the Template Page");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the Material Preview Image in the Insertions table$")
	public void click_on_the_Material_Preview_Image_in_the_Insertions_table() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.materialPreviewImgInInserttionsTbl);
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.materialReviewPopUpList);
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 10000);
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.materialReviewPopUp);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Material Preview Image in the Insertions table");
		} catch (Exception e) {
			log.error("Unable to click on the Material Preview Image in the Insertions table");
			scenario.write("Unable to click on the Material Preview Image in the Insertions table");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the Open In New Window button in the Material Review Pop Up$")
	public void click_on_the_Open_In_New_Window_button_in_the_Material_Review_Pop_Up() throws Throwable {
	    try {
	    	ArrayList<String> tabsBeforeClickingOnNewWindowBtn = new ArrayList<String>(driver.getWindowHandles());
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.openInNewWindowBtnInMaterialReviewPopUp);
			ArrayList<String> tabsAfterClickingOnNewWindowBtn = new ArrayList<String>(driver.getWindowHandles());
			Assert.assertEquals(1, (tabsAfterClickingOnNewWindowBtn.size()-tabsBeforeClickingOnNewWindowBtn.size()));
			actions.takeScreenshot(driver);
			log.info("Able to click on the Open In new Window button in the Maerial review Pop Up");
		} catch (Exception e) {
			log.error("Error in clicking on the Open In new Window button in the Maerial review Pop Up");
			scenario.write("Error in clicking on the Open In new Window button in the Maerial review Pop Up");
			throw e;
		}
	    catch (AssertionError e) {
			log.error("Unable to click on the Open In new Window button in the Maerial review Pop Up");
			scenario.write("Unable to click on the Open In new Window button in the Maerial review Pop Up");
			throw e;
		}
	}
	
	@Then("^click on the Edit link in the Material Order Id in the new window$")
	public void click_on_the_Edit_link_in_the_Material_Order_Id_in_the_new_window() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.editLinkInMaterialOrderId);
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.materialPreviewImgAfterClickingOnEditLinkList);
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 15000);
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.materialPreviewImgAfterClickingOnEditLink);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Edit link in the Material Order Id in the new window");
		} catch (Exception e) {
			log.error("Unable to click on the Edit link in the Material Order Id in the new window");
			scenario.write("Unable to click on the Edit link in the Material Order Id in the new window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the \"([^\"]*)\" button in the Edit Order Page in the new window$")
	public void click_on_the_button_in_the_Edit_Order_Page_in_the_new_window(String btnName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndJavascriptClk(driver, adssRegressionSuitePgObjects.btnInEditOrderPgInNewWindow(btnName));
			try {
				actions.takeScreenshot(driver);
			} catch (WebDriverException e) {
				// do nothing
			}
			log.info("Able to click on the " + btnName + " button in the Edit Order Page in the new window");
		} catch (Exception e) {
			log.error("Unable to click on the " + btnName + " button in the Edit Order Page in the new window");
			scenario.write("Unable to click on the " + btnName + " button in the Edit Order Page in the new window");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Given("^open the Adit URL$")
	public void open_the_Adit_URL() throws Throwable {
	    try {
	    	data = PropertiesFile.readInputPropertiesFile();
	    	String environment;
			try{
				environment= System.getProperty("environment");
				String aditUrl = "ADIT" + environment + "URL";
				driver.get(data.getProperty(aditUrl));
			}
			catch (NullPointerException e) {
				driver.get(data.getProperty("ADITQAURL"));
			}
			try {
				waitForPageToLoad();
			} catch (WebDriverException e) {
				// do nothing
			}
			actions.takeScreenshot(driver);
			log.info("Able to open the Adit URL");
		} catch (Exception e) {
			log.error("Unable to open the Adit URL");
			scenario.write("Unable to open the Adit URL");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the Product Admin Module$")
	public void click_on_the_Product_Admin_Module() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.prodAdminLink);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Product Admin Module");
		} catch (Exception e) {
			log.error("Unable to click on the Product Admin Module");
			scenario.write("Unable to click on the Product Admin Module");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^click on \"([^\"]*)\" from the list of available BUs$")
	public void click_on_from_the_list_of_available_BUs(String buName) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.buNameToClickInAdit(buName));
			actions.takeScreenshot(driver);
			log.info("Able to click on " + buName + " from the list of available BUs");
		} catch (Exception e) {
			log.error("Unable to click on " + buName + " from the list of available BUs");
			scenario.write("Unable to click on " + buName + " from the list of available BUs");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}

	@Then("^click on Auto Insertion Management tab from General Information Section in the BU$")
	public void click_on_Auto_Insertion_Management_tab_from_General_Information_Section_in_the_BU() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.AutoInsertionManagementTab);
			actions.takeScreenshot(driver);
			log.info("Able to click on Auto Insertion Management tab from General Information Section in the BU");
		} catch (Exception e) {
			log.error("Unable to click on Auto Insertion Management tab from General Information Section in the BU");
			scenario.write("Unable to click on Auto Insertion Management tab from General Information Section in the BU");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^check if the order Status is now showing as \"([^\"]*)\"$")
	public void check_if_the_order_Status_is_now_showing_as(String status) throws Throwable {
	    try {
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.orderStatusInAdit(status));
			actions.takeScreenshot(driver);
			log.info("The order Status is now showing as " + status);
		} catch (Exception e) {
			log.error("The order status is not showing as " + status);
			scenario.write("The order status is not showing as " + status);
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^in the Auto Insertion Management table, double click on the data having the \"([^\"]*)\" as \"([^\"]*)\", and, \"([^\"]*)\" as \"([^\"]*)\"$")
	public void in_the_Auto_Insertion_Management_table_double_click_on_the_data_having_the_as_and_as(String tblHeading1, String tblVal1, String tblHeading2, String tblVal2) throws Throwable {
	    try {
			ExecuteCommand<Boolean> executeCommand = new ExecuteCommand<Boolean>() {

				@Override
				public Boolean execute() {
					return actions.elementIfDisplayed(adssRegressionSuitePgObjects.tblDataInAutoInsertionMgmtTblList(tblHeading1, tblVal1, tblHeading2, tblVal2));
				}
			};
			actions.waitUntilValueIsTrue(executeCommand, 15000);
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.tblDataInAutoInsertionMgmtTbl(tblHeading1, tblVal1, tblHeading2, tblVal2));
			actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.tblDataInAutoInsertionMgmtTbl(tblHeading1, tblVal1, tblHeading2, tblVal2));
			action.moveToElement(adssRegressionSuitePgObjects.tblDataInAutoInsertionMgmtTbl(tblHeading1, tblVal1, tblHeading2, tblVal2)).doubleClick().build().perform();
			actions.waitForElementPresence(driver, adssRegressionSuitePgObjects.insertionPopup);
			actions.takeScreenshot(driver);
			log.info("Able to double click on the data having the " + tblHeading1 + " as " + tblVal1 + ", &, " + tblHeading2 + " as " + tblVal2);
		} catch (Exception e) {
			log.error("Unable to double click on the data having the " + tblHeading1 + " as " + tblVal1 + ", &, " + tblHeading2 + " as " + tblVal2);
			scenario.write("Unable to double click on the data having the " + tblHeading1 + " as " + tblVal1 + ", &, " + tblHeading2 + " as " + tblVal2);
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("No datas are present having the " + tblHeading1 + " as " + tblVal1 + ", &, " + tblHeading2 + " as " + tblVal2);
	    	scenario.write("No datas are present having the " + tblHeading1 + " as " + tblVal1 + ", &, " + tblHeading2 + " as " + tblVal2);
	    	throw e;
		}
	}
	
	@Then("^in the Update popup, select the Type as \"([^\"]*)\"$")
	public void in_the_Update_popup_select_the_Type_as(String dropdownOption) throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.typeInUpdatePopup);
			actions.selectElementFromDropdownByVisibleText(driver, adssRegressionSuitePgObjects.typeInUpdatePopup, dropdownOption);
			Assert.assertEquals(dropdownOption, actions.getCurrentValueFromDropdown(driver, adssRegressionSuitePgObjects.typeInUpdatePopup));
			actions.takeScreenshot(driver);
			log.info("Able to select the Type as " + dropdownOption + " in the Update popup");
		} catch (Exception e) {
			log.error("Error in selecting the Type as " + dropdownOption + " in the Update popup");
			scenario.write("Error in selecting the Type as " + dropdownOption + " in the Update popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error("Unable to select the Type as " + dropdownOption + " in the Update popup");
	    	scenario.write("Unable to select the Type as " + dropdownOption + " in the Update popup");
	    	throw e;
		}
	}
	
	@Then("^in the Update popup, select the Type as \"([^\"]*)\", and Save if not selected$")
	public void in_the_Update_popup_select_the_Type_as_and_Save_if_not_selected(String dropdownOption) throws Throwable {
	    try {
			if(!(actions.getCurrentValueFromDropdown(driver, adssRegressionSuitePgObjects.typeInUpdatePopup).trim().equals(dropdownOption))){
				in_the_Update_popup_select_the_Type_as(dropdownOption);
				actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.okBtnInUpdateInsertionPopup);
				actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.saveBtnInBUPgInAdit);
				wait_for_seconds(20);
			}
			actions.takeScreenshot(driver);
			log.info("Able to select the Type as " + dropdownOption + " in the Update popup, and Save if not selected");
		} catch (Exception e) {
			log.error("Unable to select the Type as " + dropdownOption + " in the Update popup, and Save if not selected");
			scenario.write("Error in selecting the Type as " + dropdownOption + " in the Update popup, and Save if not selected");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^click on the Approve button, followed by Confirm Approve button in the popup$")
	public void click_on_the_Approve_button_followed_by_Confirm_Approve_button_in_the_popup() throws Throwable {
	    try {
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.approveBtnInAdit);
			actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.approveOrderConfBtn);
			actions.takeScreenshot(driver);
			log.info("Able to click on the Approve button, followed by Confirm Approve button in the popup");
		} catch (Exception e) {
			log.error("Unable to click on the Approve button, followed by Confirm Approve button in the popup");
			scenario.write("Unable to click on the Approve button, followed by Confirm Approve button in the popup");
			throw e;
		}
	    catch (AssertionError e) {
	    	log.error(e);
	    	scenario.write(e.getMessage());
	    	throw e;
		}
	}
	
	@Then("^clear the text in the textarea paragraph field$")
	public void clear_the_text_in_the_textarea_paragraph_field() throws Throwable {
	    try {
	    	actions.waitForElementToBeClickableAndClick(driver, adssRegressionSuitePgObjects.CTCObitText);
	    	adssRegressionSuitePgObjects.CTCObitText.clear();
	    	Assert.assertEquals("", adssRegressionSuitePgObjects.CTCObitText.getText().trim());
	    	actions.takeScreenshot(driver);
	    	log.info("Able to clear the text in the textarea paragraph field");
		} catch (Exception e) {
			log.error("Error in clearing the text in the textarea paragraph field");
			scenario.write("Error in clearing the text in the textarea paragraph field");
			throw e;
		}
	    catch (AssertionError e) {
			log.error("Unable to clear the text in the textarea paragraph field");
			scenario.write("Unable to clear the text in the textarea paragraph field");
			throw e;
		}
	}
	//Partha
	@Then("^get the total price adit$")
	public void get_the_total_price_adit() throws Throwable {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", adssRegressionSuitePgObjects.aditOrderPrice);
			String aditOrderPrice =  adssRegressionSuitePgObjects.aditOrderPrice.getText();
			try{
			TotalPriceAdit="$"+String.valueOf(Double.parseDouble(Utils.getPriceValue(aditOrderPrice)));
			}
			catch(NumberFormatException e){
				TotalPriceAdit="$"+String.valueOf((NumberFormat.getNumberInstance(java.util.Locale.US).parse(Utils.getPriceValue(aditOrderPrice))).doubleValue());
			}
			
		} catch (Exception e) {
			log.error("Unable to check ADIT total price");
			scenario.write("Unable to check ADIT total price");
			throw e;
		}
	}
	//Partha
		@Then("^Kill Order from Adit$")
		public void KillOrder_from_Adit() throws Throwable {
			try {
				actions.click(adssRegressionSuitePgObjects.moreActionsinAditOrder);
				Thread.sleep(2000);
				actions.click(adssRegressionSuitePgObjects.KillLinkInAditOrder);
				Thread.sleep(3000);
				actions.click(adssRegressionSuitePgObjects.killOrderButtonOnConformation);
				Thread.sleep(15000);
				driver.navigate().refresh();
				Thread.sleep(3000);
				String orderStatus=adssRegressionSuitePgObjects.OrderStatusinAdit.getText();
				if(orderStatus.contains("Killed ")) {
					log.info("order killed successfully in adit");
					scenario.write("order killed successfully in adit");
				}
				else
				{	
					log.info("order not killed successfully in adit");
					scenario.write("order not killed successfully in adit");
				}
				actions.takeScreenshot(driver);
				}
				
			 catch (Exception e) {
				log.error("Unable to kill order in adit");
				scenario.write("Unable to Kill order in adit");
				throw e;
			}
	}
		
		
		@Then("^Search For the Relative product group for product group \"([^\"]*)\" in Adit$")
		public void searchForRelativeProductGroupinAdit(String Product_Type) throws Throwable {
			
			data = PropertiesFile.readInputPropertiesFile();
			String BusinessUnitForRelativeProduct1 = null, RelativeproductGroupName1;
			actions.scrollToElement(driver,adssRegressionSuitePgObjects.generalSettingInAdit);
			Thread.sleep(2000);
			actions.click(adssRegressionSuitePgObjects.generalSettingInAdit);
			Thread.sleep(2000);
			actions.scrollToElement(driver,adssRegressionSuitePgObjects.generalLinkInAdit);
			Thread.sleep(2000);
			actions.click(adssRegressionSuitePgObjects.generalLinkInAdit);
			Thread.sleep(2000);
			actions.scrollToElement(driver,adssRegressionSuitePgObjects.relativeProductGroupLinkInAdit);
			Thread.sleep(2000);
			actions.click(adssRegressionSuitePgObjects.relativeProductGroupLinkInAdit);
			Thread.sleep(2000);
			if(Product_Type.equalsIgnoreCase("display")) {
			BusinessUnitForRelativeProduct1 = data.getProperty("BusinessUnitForRelativeProductDisplay");
			log.info("business unit is:"+BusinessUnitForRelativeProduct1);
			System.out.println("business unit is:"+BusinessUnitForRelativeProduct1);
			RelativeproductGroupName1=data.getProperty("RelativeproductGroupNameDisplay");
			}
			else
			{
			BusinessUnitForRelativeProduct1 = data.getProperty("BusinessUnitForRelativeProductOnline");
			RelativeproductGroupName1=data.getProperty("RelativeproductGroupNameOnline");
				
			}
			System.out.println("BusinessUnitForRelativeProduct:"+BusinessUnitForRelativeProduct1);
			
			//Utils.selectDropdown(adssRegressionSuitePgObjects.BusinessUnitDropdownInAdit, BusinessUnitForRelativeProduct1);
			actions.click(adssRegressionSuitePgObjects.BusinessUnitDropdownInAdit);
			Thread.sleep(1000);
			String buvaluexpath="//select[@id='BusinessUnitId']/option[contains(text(),'"+BusinessUnitForRelativeProduct1+"')]";
			actions.click(driver.findElement(By.xpath(buvaluexpath)));
			Thread.sleep(1000);
			//Utils.selectDropdown(adssRegressionSuitePgObjects.ProductTypeDropdowninRelativeProductsGroupPage, Product_Type);
			actions.click(adssRegressionSuitePgObjects.ProductTypeDropdowninRelativeProductsGroupPage);
			Thread.sleep(1000);
			String ProductTypevaluexpath="//select[@id='productTypeId']/option[contains(text(),'"+Product_Type+"')]";
			actions.click(driver.findElement(By.xpath(ProductTypevaluexpath)));
			Thread.sleep(1000);
			System.out.println("RelativeproductGroupName1:"+RelativeproductGroupName1);
			actions.click(adssRegressionSuitePgObjects.editLinkforRelativeProductGroup(RelativeproductGroupName1));

		}
		
			
		
		@Then("^copy the query String of product \"([^\"]*)\" in relative product group$")
		public void copy_the_query_String_of_product_in_relative_product_group(String Product_Type) throws Throwable {
			
			String querystringxpath = null;
			try {
				Thread.sleep(3000);
			//if(Product_Type.equalsIgnoreCase("display"))
			//querystringxpath="//html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[12]/div[1]";
				querystringxpath="(//div[contains(text(),'[0].sectionid')])[1]";
				
			queryString=driver.findElement(By.xpath(querystringxpath)).getText();
			Thread.sleep(2000);
			
			scenario.write("Able to read quesry string:"+queryString);
		}
			catch(Exception e) {
				log.error("Unable to read quesry string");
				scenario.write("Unable to read quesry string");
				throw e;
				
			}
		}
		@Then("^copy the query Strings of relative products for product type \"([^\"]*)\" in relative product group$")
		public void copy_the_query_Strings_of_relative_products_for_product_type (String Product_Type) throws Throwable {
			
			String querystringxpath = null;
			try {
				Thread.sleep(3000);
			//if(Product_Type.equalsIgnoreCase("display"))
			//querystringxpath="//html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[2]/div[2]/div[1]/div[*]/div[1]/div[1]/div[12]/div[1]";
				querystringxpath="//div[contains(text(),'[0].sectionid')]";
				
				List<WebElement> quersyStrings = driver.findElements(By.xpath(querystringxpath));
			
			for(int i=1; i< quersyStrings.size(); i++){
				WebElement element = quersyStrings.get(i);
				String quesryString = element.getText();
				RelativeproductsInAdit.add(i-1, quesryString);
			}
			for(int i=1; i< quersyStrings.size(); i++){
			scenario.write("query String:"+RelativeproductsInAdit.get(i-1));
			}
		
			
		}
			catch(Exception e) {
				log.error("Unable to read quesry string");
				scenario.write("Unable to read quesry string");
				throw e;
				
			}
		}
		@Then("^Open the Related Product QueryString URL in ADSS for \"([^\"]*)\" view$")
		public void Open_the_Related_Product_QueryString_URL_in_ADSS(String View) throws Throwable {
			
				String environment;
				String ViewURL;
				String urlToTest;
				data = PropertiesFile.readInputPropertiesFile();
				try {
					Thread.sleep(2000);
					environment = System.getProperty("environment");
					ViewURL = View + environment + "URLINITPART";
					
					try {
					//driver.get(data.getProperty(ViewURL));
						scenario.write("view url valuee:"+data.getProperty(ViewURL));
					urlToTest=data.getProperty(ViewURL)+queryString;
					driver.get(urlToTest);
					Thread.sleep(2000);
					driver.navigate().refresh();
					Thread.sleep(6000);
					
					}
					catch (Exception e) {
						environment = "QA";
						ViewURL = View + environment + "URLINITPART";
						
						urlToTest=data.getProperty(ViewURL)+queryString;
						scenario.write(" url value:"+urlToTest);
						driver.get(urlToTest);
						Thread.sleep(2000);
						driver.navigate().refresh();
						Thread.sleep(6000);
					}
					Thread.sleep(12000);
					waitForPageToLoad();
				actions.takeScreenshot(driver);
				log.info("Able to open the Relative product query string URL");
			} catch (Exception e) {
				log.error("Unable to open the Relative product query string URL");
				scenario.write("Unable to open the Relative product query string URL");
				throw e;
			}
		}
		@Then("^Verify all the relative product displayed in ADSS$")
		public void Verify_all_the_relative_product_displayed_in_ADSS() throws Throwable {
			
			String titleXpathinADSSRelativeProduct = "//ngu-tile[*]/div[1]/div[1]/div[1]/div[1]//img";
			try {
				Thread.sleep(3000);
				int count=0;
				//actions.scrollToElement(driver, driver.findElement(By.xpath("//ngu-tile[1]/div[1]/div[1]/div[1]/div[1]//img")));
			List<WebElement> TitlesinADSSRElativeProduct = driver.findElements(By.xpath(titleXpathinADSSRelativeProduct));
			scenario.write("no of relative product in adss:"+TitlesinADSSRElativeProduct.size());
			for(int i=0; i< RelativeproductsInAdit.size(); i++){
				
				
				boolean QueryStringFound=false;
				String QueryStringInADSSProductTitle = RelativeproductsInAdit.get(i);
				scenario.write("QueryStringInADSSProductTitle"+QueryStringInADSSProductTitle);
				for (int k=0;k<TitlesinADSSRElativeProduct.size();k++)
				{
					if(TitlesinADSSRElativeProduct.get(k).getAttribute("title").equalsIgnoreCase(QueryStringInADSSProductTitle)) {
						QueryStringFound=true;
						count++;
						break;
					}
				}
				
			}
			
			Assert.assertEquals(count, RelativeproductsInAdit.size());
			
			
			
		}
			catch(Exception e) {
				log.error("all realtive product not displayed in ADSS");
				scenario.write("all realtive product not displayed in ADSS");
				throw e;
				
			}
		}
		@Then("^Add or remove product (\\d+) from relative product$")
		public void Add_product_from_relative_product(int productNo) throws Throwable {
			
			WebElement addbuttonxpathRelativeProduct = adssRegressionSuitePgObjects.AddRemovebuutonLinkForRelativeProduct(productNo);
			
			try {
				Thread.sleep(2000);
				int count=0;
				actions.scrollToElement(driver, addbuttonxpathRelativeProduct);
				Thread.sleep(2000);
				actions.click(addbuttonxpathRelativeProduct);
				Thread.sleep(10000);
				scenario.write("able to click on add/Remove button for product no:"+productNo);
			
			}
			catch(Exception e) {
				log.error("unable to click on add button for product no:"+productNo);
				scenario.write("unable to click on add/Remove button for product no:"+productNo);
				throw e;
				
			}
		}
		@Then("^verify button Value changed to \"([^\"]*)\" for product (\\d+) after addition$")
		public void verify_Add_Value_changed_to_Remove_for_product_after_addition(String value,int productNo) throws Throwable {
			
			WebElement addbuttonxpathRelativeProduct = adssRegressionSuitePgObjects.AddRemovebuutonLinkForRelativeProduct(productNo);
			
			try {
				Thread.sleep(2000);
				int count=0;
				actions.scrollToElement(driver, addbuttonxpathRelativeProduct);
				Thread.sleep(2000);
				String buttonValue=addbuttonxpathRelativeProduct.getText();
				Assert.assertEquals(buttonValue, value);
				scenario.write("Button value cahneged to"+value);
			}
			catch(Exception e) {
				log.error("Button value not cahneged to"+value);
				scenario.write("Button value not cahneged to"+value);
				throw e;
				
			}
		}
		@Then("^verify header value of product count displayed (\\d+) of (\\d+) in relative product$")
		public void verify_header_value_of_product_count_displayed(int no1,int no2) throws Throwable {
			
			String headerCountValue = null,headerCountValueExpected;
			
			try {
				actions.scrollToElement(driver, adssRegressionSuitePgObjects.countHeaderInmultipleProduct);
				Thread.sleep(2000);
				 headerCountValue=adssRegressionSuitePgObjects.countHeaderInmultipleProduct.getText();
				 headerCountValueExpected=no1+" of "+no2;
				
				Assert.assertEquals(headerCountValueExpected, headerCountValue);
				scenario.write("Header count is showing as:"+headerCountValue+" as expected");
			
			}
			catch(Exception e) {
				log.error("Header count is showing as:"+headerCountValue+" which is not showing as expected");
				scenario.write("Header count is showing as:"+headerCountValue+" which is not showing as expected");
				throw e;
				
			}
		}
		@Then("^click Continue on Delete product in multiple product page$")
		public void click_Continue_on_Delete_product_in_multiple_product_page() throws Throwable {
					
			try {
				
				actions.click(adssRegressionSuitePgObjects.ContinueButtonOnDeleteproduct);
				Thread.sleep(2000);
				scenario.write("Clicked on Continue button for delete product");
				
			}
			catch(Exception e) {
				log.error("not able to click on Continue button for delete product");
				scenario.write("not able to click on Continue button for delete product");
				throw e;
				
			}
		}
		@Then("^click on Next product product in multiple product page$")
		public void click_Next_product_on_Delete_product_in_multiple_product_page() throws Throwable {
					
			try {
				//actions.scrollToElement(driver, adssRegressionSuitePgObjects.ViewHomeLogo);
				Thread.sleep(2000);
				actions.click(adssRegressionSuitePgObjects.NextProductButton);
				Thread.sleep(2000);
				scenario.write("Clicked on Next Product button on the multiple product page");
				
			}
			catch(Exception e) {
				log.error("not able to click on Next Product button on the multiple product page");
				scenario.write("not able to click on Next Product button on the multiple product page");
				throw e;
				
			}
		}
		
		@Then("^click on Previous product product in multiple product page$")
		public void click_Previous_product_on_Delete_product_in_multiple_product_page() throws Throwable {
					
			try {
				//actions.scrollToElement(driver, adssRegressionSuitePgObjects.ViewHomeLogo);
				Thread.sleep(2000);
				actions.click(adssRegressionSuitePgObjects.PreviousProductButton);
				Thread.sleep(2000);
				scenario.write("Clicked on Next Product button on the multiple product page");
				
			}
			catch(Exception e) {
				log.error("not able to click on Next Product button on the multiple product page");
				scenario.write("not able to click on Next Product button on the multiple product page");
				throw e;
				
			}
		}
		@Then("^Refresh the Page$")
		public void Refresh_the_Page() throws Throwable {
					
			try {
				driver.navigate().refresh();
				Thread.sleep(4000);
				
			}
			catch(Exception e) {
				
				scenario.write("error in page refresh");
				throw e;
				
			}
		}
		
		
		@Then("^click on the Schedule tab (\\d+) in multiple product$")
		public void click_on_the_Schedule_tab_in_multiple_product(int productno) throws Throwable {
			try {
				Thread.sleep(10000);
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.scheduleTabInmultipleProduct(productno));
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.scheduleTabInmultipleProduct(productno));
				
				actions.click(adssRegressionSuitePgObjects.scheduleTabInmultipleProduct(productno));
				actions.takeScreenshot(driver);
				log.info("Able to click on the Schedule Tab for product "+productno);
			} catch (Exception e) {
				log.error("Unable to click on the Schedule Tab for product"+productno);
				scenario.write("Unable to click on the Schedule Tab for product"+productno);
				throw e;
			}
		}
		@Then("^Scroll to Progress summary$")
		public void Scroll_to_Progress_summary() throws Throwable {
			try {
				
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.ProgressSummary);
				actions.scrollUp(driver);
				//actions.scrollToElement(driver, adssRegressionSuitePgObjects.scheduleTab);
				Thread.sleep(2000);
			} catch (Exception e) {
				log.error("Unable to scrool to the ProgressSummary Tab");
				scenario.write("Unable to scrool to the ProgressSummary Tab");
				throw e;
			}
		}
		@Then("^click on the Layout tab (\\d+) in multiple product$")
		public void click_on_the_Layout_tab_in_multiple_product(int productno) throws Throwable {
			try {
				Thread.sleep(10000);
				actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.layoutTabInmultipleProduct(productno));
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.layoutTabInmultipleProduct(productno));
				
				actions.click(adssRegressionSuitePgObjects.layoutTabInmultipleProduct(productno));
				Thread.sleep(2000);
				actions.takeScreenshot(driver);
				log.info("Able to click on the Layout Tab for product "+productno);
			} catch (Exception e) {
				log.error("Unable to click on the Layout Tab for product"+productno);
				scenario.write("Unable to click on the Layout Tab for product"+productno);
				throw e;
			}
		}
		@Then("^verify and configure material in multiple product$")
		public void verify_and_configure_material_in_multiple_product() throws Throwable {
			try {
				if(driver.findElements(By.xpath("//input[contains(@id,'mat-input-')]")).size()>0)
				{
					driver.findElement(By.xpath("//input[contains(@id,'mat-input-')]")).sendKeys("google.com");
				}
				
				if(driver.findElements(By.xpath("//mat-icon[contains(text(),'fiber_new')]")).size()==0)
				{
					actions.click(adssRegressionSuitePgObjects.uploadButton);
					log.info("Able to click on upload button");
					adssRegressionSuitePgObjects.uploadLink.sendKeys(System.getProperty("user.dir") + "\\Images\\"+Utils.pickRandomIamge1());
					log.info("Able to upload photo");
					actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.saveImage);
					actions.click(adssRegressionSuitePgObjects.saveImage);
					Thread.sleep(3000);					
					log.info("Able to click on Accept and Continue button");
					scenario.write("material configuration done");
				}
				{
					log.info("Material already configured no need to edit");
				}
				
			} catch (Exception e) {
				log.error("Unable to configure material for multiple product");
				scenario.write("Unable to configure material for multiple product");
				throw e;
			}
		}
		@Then("^sort by product in relative product group$")
		public void sort_by_product_in_relative_product_group() throws Throwable {
			try {
				
				actions.click(adssRegressionSuitePgObjects.ProductTypeColumn);
				Thread.sleep(3000);
				log.info("able to sort by product type");
				scenario.write("able to sort by product type");
				
			} catch (Exception e) {
				log.error("Unable to srt by product type");
				scenario.write("Unable to srt by product type");
				throw e;
			}
		}
		
		@Then("^Type Click through url for online product$")
		public void Type_Click_through_url_for_online_product() throws Throwable {
			try {
				
				actions.scrollToElement(driver, adssRegressionSuitePgObjects.ClickThroughURL);
				Thread.sleep(2000);
				adssRegressionSuitePgObjects.ClickThroughURL.sendKeys("google.com");
				
				
				Thread.sleep(1000);
				log.info("Click through url entered");
				scenario.write("Click through url entered");
				
			} catch (Exception e) {
				log.error("Unable to enter click through url");
				scenario.write("Unable to enter click through url");
				throw e;
			}
		}
		@Then("Approve the order$")
		public void Approve_the_order() throws Throwable {
			
				
				clickApproveButton();
				log.info("Approve Button Clicked");
				clickConfirmApproveButton();
				log.info("Confirm-Approve Button Clicked");

				driver.navigate().back();

				String orderStatus=checkOrderStatus();
				try{
				Assert.assertEquals(orderStatus,"Processed");
				}
				catch (AssertionError e) {
					Thread.sleep(40000);
					driver.navigate().refresh();
					search_for_the_same_Order_Number_in_Adit();
					orderStatus=checkOrderStatus();
					Assert.assertEquals(orderStatus,"Processed");
				}
				scenario.write("Order Status Changed to Processed");
				log.info("Order Status Changed to Processed");
					
			
		}
		//Preetham
		@Then("^Select the \"([^\"]*)\" town name$")
	    public void select_the_something_town_name(String townname) throws Throwable {
			try{
				Thread.sleep(1000);
				Utils.selectDropdown(adssRegressionSuitePgObjects.SelectTown(townname),townname);
				actions.takeScreenshot(driver);
				log.info("Able to select townname as " + townname);
			}
			catch (Exception e) {
				log.error("Unable to select townname as " + townname);
				scenario.write("Unable to select category as " + townname);
				throw e; 
			}
		
	    }
		//Preetham K p
		@Then("^select the template$")
	    public void select_the_template() throws Throwable {
	        try {
	        	actions.waitUntilElementVisible(driver,adssRegressionSuitePgObjects.template);
	        	actions.waitUntilElementClickable(driver,adssRegressionSuitePgObjects.template);
	        	adssRegressionSuitePgObjects.template.click();
	        	Thread.sleep(2000);
	        	waitForPageToLoad();
	        	actions.takeScreenshot(driver);
	        	log.info("Able to select the template");
	        }
	        catch (Exception e) {
	        	log.error("Unable to select the template");
	        	scenario.write("Unable to select the template");
	        }
	    }
		//Preetham K P
		@Then("^select the second template$")
	    public void select_the_second_template() throws Throwable {
	        try {
	        	actions.waitUntilElementVisible(driver,adssRegressionSuitePgObjects.templatetwo);
	        	actions.waitUntilElementClickable(driver,adssRegressionSuitePgObjects.templatetwo);
	        	adssRegressionSuitePgObjects.templatetwo.click();
	        	Thread.sleep(2000);
	        	waitForPageToLoad();
	        	actions.takeScreenshot(driver);
	        	log.info("Able to select the  second template");
	        }
	        catch (Exception e) {
	        	log.error("Unable to select the second template");
	        	scenario.write("Unable to select the second template");
	        	
	        }
	    }
		//Preetham K P

	    @Then("^fill in the text sscst$")
	    public void fill_in_the_text_sscst() throws Throwable {
	    	try {
	    		Utils.fillText(adssRegressionSuitePgObjects.HeadLine, Utils.generateRandomString(10));
	    		Thread.sleep(2000);
	    		Utils.fillText(adssRegressionSuitePgObjects.Bodycopy1,Utils.generateRandomString(20));
	    		Thread.sleep(2000);
	    		Utils.fillText(adssRegressionSuitePgObjects.CompanyNameSSCST, Utils.generateRandomString(7));
	    		Thread.sleep(2000);
	    		Utils.fillText(adssRegressionSuitePgObjects.Addresssscst, Utils.generateRandomAlphaNumString(20));
	    		Thread.sleep(2000);
	    		Utils.fillText(adssRegressionSuitePgObjects.PhoneNumber, "9903463256");
	    		Thread.sleep(2000);
	    		Utils.fillText(adssRegressionSuitePgObjects.WebAddressSSCST, "https://www."+Utils.generateRandomString(7)+".com");
	    		Thread.sleep(2000);
	    		actions.takeScreenshot(driver);
				log.info("Able to fill in the text sscst");
			} catch (Exception e) {
				log.error("Unable to fill in the text sscst");
				scenario.write("Unable to fill in the text sscst");
				throw e;
	    	}
	        
	    }
	    //Preetham K P
	    @Then("^click on save and continue button$")
	    public void click_on_save_and_continue_button() throws Throwable {
	        try {
	        	Thread.sleep(7000);
	        	actions.waitUntilElementVisible(driver, adssRegressionSuitePgObjects.SaveandContinueButton);
				actions.waitUntilElementClickable(driver, adssRegressionSuitePgObjects.SaveandContinueButton);
				Thread.sleep(5000);
				actions.click(adssRegressionSuitePgObjects.SaveandContinueButton);
				Thread.sleep(10000);
				try
				{
					Thread.sleep(10000);
					//actions.click(adssRegressionSuitePgObjects.SaveandContinueButton);
				}
				catch(Exception e)
				{

				}
				waitForPageToLoad();
				actions.takeScreenshot(driver);
				log.info("Able to click on Save and Continue button");
			} catch (Exception e) {
				log.error("Unable to click on Save and Continue button");
				scenario.write("Unable to click on Save and Continue button");
				throw e;
	        	
	        }
	    }
	    //preetham K P
	    @Then("^fill in deatils for second template sscst$")
	    public void fill_in_deatils_for_second_template_sscst() throws Throwable {
	        try {
	        	Utils.fillText(adssRegressionSuitePgObjects.HeadLine, Utils.generateRandomString(10));
	        	Thread.sleep(2000);
	        	Utils.fillText(adssRegressionSuitePgObjects.BodyCopy, Utils.generateRandomString(10));
	        	Thread.sleep(2000);
	        	Utils.fillText(adssRegressionSuitePgObjects.CompanyName, Utils.generateRandomString(7));
	        	Thread.sleep(2000);
	        	Utils.fillText(adssRegressionSuitePgObjects.Addresssscst, Utils.generateRandomAlphaNumString(20));
	        	Thread.sleep(2000);
	        	Utils.fillText(adssRegressionSuitePgObjects.PhoneNumberInDesignYourMaterialPg, "9903463256");
	        	Thread.sleep(2000);
	        	Utils.fillText(adssRegressionSuitePgObjects.WebAddress, "https://www."+Utils.generateRandomString(7)+".com");
				Thread.sleep(2000);
				actions.takeScreenshot(driver);
				log.info("Able to fill in the text ctc");
			} catch (Exception e) {
				log.error("Unable to fill in the text ctc");
				scenario.write("Unable to fill in the text ctc");
				throw e;
	        	
	        }
	    }
	    //Preetham K p
	    @Then("^apply the coupon button$")
	    public void apply_the_coupon_button() throws Throwable {
	        try {
	        	actions.waitForElementToBeClickableAndClick(driver,adssRegressionSuitePgObjects.Applybutton );
	        	Thread.sleep(2000);
	        	actions.takeScreenshot(driver);
	        	log.info("Able to click on Apply  button");
	        }catch(Exception e) {
	        	log.error("Unable to click on Apply button");
				scenario.write("Unable to click on Apply button");
				throw e;
	        }
	    }
	    //Preetham K P
	    @Then("^fill in the textCTC$")
	    public void fill_in_the_text_secondsscst() throws Throwable {
	        try {
	        	Utils.fillText(adssRegressionSuitePgObjects.HeadLine, Utils.generateRandomString(10));
				Utils.fillText(adssRegressionSuitePgObjects.CompanyName, Utils.generateRandomString(7));
				Utils.fillText(adssRegressionSuitePgObjects.PhoneNumberInDesignYourMaterialPg, "9903463256");
				Utils.fillText(adssRegressionSuitePgObjects.WebAddress, "https://www."+Utils.generateRandomString(7)+".com");
				actions.takeScreenshot(driver);
				log.info("Able to fill in the text CTC");
			} catch (Exception e) {
				log.error("Unable to fill in the text CTC");
				scenario.write("Unable to fill in the text CTC");
				throw e;
	        	
	        }
	    }
	    


}
	
	
