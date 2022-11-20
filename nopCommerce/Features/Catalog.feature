Feature: Catalog

	Background: Below are common steps for every scenario
		Given User Launch Chrome browser
		When User opens URL "https://admin-demo.nopcommerce.com/login"
		And User enters Email as "admin@yourstore.com" and Password as "admin"
		And Click on login
		Then User can view Dashboard
		When User click on Catelog Menu
	
	@regression
	Scenario Outline: Add a new product
		And Click on Products sub-item
		And Click on Addnew button
		Then View Products page
		When User assign product info like Name as "<name>" and Short description as "<s_des>"
		And Now Click on Save button
		Then After saving user can view the conformation message "The new customer role has been added successfully."
		And close browser
		
			Examples:
				| name | s_des |
				| Apple | Apple iPhone 14 Pro Max comes with a 6.7 inches LTPO Super Retina XDR OLED 1290 x 2796 pixels screen. It has a double punch-hole design. |
				| Nokia | 6.7 inches LTPO Super Retina XDR OLED 1290 x 2796 pixels screen. |
