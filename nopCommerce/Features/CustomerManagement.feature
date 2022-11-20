Feature: Customers 

	Background: Below are common steps for every scenario
		Given User Launch Chrome browser
		When User opens URL "https://admin-demo.nopcommerce.com/login"
		And User enters Email as "admin@yourstore.com" and Password as "admin"
		And Click on login
		Then User can view Dashboard
		When User click on Customers Menu
		And Click on Customers Menu Item
		
	@sanity
	Scenario: Add a new Customer
		And click on Add new button
		Then User can view Add new customers page
		When User enter Customer info
		And click on Save button
		Then User can view conformation message "The new customer has been added successfully."
		And close browser
		
	@sanity
	Scenario: Search Customer by EmailID
		And Enter Customer Email
		When Click on search button
		Then User should found Email in Search table
		And close browser
	
	@sanity
	Scenario: Delete a Customer
		When Click on Edit button
		Then User can view Edit customer details
		When Click on Delete button on top of right corner
		Then Confirm are your want to delete this item?
		When Click on Delete button on modal 
		Then User can view conformation message like "The customer has been deleted successfully."
		And close browser
		
