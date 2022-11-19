package stepDefinitions;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;

public class BaseClass {

	public WebDriver driver;   // global variable that access all methods
	public LoginPage lp;	 // lp = global variable that access all methods called from pageObject package 
	public AddCustomerPage addCust;
	
	
	//Created for generating random string for email id 
	public static String randomstring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);    // contains 5 characters email id randomly
		return generatedString1;   
	}
}
