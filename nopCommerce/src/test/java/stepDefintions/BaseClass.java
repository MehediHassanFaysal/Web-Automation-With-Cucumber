package stepDefintions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import pageObjects.addCalalog;
import pageObjects.addCustomerPage;
import pageObjects.addCustomerRolePage;
import pageObjects.deleteCustomer;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage lp;
	public addCustomerPage addCust;
	public SearchCustomerPage searchCust;
	public deleteCustomer deleteCust;
	public addCustomerRolePage addCustomerRole;
	public addCalalog addCatalog;
	
	public static Logger logger;      // call for printing what we execute
	public Properties configProp;     // this is for run program in different browser
	
	// Created for generating random string for email id  (not applicable for Login feature)
	public static String randomString() {
		String generatedAlphaNumericString = RandomStringUtils.randomAlphanumeric(6);
		return generatedAlphaNumericString;
		
	}
	
}
