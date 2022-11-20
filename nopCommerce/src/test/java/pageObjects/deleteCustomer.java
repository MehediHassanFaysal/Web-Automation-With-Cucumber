package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class deleteCustomer {
	public WebDriver ldriver;
	
	public deleteCustomer(WebDriver rdriver) {  // constructor of LoginPage class
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	// load all the page loader ------------------------
	
	By clickOnEditButton = By.xpath("//*[@id=\"customers-grid\"]/tbody/tr[3]/td[7]/a");
	By clickOnDeleteButton = By.xpath("//*[@id=\"customer-delete\"]");
	
	By clickOnDeleteButtonOnModal = By.xpath("//*[@id=\"customermodel-Delete-delete-confirmation\"]/div/div/form/div/div[2]/button");

	
	
	// Actions------------------------------------------
	
	public String getPageTitle() {
		return ldriver.getTitle();	
	}
	
	public void clickOnEditButton() {
		ldriver.findElement(clickOnEditButton).click();
	}
	
	public void clickOnDeleteButton() {
		ldriver.findElement(clickOnDeleteButton).click();
	}
	
	public void clickOnDeleteButtonOnModal() {
		ldriver.findElement(clickOnDeleteButtonOnModal).click();
	}


}
