package com.automation.test;

import java.io.IOException;

public class JavaExample {

	public static void main(String[] args) throws IOException, Exception {
		
		String dataToNote = "/api/ad-material/preview/adss/3111654/373482/1?isColor=True&adPreviewSizeId=3&ts=1573725055425";
		System.out.println(dataToNote.indexOf("&ts"));
		dataToNote = dataToNote.substring(0, (dataToNote.indexOf("&ts"))+3).trim();
		System.out.println(dataToNote);
		

}


}


