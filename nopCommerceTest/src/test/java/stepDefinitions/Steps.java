package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {


	@Before
	public void setup() throws IOException {
		
		logger=Logger.getLogger("nopCommerce"); // Added logger
		PropertyConfigurator.configure("log4j.properties"); // Added logger
		
		//Reading properties
		configProp=new Properties();
		FileInputStream configProfile=new FileInputStream("config.properties");
		configProp.load(configProfile);
		
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
		//driver = new ChromeDriver();
		String br=configProp.getProperty("browser");
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}
		else if(br.equals("iepath")) {
			System.setProperty("webdriver.ie.driver", configProp.getProperty("ie"));
			driver = new FirefoxDriver();
		}else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		
	    
	}
	
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
	    logger.info("--------- launching browser ---------");
	    lp= new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("--------- Opening URL ---------");
		driver.get(url); 
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) throws InterruptedException {
		logger.info("--------- Providing login details ---------");
		lp.setUserName(email);
	    lp.setPassword(password);
		   Thread.sleep(1000);
	}

	@When("Click on login")
	public void click_on_login() throws InterruptedException {
		logger.info("--------- Started Login ---------");
	    lp.clickLogin();
	    Thread.sleep(2000); 
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
	   if(driver.getPageSource().contains("Login was unsuccessful.")) {
		   driver.close();
		   logger.info("--------- Login passed ---------");
		   Assert.assertTrue(false);
	   }else {
		   logger.info("--------- Login failed ---------");
		   Assert.assertEquals(title,driver.getTitle());
	   }
	   Thread.sleep(2000);
	}

	@When("User check on Logout")
	public void user_check_on_logout() throws InterruptedException {
		logger.info("--------- Click on logout link ---------");
	    lp.clickLogout();
	    Thread.sleep(3000); // 3 seconds
	}

	@Then("close browser")
	public void close_browser() {
	   logger.info("--------- Login passed ---------");
	   driver.quit();   // or driver.close();
	}
	
	
	// customers features step definitions.....................
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		   logger.info("--------- Login passed ---------");
	   addCust = new AddCustomerPage(driver);
	   Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}
	
	@When("User click on Customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
	   logger.info("--------- Clicking on menu ---------");
	   Thread.sleep(2000);
	   addCust.clickOnCustomersMenu();
	}
	
	@When("Click on Customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		   logger.info("--------- Clicking on customers sub-item menu ---------");
		   Thread.sleep(2000);
		   addCust.clickOnCustomersMenuItem();
	}
	
	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		   logger.info("--------- Click on Add new button ---------");
		   addCust.clickOnAddNew();
		   Thread.sleep(3000);
	}
	
	@Then("User can view Add new customers page")
	public void user_can_view_add_new_customers_page() {
		 Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User enter Customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("--------- Adding new customer info. ---------");
	    String email = randomstring()+"@gmail.com";
	    addCust.setEmail(email);
	    addCust.setPassword("text1234");
	    // Registered --default
	    // The customer cannot in both 'Guest' and 'registered' customer role
	    // Add the customer to 'Guests' or 'Registered' customer role
	    addCust.setCustomerRoles("Guests");
	    Thread.sleep(2000);
	    
	    addCust.setFirstName("Faysal");
	    addCust.setLastName("Sarder");
	    addCust.setgender("Male");
	    addCust.setmanagerOfvendor("Vendor 1");
	    addCust.setDoB("11-11-1999");
	    addCust.setCompanyname("Tech Hub");
	    addCust.setAdminContent("This is for automated testing........... ");
	    
	}
	
	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("--------- New customer assigned successfully ---------");
		Thread.sleep(1000);
	    addCust.clickOnSave();
	    Thread.sleep(1000);
	}
	
	@Then("User can view conformation message {string}")
	public void user_can_view_conformation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
	   
	}
	
	
	// steps for searching a customer by email id

	@When("Enter Customer Email")
	public void enter_customer_email() {
	    searchCust = new SearchCustomerPage(driver);
	    searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(2000);
	}
	@Then("User should found Email in Search table")
	public void user_should_found_email_in_search_table() {
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}



}
