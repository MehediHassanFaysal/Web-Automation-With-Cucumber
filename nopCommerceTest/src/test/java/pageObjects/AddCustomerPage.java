package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	
public WebDriver ldriver;   // object of webdriver
	
	public AddCustomerPage(WebDriver rdriver) {  // constructor od LoginPage class
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	// Locators of all the elements ---------------------------------------------------
	
	By lnkCustomers_menu=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/a");
	By lnkCustomers_menuItem=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/ul/li[1]/a");
	
	By btnAddNew = By.xpath("/html/body/div[3]/div[1]/form[1]/div/div/a");
	
	By txtEmail = By.xpath("//*[@id=\"Email\"]");
	By txtPassword = By.xpath("//*[@id=\"Password\"]");
	
	By txtFirstName=By.xpath("//*[@id=\"FirstName\"]");
	By txtLastName=By.xpath("//*[@id=\"LastName\"]");
	
	By male_gender=By.id("Gender_Male");
	By female_gender=By.id("Gender_Female");
	By txtDoB=By.xpath("//*[@id=\"DateOfBirth\"]");
	By txtCompanyName=By.xpath("//*[@id=\"Company\"]");
	
	By txtcustomerRoles = By.xpath("//*[@id=\"customer-info\"]/div[2]/div[10]/div[2]/div/div[1]/div/div");
	
	By lstItemAdministrators = By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[1]");
	By lstItemForumModerators = By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[2]");
	By lstItemGuests = By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[3]");
	By lstItemRegistered = By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[4]");
	By lstItemVendors = By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[5]");
	
	By drpmgrOfVendor = By.xpath("//*[@id=\"VendorId\"]");
	By txtAdminContent=By.xpath("//*[@id=\"AdminComment\"]");
	By btnSave=By.xpath("/html/body/div[3]/div[1]/form/div[1]/div/button[1]");
	
	// Actions for all the locators-----------------------------------------------
	
	public String getPageTitle() {
		return ldriver.getTitle();
	}
	
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuItem).click();
	}
	
	public void clickOnAddNew() {
		ldriver.findElement(btnAddNew).click();
	}
	
	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException {
//		if(!role.equals("Vendor")) {
//			ldriver.findElement(By.xpath(""));
//		}
		ldriver.findElement(txtcustomerRoles).click();
		
		WebElement listitem;
		Thread.sleep(3000);
		
		if(role.equals("Administrators")) {
			listitem=ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]"));
			listitem.click();
			listitem=ldriver.findElement(lstItemAdministrators);
		}
		else if(role.equals("Forum Moderators")) {
			listitem=ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]"));
			listitem.click();
			listitem=ldriver.findElement(lstItemForumModerators);
		}
		else if(role.equals("Guests")) {
			listitem=ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]"));
			listitem.click();
			listitem=ldriver.findElement(lstItemGuests);

		}
		else if(role.equals("Registered")) {
			listitem=ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]"));
			listitem.click();
			listitem=ldriver.findElement(lstItemRegistered);
		}
		else if(role.equals("Vendors")) {
			listitem=ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]"));
			listitem.click();
			listitem=ldriver.findElement(lstItemVendors);
		}
		else {
			listitem=ldriver.findElement(lstItemGuests);
		}
		
		//listitem.click();
		//Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor)ldriver;    // work like as listitem.click()
		js.executeScript("arguments[0].click();",listitem);
	}
	
	public void setmanagerOfvendor(String value) {
		Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	public void setgender(String gender) {
		if(gender.equals("male")) {
			ldriver.findElement(male_gender).click();
		}
		else if(gender.equals("male")) {
			ldriver.findElement(female_gender).click();
		}else {
			ldriver.findElement(male_gender).click();  // Default
		}
	}
	
	public void setFirstName(String fname) {
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDoB(String dob) {
		ldriver.findElement(txtDoB).sendKeys(dob);
	}
	
	public void setCompanyname(String comname) {
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setAdminContent(String content) {
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}
}
