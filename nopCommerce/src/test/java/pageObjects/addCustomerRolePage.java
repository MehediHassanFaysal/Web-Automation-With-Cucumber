package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class addCustomerRolePage {
public WebDriver ldriver;
	
	public addCustomerRolePage(WebDriver rdriver) {  // constructor of LoginPage class
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	// load all the page loader ------------------------
		By clickOnCatalogMenu=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[2]/a");
		By clickOnCustomerRolesMenuItem=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[2]/ul/li[1]/a");
		By clickOnAddnewbutton = By.xpath("/html/body/div[3]/div[1]/div/div/a");
		
		By txtName = By.xpath("//*[@id=\"Name\"]");
		By txtSystemName = By.xpath("//*[@id=\"SystemName\"]");
		
		By saveBtn = By.xpath("/html/body/div[3]/div[1]/form/div/div/button[1]");
		
		
		// Action methods for all page loader
		
		public String getPageTitle() {
			return ldriver.getTitle();
		}
		
		public void clickOnCatalogMenu() {
			ldriver.findElement(clickOnCatalogMenu).click();
		}
		
		public void clickOnCustomerRolesMenuItem() {
			ldriver.findElement(clickOnCustomerRolesMenuItem).click();
		}
		
		public void clickOnAddnewbutton() {
			ldriver.findElement(clickOnAddnewbutton).click();
		}
		
		public void txtName(String role) {
			ldriver.findElement(txtName).sendKeys(role);
		}
		
		public void txtSystemName(String s_name) {
			ldriver.findElement(txtSystemName).sendKeys(s_name);
		}
		
		public void savebutton() {
			ldriver.findElement(saveBtn).click();
		}

		
		
}
