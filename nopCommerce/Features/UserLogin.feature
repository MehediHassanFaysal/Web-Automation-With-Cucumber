Feature: Login 	

Background: Below are common steps for every scenario
	Given User Launch Chrome browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	
    @regression
	Scenario Outline: Login Data Driven with valid Password Credentials
		And User enters Email as "<email>" and Password as "<password>"
		And Click on login
		Then Page Title should be "Dashboard / nopCommerce administration"
		When User check on Logout
		Then Page Title should be "Your store. Login"
		And close browser
		
		Examples:
			| email | password |
			| 0918@yourstore.com | admin |
			| 12faysal@gmail.com | admin |
			| admin@yourstore.com| admin |
	
	@regression
	Scenario Outline: Login Data Driven with valid Email Credentials
		And User enters Email as "<email>" and Password as "<password>"
		And Click on login
		Then Dashboard Page title should be "Dashboard / nopCommerce administration"
		When User check on Logout
		Then Page Title should be "Your store. Login"
		And close browser
		
		Examples:
			| email | password |
			| admin@yourstore.com | Admin |
			| admin@yourstore.com | -123- |
			| admin@yourstore.com | @1#9$ |
			| admin@yourstore.com | admin |         
	
	
	@regression
	Scenario Outline: Try to login with blank email and password fields
		And User enters Email as "<email>" and Password as "<password>"
		And Click on login
		Then Landing Page title should be "Dashboard / nopCommerce administration"
		When User check on Logout
		Then Page Title should be "Your store. Login"
		And close browser
		
		Examples:
			| email | password |
			|  |  |
  	
	@regression
	Scenario Outline: Try to login with valid email but password field as blank 
		And User enters Email as "<email>" and Password as "<password>"
		And Click on login
		Then Title should be "Dashboard / nopCommerce administration"
		When User check on Logout
		Then Page Title should be "Your store. Login"
		And close browser
		
		Examples:
			| email | password |
			| admin@yourstore.com |  |
    
	
	@sanity
	Scenario: Successfully Login with valid Credentials except Unchecking Remember Me checkbox
		And User enters Email as "admin@yourstore.com" and Password as "admin"
		And Check the Remember Me Checkbox
		And Click on login
		Then Page Title should be "Dashboard / nopCommerce administration"
		When User check on Logout
		Then Page Title should be "Your store. Login"
		And close browser 
		
	@sanity
	Scenario: Login with valid Credentials without checking Remember Me checkbox
		And User enters Email as "admin@yourstore.com" and Password as "admin"
		And Click on login
		Then Page Title should be "Dashboard / nopCommerce administration"
		When User check on Logout
		Then Page Title should be "Your store. Login"
		And close browser 
