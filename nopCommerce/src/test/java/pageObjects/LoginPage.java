package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver ldriver;
	
	// Constructor of LoginPage class 
	public LoginPage(WebDriver cdriver) {
		ldriver = cdriver;
		PageFactory.initElements(cdriver, this);
	}
	
	
	// catch contents from loader file (web pages)
	@FindBy (id="Email")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy (id="Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy (id="RememberMe")
	@CacheLookup
	WebElement clickRememberMe;
	
	@FindBy (xpath="/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")
	@CacheLookup
	WebElement btnLoggin;
	
	@FindBy (xpath="//*[@id=\"navbarText\"]/ul/li[3]/a")
	@CacheLookup
	WebElement LogoutButton;
	
	
	// This for performing Actions
	public void setUserName(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);	
	}
	
	public void setPassword(String password) {
		txtPassword.clear();
		txtPassword.sendKeys(password);	
	}
	
	public void clickRememberMe() {
		clickRememberMe.click();
	}
	public void clickLogin() {
		btnLoggin.click();
	}
	public void clickLogout() {
		LogoutButton.click();
	}
}
