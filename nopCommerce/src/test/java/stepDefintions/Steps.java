package stepDefintions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import pageObjects.addCalalog;
import pageObjects.addCustomerPage;
import pageObjects.deleteCustomer;

public class Steps extends BaseClass{
	
	@Before
	public void setup() throws IOException {
		logger = Logger.getLogger("nopCommerce:Defaults for admin area");
		PropertyConfigurator.configure("log4j.properties");
	
		// Reading properties
		configProp = new Properties();
		FileInputStream config = new FileInputStream("config.properties");
		configProp.load(config);
		
		String br = configProp.getProperty("browser");
		
		if(br.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
		}
		else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
		}else {
			System.out.print("config file not found");
		}
	}
	

		@Given("User Launch Chrome browser")
		public void user_launch_chrome_browser() {
			 logger.info("--------- launching browser ---------");
			 lp = new LoginPage(driver);
		}
		
		@When("User opens URL {string}")
		public void user_opens_url(String url) {
			logger.info("--------- Opening URL ---------");
			driver.get(url);
			driver.manage().window().maximize();
		}
		
		@When("User enters Email as {string} and Password as {string}")
		public void user_enters_email_as_and_password_as(String email, String password) throws InterruptedException {
		  logger.info("--------- Providing Email and Password for Login ---------");
		  
		  lp.setPassword(password);
		  lp.setUserName(email);
		
		  Thread.sleep(2000);
		}
		
		@When("Check the Remember Me Checkbox")
		public void check_the_remember_me_checkbox() throws InterruptedException {
			 logger.info("--------- Check on Remember Me Checkbox ---------");
			 lp.clickRememberMe();
			 Thread.sleep(2000);
		}
		
		
		@When("Click on login")
		public void click_on_login() throws InterruptedException {
			logger.info("--------- Started Login ---------");
			lp.clickLogin();
			Thread.sleep(2500);
		}
		
		@Then("Page Title should be {string}")
		public void page_title_should_be(String title) throws InterruptedException {
			if(driver.getPageSource().contains("No customer account found"))
			{
				driver.close();
				logger.info("--------- Login failed(No customer account found) ---------");
				Assert.assertTrue(false);
			}else {
				logger.info("--------- Login passed ---------");
				Assert.assertEquals(title, driver.getTitle());
			}
			
			Thread.sleep(3000);

		}
		
		@Then("Dashboard Page title should be {string}")
		public void dashboard_page_title_should_be(String title) throws InterruptedException {
			if(driver.getPageSource().contains("The credentials provided are incorrect"))
			{
				driver.close();
				logger.info("--------- Login failed(The credentials provided are incorrect) ---------");
				Assert.assertTrue(false);
			}else {
				logger.info("--------- Login passed ---------");
				Assert.assertEquals(title, driver.getTitle());
			}
			
			Thread.sleep(3000);

		}


		
		@When("User check on Logout")
		public void user_check_on_logout() throws InterruptedException {
			logger.info("--------- Click on logout link ---------");
		    lp.clickLogout();
		    Thread.sleep(2000); // 3 seconds
		}
		
		@Then("close browser")
		public void close_browser() {
			logger.info("--------- Login passed ---------");
			driver.close();   // or driver.quit();
		}
		
		@Then("Title should be {string}")
		public void title_should_be(String title) throws InterruptedException {
			if(driver.getPageSource().contains("Please enter your password"))
			{
				System.out.print("Should not logged In");
				driver.close();
				logger.info("--------- Login failed(Required: Please enter your password) ---------");
				Assert.assertTrue(false);
			}else {
				logger.info("--------- Login passed ---------");
				Assert.assertEquals(title, driver.getTitle());
			}
			
			Thread.sleep(3000);
		}

		@Then("Landing Page title should be {string}")
		public void landing_page_title_should_be(String title) throws InterruptedException {
			if(driver.getPageSource().contains("Please enter your email"))
			{
				System.out.print("Should not logged In");
				driver.close();
				logger.info("--------- Login failed(Required: Please enter your email) ---------");
				Assert.assertTrue(false);
			}else {
				logger.info("--------- Login passed ---------");
				Assert.assertEquals(title, driver.getTitle());
			}

		}

		
		// Customers feature step definitions--------------------------------------------------------------
		
		//Adding a new customer ------
		
		@Then("User can view Dashboard")
		public void user_can_view_dashboard() {
			   logger.info("--------- Login passed ---------");
		   addCust = new addCustomerPage(driver);
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
			logger.info("--------- Varifying with page title---------");
			 Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
		}
		@When("User enter Customer info")
		public void user_enter_customer_info() throws InterruptedException {
			logger.info("--------- Adding new customer info. ---------");
		    String email = randomString()+"@gmail.com";
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
			logger.info("--------- Passed ---------");
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
		   
		}


		// steps for searching a customer by email id-------------------------------------------------------

		@When("Enter Customer Email")
		public void enter_customer_email() {
			logger.info("--------- Search by email ---------");
		    searchCust = new SearchCustomerPage(driver);
		    searchCust.setEmail("victoria_victoria@nopCommerce.com");
		}
		@When("Click on search button")
		public void click_on_search_button() throws InterruptedException {
			logger.info("--------- Clicking on search button ---------");
			searchCust.clickSearch();
			Thread.sleep(2000);
		}
		@Then("User should found Email in Search table")
		public void user_should_found_email_in_search_table() {
			logger.info("--------- Passed ---------");
			boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
			Assert.assertEquals(true, status);
		}
		
	
		// steps for deleting a customer -----------------------------------------------------------

		
		@When("Click on Edit button")
		public void click_on_edit_button() throws InterruptedException {
			logger.info("---------- Clicking on Edit button ----------");
			deleteCust = new deleteCustomer(driver);
			Thread.sleep(2000);
			deleteCust.clickOnEditButton();
		}
		
		@Then("User can view Edit customer details")
		public void user_can_view_edit_customer_details() throws InterruptedException {
			logger.info("---------- View Edit customer details page ----------");
			Assert.assertEquals("Edit customer details / nopCommerce administration", deleteCust.getPageTitle());
		}
		
		@When("Click on Delete button on top of right corner")
		public void click_on_delete_button_on_top_of_right_corner() throws InterruptedException {
			logger.info("---------- Clicking on Delete button ----------");
			Thread.sleep(2000);
			deleteCust.clickOnDeleteButton();
		}
		
		@Then("Confirm are your want to delete this item?")
		public void confirm_are_your_want_to_delete_this_item() {
			logger.info("---------- Matching with modal element ----------");
			boolean status = driver.findElement(By.xpath("//*[@id=\"customermodel-Delete-delete-confirmation\"]/div/div/form/div/div[1]")).isEnabled();
			Assert.assertEquals(true, status);
		}
		
		@When("Click on Delete button on modal")
		public void click_on_delete_button_on_modal() throws InterruptedException {
			logger.info("---------- Clicking on Delete button ----------");
			Thread.sleep(2000);
			deleteCust.clickOnDeleteButtonOnModal();
	
		}
		
		@Then("User can view conformation message like {string}")
		public void user_can_view_conformation_message_like(String string) {
			logger.info("---------- Matched with response message (Passed) ----------");
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The customer has been deleted successfully."));
		}
		
		// Catalog step Definitions -----------------------------------------------------------
		
		
		@When("User click on Catelog Menu")
		public void user_click_on_catelog_menu() throws InterruptedException {
			addCatalog = new addCalalog(driver);
			logger.info("--------- Clicking on Catalog menu ---------");
			addCatalog.clickOnCatalogMenu();
			Thread.sleep(2000);
		}
		
		@When("Click on Products sub-item")
		public void click_on_products_sub_item() throws InterruptedException {
			logger.info("---------- Clicking on Products Sub-menu Item ----------");
			addCatalog.clickOnProductsMenuItem();
			Thread.sleep(2000);
		}

		
		@When("Click on Addnew button")
		public void click_on_addnew_button() throws InterruptedException {
			logger.info("---------- Clicking on Add new button ----------");
			addCatalog.clickOnAddnewbutton();
			Thread.sleep(2000);
		}
		
		@Then("View Products page")
		public void view_products_page() throws InterruptedException {
			logger.info("---------- Validation with Add a new product page title ----------");
			Assert.assertEquals("Add a new product / nopCommerce administration", addCatalog.getPageTitle());
			Thread.sleep(1000);
		}

		
		
		@When("User assign product info like Name as {string} and Short description as {string}")
		public void user_assign_product_info_like_name_as_and_short_description_as(String name, String s_des) throws InterruptedException {
			logger.info("--------- Adding product info ---------");
			addCatalog.txtProductName(name);
			Thread.sleep(1000);
			addCatalog.txtShortDescription(s_des);
			Thread.sleep(2000);
		}
		
		@When("Now Click on Save button")
		public void now_click_on_save_button() {
			logger.info("--------- Product has been set successfully ---------");
			addCatalog.savebutton();
		}
		
		@Then("After saving user can view the conformation message {string}")
		public void after_saving_user_can_view_the_conformation_message(String string) {
			logger.info("--------- Passed ---------");
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new product has been added successfully."));
		}
		


   

}
