package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.*;
import pageObjects.LoginPage;

public class Steps {

	public WebDriver driver;   // global variable that access all methods
	public LoginPage lp;	 // lp = global variable that access all methods called from pageObject package 
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
	    driver = new ChromeDriver();
	    
	    lp= new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) throws InterruptedException {
	    lp.setUserName(email);
	    lp.setPassword(password);
		   Thread.sleep(1000);
	}

	@When("Click on login")
	public void click_on_login() {
	    lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
	   if(driver.getPageSource().contains("Login was unsuccessful.")) {
		   driver.close();
		   Thread.sleep(2000);
		   Assert.assertTrue(false);
	   }else {
		   Assert.assertEquals(title,driver.getTitle());
	   }
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


}
