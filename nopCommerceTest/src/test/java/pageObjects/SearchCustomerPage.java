package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {

	
public WebDriver ldriver;   // object of webdriver
	
	WaitHelper waithelper;

	public SearchCustomerPage(WebDriver rdriver) {  // constructor od LoginPage class
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		waithelper = new WaitHelper(ldriver);
	}
	
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement drpdobDay1;
	
	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(how = How.XPATH, using = "//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement txtcustomerRoles;
	
	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;
	
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	// action methods
	
	public void setEmail(String email) {
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void clickSearch(){
		btnSearch.click();
		waithelper.WaitForElement(txtEmail, 30);	
	}
	
	public int getNoOfRows() {
		return(tableRows.size());
	}
	
	public int getNoOfColumns() {
		return(tableColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email) { 
		boolean flag = false;
		for(int i = 1; i<=getNoOfRows();i++) {
			String emailid = table.findElement(By.xpath("//*[@id=\"customers-grid\"]/tbody/tr["+i+"]/td[2]")).getText();
		
			if(emailid.equals("victoria_victoria@nopCommerce.com")) {
				flag=true;
			}
		}
		return flag;
		
	}
	
}
