package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class addCalalog {
public WebDriver ldriver;
	
	public addCalalog(WebDriver rdriver) {  // constructor of LoginPage class
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	// load all the page loader ------------------------
		By clickOnCatalogMenu=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[2]/a");
		By clickOnProductsMenuItem=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[2]/ul/li[1]/a");
		By clickOnAddnewbutton = By.xpath("/html/body/div[3]/div[1]/form[1]/div/div/a");
		
		By txtProductName = By.xpath("//*[@id=\"Name\"]");
		By txtShortDescription = By.xpath("//*[@id=\"ShortDescription\"]");
		
		By saveBtn = By.xpath("//*[@id=\"product-form\"]/div[1]/div/button[1]");
		
		
		// Action methods for all page loader
		
		public String getPageTitle() {
			return ldriver.getTitle();
		}
		
		public void clickOnCatalogMenu() {
			ldriver.findElement(clickOnCatalogMenu).click();
		}
		
		public void clickOnProductsMenuItem() {
			ldriver.findElement(clickOnProductsMenuItem).click();
		}
		
		public void clickOnAddnewbutton() {
			ldriver.findElement(clickOnAddnewbutton).click();
		}
		
		public void txtProductName(String name) {
			ldriver.findElement(txtProductName).sendKeys(name);
		}
		
		public void txtShortDescription(String s_des) {
			ldriver.findElement(txtShortDescription).sendKeys(s_des);
		}
		
		public void savebutton() {
			ldriver.findElement(saveBtn).click();
		}

}
