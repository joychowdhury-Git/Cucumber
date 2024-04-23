package com.automation.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.interfaces.ExecuteCommand;
import com.automation.test.SetUp;
import com.automation.utilities.GlobalVars;

public class ActionMethods {

	public void waitUntilElementVisible(WebDriver driver, WebElement element){
		//	Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(30)).ignoring(StaleElementReferenceException.class);
		Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(30)).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitUntilListOfElementsVisible(WebDriver driver, List<WebElement> elementList){
		Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(30)).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
}
	
	public void waitUntilElementClickable(WebDriver driver, WebElement element){
			Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(30)).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitUntilElementVisibleSmall(WebDriver driver, WebElement element){
		Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
}
	
	public void type(WebElement element, Object str){
		element.click();
		element.sendKeys(str.toString());
	}
	
	public void click(WebElement element){
		element.click();
	}
	
	public void setClipboardData(String str){
		StringSelection stringSelection = new StringSelection(str);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	public void selectElementFromDropdownByVisibleText(WebDriver driver, WebElement element, String visibleText){
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(visibleText);
	}

	public void scrollDown(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollUp(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-600)", "");
	}
	
	public void highlightElement(WebDriver driver, WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow; border: 2px solid red')", element);
	}
	
	public void checkBrokenLinks(WebDriver driver) throws IOException{
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(int i=0; i< links.size(); i++){
			WebElement element = links.get(i);
			String url = element.getAttribute("href");
			verifyActiveLink(url);
		}
	}

	public void verifyActiveLink(String linkUrl) throws IOException {
		URL url = new URL(linkUrl);
		HttpURLConnection httpConnect = (HttpURLConnection) url.openConnection();
		httpConnect.setConnectTimeout(3000);
		httpConnect.connect();
		if(httpConnect.getResponseCode()==200){
			System.out.println(linkUrl + "_" + httpConnect.getResponseMessage());
		}
		else if (httpConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND) {
			System.out.println(linkUrl + "_" + httpConnect.getResponseMessage() + HttpURLConnection.HTTP_NOT_FOUND);
		}
		else {
			System.out.println("Invalid Connection");
		}
	}
	
	public void takeScreenshot(WebDriver driver) throws IOException{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\Screenshots\\" + SetUp.scenarioName + "\\" + System.currentTimeMillis() + ".png"));
	}
	
	
	public List<WebElement> getValuesFromDropdown(WebDriver driver, WebElement element){
		Select dropdown = new Select(element);
		List<WebElement> values  = dropdown.getOptions();
		return values;
	}
	
	public String getCurrentValueFromDropdown(WebDriver driver, WebElement element){
		Select select = new Select(element);
		String dropdownSelectedVal = select.getFirstSelectedOption().getText();
		return dropdownSelectedVal;
	}
	
	public void scrollToElement(WebDriver driver, WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public void switchFramesbyWebElement(WebDriver driver, WebElement element){
		driver.switchTo().frame(element);
	}
	
	public void switchToDefaultFrame(WebDriver driver){
		driver.switchTo().defaultContent();
	}

	public ArrayList<String> getRunDatesPrintAdOrderLine(int index, List<WebElement> orderDate) {
		ArrayList<String> RunDates = new ArrayList<String>();
		int numberofRunDates = orderDate.size();

		for(int i=0;i<numberofRunDates;i++)
			RunDates.add(Utils.getExactRunDate(orderDate.get(i).getText().trim()));


		System.out.println(RunDates.get(RunDates.size()-1));
		return RunDates;
	}

	public void javascriptClk(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public ArrayList<String> getRunDatesOnlineAdOrderLine(int index, List<WebElement> orderDateOnline) {
		ArrayList<String> RunDates = new ArrayList<String>();
		int numberofRunDates = orderDateOnline.size();

		for(int i=0;i<numberofRunDates;i++)
			RunDates.add(Utils.getExactOnlineRunDate(orderDateOnline.get(i).getText()));

		System.out.println(RunDates.get(RunDates.size()-1));
		return RunDates;
	}

	public Boolean elementIfDisplayed(List<WebElement> webElement) {
		boolean boolVal = webElement.size() >0 ? true : false;
		return boolVal;
	}
	
	public Boolean elementIfClickable(WebElement webElement) {
		boolean boolVal = false;
		try {
			webElement.click();
			boolVal = true;
		} catch (ElementClickInterceptedException e) {
			boolVal = false;
		}
		return boolVal;
	}

	public void waitUntilValueIsTrue(ExecuteCommand<Boolean> executeCommand, long i) throws InterruptedException {
		Boolean boolVal = executeCommand.execute();
		long constTime = System.currentTimeMillis();
		while((constTime + i) > System.currentTimeMillis()){
			if(!boolVal){
				Thread.sleep(1000);
			}
			else {
				break;
			}
		}
	}
	
	public void waitForElementPresence(WebDriver driver, WebElement element){
		waitUntilElementVisible(driver, element);
		scrollToElement(driver, element);
	}
	
	public void waitForElementToBeClickableAndClick(WebDriver driver, WebElement element){
		waitUntilElementVisible(driver, element);
		scrollToElement(driver, element);
		element.click();
	}
	
	public void waitForElementToBeClickableAndJavascriptClk(WebDriver driver, WebElement element){
		waitUntilElementVisible(driver, element);
		scrollToElement(driver, element);
		javascriptClk(driver, element);
	}

	public void javascriptType(WebDriver driver, WebElement webElement, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + text + "'", webElement);
	}

	public void keyboardPaste() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void keyboardEnter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void openNewIncognitoWindow() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	
}
