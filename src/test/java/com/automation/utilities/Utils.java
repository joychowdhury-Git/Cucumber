package com.automation.utilities;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utils {

	public final static String adit2Username="";
	public final static String adit2Password="";
	public final static String baseString="Lorem ipsum dolor sit amet, consectetur adipiscing elit."
			+ " Aenean sodales efficitur tristique. Sed ut dapibus libero. Donec vehicula erat vel eros vehicula dignissim."
			+ " Nam vel neque sem. Sed placerat congue sem semper viverra. In cursus fringilla enim, id viverra turpis dignissim ac."
			+ " Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Cras lobortis a nulla dapibus bibendum."
			+ " Cras dictum neque ut viverra faucibus.";
	
	//@Step("Get Dummy Text Paragraph")
	public static final String getParagraph()
	{
		return baseString;
	}
	
	//@Step("Get Draft Id")
	public static final String getDraftId(String url)
	{
		url=url.substring(url.indexOf("drafts/")+7);
		url=url.substring(0, url.indexOf("/"));
		
		return url;
	}
	
	//@Step("Get LOC Price")
	public static Double getLOCprice(Double price)
	{
		price=price-(price*0.15);
		DecimalFormat df=new DecimalFormat("0.00");
		String formate = df.format(price);
		double finalValue=0;
		try {
			finalValue = df.parse(formate).doubleValue() ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalValue;
	}
	
	//@Step("Check ADSS-Order IsApprove Criteria")
	public static boolean checkIsApprove(Double price)
	{
		if(price<=1.00)	
			return true;
		
		return false;
	}
	
	//@Step("Get Price Value")
	public static String getPriceValue(String price)
	{
		return price.substring(price.indexOf("$")+1);
	}
	
	//@Step("Get Price Formatted Value")
	public static Double getPriceFormattedValue(Double price)
	{
		DecimalFormat df=new DecimalFormat("0.00");
		String formate = df.format(price); 
		double finalValue=0;
		try {
			finalValue = (Double)df.parse(formate) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalValue;
	}
	
	//@Step("Get Exact Ad-Size")
	public static String getExactAdsize(String AdSize)
	{
		AdSize=AdSize.replaceAll("\\s", "");
				
		if(AdSize.contains("column"))
		{
			AdSize=AdSize.substring(0, AdSize.indexOf("\""));
			AdSize=AdSize.replaceAll("column", "");
		}			
		
		if(AdSize.contains("pixels"))
			AdSize=AdSize.substring(AdSize.indexOf("(")+1, AdSize.indexOf("pixels"));
		
		return AdSize;
	}
	
	//@Step("Get Exact Pub-Dates")
	public static String getExactRunDate(String Date)
	{
		Date=Date.replaceAll("\\s", "");		
		
		String MonthName=Date.substring(Date.indexOf(",")+1, Date.lastIndexOf(",")-2);
		MonthName=Integer.toString(Month.valueOf(MonthName.toUpperCase()).getValue());
		if(MonthName.length()==1)
			MonthName="0"+MonthName;
		
		String DateName=Date.substring(Date.lastIndexOf(",")-2,Date.lastIndexOf(","));
		
		String YearName=Date.substring(Date.lastIndexOf(",")+1);
		
		String exactDate=MonthName+"/"+DateName+"/"+YearName;
		
		return exactDate;
	}
	
	//@Step("Get Exact Online Pub-Dates")
	public static String getExactOnlineRunDate(String Date)
	{
		Date=Date.replaceAll("\\s", "");
		//Date=Date.substring(Date.indexOf(",")+1,Date.indexOf("-"));
		Date=Date.substring(0,Date.indexOf(","));
		return Date;
	}

	//@Step("Take Screenshot")
	public static void takeSnapShot(WebDriver driver,String filename) throws Exception{

		Thread.sleep(3000);          
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);           
		try {
			String path=System.getProperty("user.dir")+"\\screenshots\\";
			String newfilename=filename+System.currentTimeMillis();
			FileUtils.copyFile(src, new File(path+newfilename+".png"));   
			
			
		} 
		catch (IOException e)
		{
			System.out.println(e.getMessage()); 
			throw e;
		}
	}

	

	public static String getDate(int i) {

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, i);
		DateFormat targetFormat = new SimpleDateFormat("d/MMM/yyyy", Locale.ENGLISH);
		String expDate  = targetFormat.format(c.getTime());
		return expDate;

	}

	public static String imagePath()
	{
		String pathForImage = System.getProperty("user.dir") + "\\src\\main\\resources\\Images\\"+pickRandomIamge();
		return pathForImage;
	}

	public static String randomNumber()
	{
		int max = 99999;
		int min = 1;
		Random rand = new Random(); 
		int value = rand.nextInt((max - min) + 1) + min;
		
		return Integer.toString(value);

	}

	/**.
	 * Generate  random integers in the range 0..n.
	 * @param     numberOfDigits
	 * @return    integer - a random integer
	 * */
	public final int randomInteger(final int numberOfDigits) {
		Random randomGenerator = new Random();
		boolean flag= false;
		int randomInt;
		int randomInt2;
		do
		{
			int ndigits = 1;
			for (int i = 1; i <= numberOfDigits ; i++)
				ndigits = 10 * ndigits;
			randomInt = randomGenerator.nextInt(ndigits);
			randomInt2=randomInt+1;
			int power = (int) Math.pow(10, numberOfDigits-1);
			if(randomInt>=power)
				break;
		}
		while (flag);
		return randomInt2;
	}

	/**.
	 * Generate  random String.
	 * @param     length
	 * @return    String - a random string
	 * */
	static final String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String generateRandomString(final int length) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(randomString.charAt(rnd.nextInt(randomString.length()) ) );
		return sb.toString();
	}

	/**.
	 * Generate  random AlphaNumeric String.
	 * @param     length
	 * @return    AlphaNumeric String - a random AlphaNumeric String
	 * */
	static final String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String generateRandomAlphaNumString(final int length) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(alphaNumeric.charAt(rnd.nextInt(alphaNumeric.length())) );
		return sb.toString();
	}

	/**.
	 * Picking the random image from the Images folder.
	 * @param
	 * @return    file name of the image
	 * */
	static final String [] images = {"IMAGE 1.pdf","IMAGE 2.pdf","IMAGE1.png","IMAGE2.png"};
	public final static String pickRandomIamge()
	{
		/*Random rnd = new Random();
         StringBuilder sb = new StringBuilder(length);
         for (int i = 0; i < length; i++)
            sb.append(alphaNumeric.charAt(rnd.nextInt(alphaNumeric.length())) );
         return sb.toString();*/
		int idx = new Random().nextInt(images.length);
		String random = (images[idx]);
		return random;
	}


	static final String [] images1 = {"Image1.jpg","Image2.jpg", "Image3.jpg"};
	public final static String pickRandomIamge1()
	{
		/*Random rnd = new Random();
         StringBuilder sb = new StringBuilder(length);
         for (int i = 0; i < length; i++)
            sb.append(alphaNumeric.charAt(rnd.nextInt(alphaNumeric.length())) );
         return sb.toString();*/
		int idx = new Random().nextInt(images1.length);
		String random = (images1[idx]);
		return random;
	}

	static final String [] images2 = {"IMAGE1.png","IMAGE2.png"};
	public final String pickRandomIamge2()
	{
		/*Random rnd = new Random();
         StringBuilder sb = new StringBuilder(length);
         for (int i = 0; i < length; i++)
            sb.append(alphaNumeric.charAt(rnd.nextInt(alphaNumeric.length())) );
         return sb.toString();*/
		int idx = new Random().nextInt(images2.length);
		String random = (images2[idx]);
		return random;
	}

	static final String [] images3 = {"IMAGE3.pdf","IMAGE4.pdf","IMAGE 1.pdf","IMAGE 2.pdf"};
	public final String pickRandomIamge3()
	{
		int idx = new Random().nextInt(images3.length);
		String random = (images3[idx]);
		return random;
	}
	
	/*A general method to fill text in an input field without logging*/
	public static void fillText(WebElement ele,String input, String labelName)
	{
		try{
			if(ele.isDisplayed()){
				ele.click();
				ele.clear();
				ele.sendKeys(input);

				
				ele.sendKeys(Keys.TAB);
			}
			else{
				
				throw new NoSuchElementException("The text is not available..");
			}
		}
		catch(Exception e){
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static void fillText(WebElement ele,String input){
		try
		{
			if(ele.isDisplayed())
			{
				ele.click();
				ele.clear();
				ele.sendKeys(input);
				//Logger.info("The text is entered as: "+input);
			}
			/*else
			{
				//Logger.info("The text is not available..");
				throw new NoSuchElementException("The text is not available..");
			}*/
		}
		catch(Exception e)
		{
			//Logger.error(e.getMessage());
			/*try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			throw e;
		}
	}
	/*A general method to select particular value in a dropdown ByVisibleText method*/
	public static void selectDropdown(WebElement ele, String input)
	{
		try{
			if(ele.isDisplayed()){
				//ele.getText();
				ele.click();
				Thread.sleep(3000);
				Select select = new Select(ele);
				Thread.sleep(3000);
				select.selectByVisibleText(input);
			}
			
		}
		catch(Exception e){
			//Logger.error(e.getMessage());
		}
	}
}
