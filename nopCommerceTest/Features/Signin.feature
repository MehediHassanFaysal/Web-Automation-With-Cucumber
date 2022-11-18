Feature: Login 

Scenario: Successful Login with valid Credentials
	Given User Launch Chrome browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User check on Logout
	Then Page Title should be "Your store. Login"
	And close browser 