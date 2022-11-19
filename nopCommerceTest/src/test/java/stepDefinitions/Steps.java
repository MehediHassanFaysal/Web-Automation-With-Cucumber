package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {


	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
	    driver = new ChromeDriver();
	    
	    lp= new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url); 
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) throws InterruptedException {
	    lp.setUserName(email);
	    lp.setPassword(password);
		   Thread.sleep(1000);
	}

	@When("Click on login")
	public void click_on_login() throws InterruptedException {
	    lp.clickLogin();
	    Thread.sleep(2000); 
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
	   if(driver.getPageSource().contains("Login was unsuccessful.")) {
		   driver.close();
		   
		   Assert.assertTrue(false);
	   }else {
		   Assert.assertEquals(title,driver.getTitle());
	   }
	   Thread.sleep(2000);
	}

	@When("User check on Logout")
	public void user_check_on_logout() throws InterruptedException {
	    lp.clickLogout();
	    Thread.sleep(3000); // 3 seconds
	}

	@Then("close browser")
	public void close_browser() {
	   driver.quit();   // or driver.close();
	}
	
	
	// customers features step definitions.....................
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
	   addCust = new AddCustomerPage(driver);
	   Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}
	
	@When("User click on Customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
	   Thread.sleep(2000);
	   addCust.clickOnCustomersMenu();
	}
	
	@When("Click on Customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		   Thread.sleep(2000);
		   addCust.clickOnCustomersMenuItem();
	}
	
	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {

		   addCust.clickOnAddNew();
		   Thread.sleep(3000);
	}
	
	@Then("User can view Add new customers page")
	public void user_can_view_add_new_customers_page() {
		 Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User enter Customer info")
	public void user_enter_customer_info() throws InterruptedException {
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
