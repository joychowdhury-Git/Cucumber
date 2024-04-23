###############Date:19th March, 2019

Feature: To test Smoke Suite of ADSS

	@Regression
	@Smoke
	@01
	@LOCTest
  Scenario: BCS Purchase Test
  Given Open the URL to test the "BCS"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "LocUser" in the "email" field in the sign in window
  When enter required "LocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then select Ad Size
  Then click on upload button
  Then upload photo
  Then click on Review and Submit button
  Then insert order notes as "BCS_Test" 
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then get the Run Dates Print In Order Line
  Then get the total price
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then wait for 3 seconds
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then check if ADSS and ADIT rundates are same
  Then check if ADSS and ADIT total prices are same for Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  
  
  
  @02
  @BUToRunInProd
  @Regression 
  @Regression03
  @Smoke
  Scenario: CTC Verify Create and View Test
  Given Open the URL to test the "CTC"  Purchase Functionality
  Then click on the Schedule tab
  Then set zones
  Then select one calendar date
  Then click Layout tab
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then set click through URL for Ad
  Then note the Draft Id
  Then click on Review and Submit button
  When click on the "Log In" link at the top right corner of the page
  When enter required "NonLocUser" in the "email" field in the sign in window
  When enter required "NonLocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Save and Complete Purchase Later button
  Then check if the Draft Id is available
  Then click on Signout
  
  
  
  @03
  @Regression
  @Smoke
  @Regression03
  @Smoke1
  Scenario: CTC Verify Edit Account Test
  Given Open the URL to test the "CTCdashboard"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "NonLocUser" in the "email" field in the sign in window
  When enter required "NonLocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on Edit Account Info link
  Then edit some info in the Account
  Then click on Save button in the edit window
  Then click on Signout
  
  
  
  @04
  @Regression
  @Smoke
  @Regression03
  @Smoke1
  Scenario: CTC Verify Purchase Test
  Given Open the URL to test the "CTC"  Purchase Functionality
  Then click on the Schedule tab
  Then set zones
  Then select one calendar date
  Then click Layout tab
  Then select the required Design Template
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then fill in the text ctc
  Then click on the Save and Continue button
  Then set click through URL for Ad
  Then click on Review and Submit button
  When click on the "Log In" link at the top right corner of the page
  When enter required "NonLocUser" in the "email" field in the sign in window
  When enter required "NonLocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "HTF_Test"
  #Then select new credit card
  #Then select the credit card checkbox
  #Then fill in the billing info
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then check if the ADSS and Adit orderlines price are same
  Then check if the ADSS and Adit totalPrice price are same for non-Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  
  @05
  @Regression
  @Smoke
  @Regression01
  @Smoke1
  Scenario: CTC Renew Order Test
  Given Open the URL to test the "CTC"  Purchase Functionality
  Then click on the Schedule tab
  Then set zones
  Then select one calendar date
  Then click Layout tab
  Then select the required Design Template
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then fill in the text ctc
  Then click on the Save and Continue button
  Then set click through URL for Ad
  Then click on Review and Submit button
  When click on the "Log In" link at the top right corner of the page
  When enter required "NonLocUser" in the "email" field in the sign in window
  When enter required "NonLocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "HTF_Test"  
  
  #Then select new credit card
  #Then select the credit card checkbox
  #Then fill in the billing info
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line for Ad Only Order Line
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then check if the ADSS and Adit orderlines price are same
  Then check if the ADSS and Adit totalPrice price are same for non-Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  Then swicth to the 1 tab
  Then click on the same order Id
  Then click on Renew Link
  Then select one calendar date
  Then click on Review and Submit button
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "HTF_Test"
  #Then select new credit card
  #Then select the credit card checkbox
  #Then fill in the billing info
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line for Ad Only Order Line
  Then swicth to the 2 tab
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then check if the ADSS and Adit orderlines price are same
  Then check if the ADSS and Adit totalPrice price are same for non-Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  
  @06
  @Regression
  @Smoke
  @Regression01
  @Smoke1
  Scenario: CTC Edit Order Test
  Given Open the URL to test the "CTC"  Purchase Functionality
  Then click on the Schedule tab
  Then set zones
  Then select one calendar date
  Then click Layout tab
  Then select the required Design Template
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then fill in the text ctc
  Then click on the Save and Continue button
  Then set click through URL for Ad
  Then click on Review and Submit button
  When click on the "Log In" link at the top right corner of the page
  When enter required "NonLocUser" in the "email" field in the sign in window
  When enter required "NonLocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "HTF_Test"
  #Then select new credit card
  #Then select the credit card checkbox
  #Then fill in the billing info
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line for CTC Order Edit
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then check if the ADSS and Adit orderlines price are same
  Then check if the ADSS and Adit totalPrice price are same for non-Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  Then swicth to the 1 tab
  Then click on the same order Id
  Then click on Edit Link in Order Detail Page
  Then click on Edit Link in Edit Ad Material Image
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then fill in the text ctc
  Then click on the Save and Continue button
  Then check if the New secion is displayed
  Then click on the Submit button
  Then check if the order number is generated after edit
  Then check if the order number after editing is the same as the order number earlier generated
  Then note all the run dates online and note the first order price line for CTC Order Edit after edit
  Then get the total price
  Then swicth to the 2 tab
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get all the Run Dates List after Edit from Adit
  Then check if all the ADSS and ADIT rundates after Edit are same for Non-Obit Package
  Then check if the ADSS and Adit totalPrice price are same for non-Loc users
  Then change the order to changed, if price is less than one dollar, else reject
  
  @07
  @Regression
  @Smoke
  @Regression03
  @Smoke2
  Scenario: HTF Purchase Test
  Given Open the URL to test the "HTF"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "NonLocUser" in the "email" field in the sign in window
  When enter required "NonLocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then select Ad Size
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then click on Review and Submit button
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "HTF_Test"
  #Then select new credit card
  #Then select the credit card checkbox
  #Then fill in the billing info
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then check if the ADSS and Adit orderlines price are same
  Then check if the ADSS and Adit totalPrice price are same for non-Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  
  
  @08
  @BUToRunInProd
  @Regression
  @Smoke
  @LOCTest
  Scenario: OSC Purchase Test
  Given Open the URL to test the "OSC"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "LocUser" in the "email" field in the sign in window
  When enter required "LocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then click on Review and Submit button  
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "OSC_Purchase_Test"
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  
  @09
  @Regression
  @Smoke
  @LOCTest
  Scenario: OSC Renew Test
  Given Open the URL to test the "OSC"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "LocUser" in the "email" field in the sign in window
  When enter required "LocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then click on Review and Submit button  
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "OSC_Renew_Test"
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then logout of Adit application
  Then swicth to the 1 tab
  Then click on the same order Id
  Then click on Renew Link
  Then clear the default dates
  Then select one calendar date
  Then click on Review and Submit button
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "OSC_Test" along with the order id 
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line
  Then swicth to the 2 tab
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
 
  
  @10
  @BUToRunInProd
  @Regression
  @Smoke2
  Scenario: DPR Display Verify Purchase Test
  Given Open the URL to test the "DPRDisplay"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "NonLocUser" in the "email" field in the sign in window
  When enter required "NonLocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then select Category as "Birthday (Celebrations)"
  Then select Ad Size
  Then select the required Design Template
  Then select Category DPR as "Birthday"
  Then fill in the Text DPR
  Then click on the Save and Continue button
  Then click on the Other Info tab
  Then set contact email as "aghosh@tribpub.com"
  Then click on Review and Submit button
  Then insert order notes as "DPR_Test"
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then wait for 10 seconds
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then check if the ADSS and Adit orderlines price are same
  Then check if the ADSS and Adit totalPrice price are same for non-Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  Then click on the "Payments" tab in the Order page
  Then check if only 2 dollar amounts are showing in the Payments tab
  
 
  @11
	@Regression
	@Smoke 
	@Regression03
	@LOCTest
  Scenario: MCALL Obit Verify Purchase Test
  Given Open the URL to test the "MCALL"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "LocUser" in the "email" field in the sign in window
  When enter required "LocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then select the required Design Template
  Then fill in the text for MCALL Obitor SSC Obit
  Then click on the Save and Continue button
  Then click on the Other Info tab
  Then set contact name as "TestName"
  Then set contact email as "aghosh@tribpub.com"
  Then click on Review and Submit button
  Then insert order notes as "BCS_Test"
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then get the Run Dates Print In Order Line
  Then get the total price
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then check if ADSS and ADIT total prices are same for Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  
  
  @12
  @Regression
  @Smoke
  @Regression03
  @LOCTest
  Scenario: SSC Obits Verify Purchase Test
  Given Open the URL to test the "SSCObits"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "LocUser" in the "email" field in the sign in window
  When enter required "LocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then select Category as "Palm Beach"
  Then select Ad Size
  Then select the required Design Template
  Then fill in the text for MCALL Obitor SSC Obit
  Then click on the Save and Continue button
  Then click on the Other Info tab
  Then set contact name as "TestName"
  Then set contact email as "aghosh@tribpub.com"
  Then click on the Other Info tab
  Then click on Review and Submit button
  Then insert order notes as "SSC_Obit_Test"
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then get the Run Dates Print In Order Line
  Then get the total price
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then get the total price adit
 ################## Then check if ADSS and ADIT total prices are same for Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  
  
  @13
  @Regression
  @Smoke
  @LOCTest
  Scenario: DPR_Obits Verify Purchase Test
  Given Open the URL to test the "DPRObit"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "LocUser" in the "email" field in the sign in window
  When enter required "LocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then select Category as "Eastern Shore"
  Then select Ad Size
  Then select the required Design Template
  Then fill in the Text DPR Obit
  Then click on the Save and Continue button
  Then click on the Other Info tab
  Then Select the "Virginia" town name
  Then set contact name as "TestName"
  Then set contact email as "aghosh@tribpub.com"
  Then click on Review and Submit button
  Then insert order notes as "SSC_Obit_Test"
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then get the Run Dates Print In Order Line
  Then get the total price
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then check if ADSS and ADIT total prices are same for Loc users
  Then change the order to processed, if price is less than one dollar, else reject
  
@Sanity
@14
@Regression
@Smoke
@LOCTest
Scenario: CTC_Obits verify Purchase Test
Given Open the URL to test the "CTCObit"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select the required Design Template
Then fill in the text ctc obit
Then click on the Save and Continue button
Then click on the Other Info tab
Then set city as "Miami"
Then set contact name as "TestName"
Then set contact email as "aghosh@tribpub.com"
Then click on Review and Submit button
Then insert order notes as "CTC_Obit_Test"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then get the Run Dates Print In Order Line
Then get the Run Dates Online Ad Print in Order Line
Then get the total price
Then open a new tab and open the Adit URL
Then login to the Adit URL
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get all the Run Dates List from Adit
Then check if all the ADSS and ADIT rundates are same
Then check if ADSS and ADIT total prices are same for Loc users
Then change the order to processed, if price is less than one dollar, else reject

@15
@Regression
@Smoke
@LOCTest
Scenario: CTC_Obits verify Edit Order Test
Given Open the URL to test the "CTCObit"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select the required Design Template
Then fill in the text ctc obit
Then click on the Save and Continue button
Then click on the Other Info tab
Then set city as "Miami"
Then set contact name as "TestName"
Then set contact email as "aghosh@tribpub.com"
Then click on Review and Submit button
Then insert order notes as "CTC_Obit_Test"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then get the Run Dates Print In Order Line
Then get the Run Dates Online Ad Print in Order Line
Then get the total price
Then open a new tab and open the Adit URL
Then login to the Adit URL
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get all the Run Dates List from Adit
Then check if all the ADSS and ADIT rundates are same
Then check if ADSS and ADIT total prices are same for Loc users
Then swicth to the 1 tab
Then click on the same order Id
Then click on the edit link in the Edit Order Page
Then click on the Edit link in the Design Template page
Then fill in the text ctc obit
Then click on only the Save and Continue button
Then check if the New secion is displayed
Then click on the Submit button
Then check if the order number is generated after edit
Then check if the order number after editing is the same as the order number earlier generated
Then get the Run Dates Print In Order Line after Edit
Then get the Run Dates Online Ad Print in Order Line after Edit
Then get the total price
Then swicth to the 2 tab
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get all the Run Dates List after Edit from Adit
Then check if all the ADSS and ADIT rundates after Edit are same
Then check if ADSS and after Edit ADIT total prices are same for Loc users
Then change the order to processed, if price is less than one dollar, else reject


@16
@Regression
@Smoke
@LOCTest
Scenario: CCT verify Purchase Test
Given Open the URL to test the "CCTObit"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
##When click on "Log in" button in the login window
When click on "SIGN IN" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select the required Design Template
Then fill in the text cct obit
Then click on the Save and Continue button
Then click on the Other Info tab
Then set contact email as "aghosh@tribpub.com"
Then set contact name as "TestName"
Then set customer name as "TestNameTest"
Then set adress as "123 Park St."
Then set city as "Miami"
Then wait for 5 seconds
Then set state as "FL"
Then set Zip Code as "12345"
Then click on Review and Submit button
Then insert order notes as "CCT_Obit_Test"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then get the Run Dates Print In Order Line
Then get the total price
Then open a new tab and open the Adit URL
Then login to the Adit URL
Then wait for 3 seconds
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get the order pub dates in order line
Then note all the adit orderlines price
Then check if ADSS and ADIT rundates are same
Then check if ADSS and ADIT total prices are same for Loc users
Then change the order to processed, if price is less than one dollar, else reject

@17
@Regression
@Smoke
@LOCTest
Scenario: CTC_Obits verify Renew Order Test
Given Open the URL to test the "CTCObit"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select the required Design Template
Then fill in the text ctc obit
Then click on the Save and Continue button
Then click on the Other Info tab
Then set city as "Miami"
Then set contact name as "TestName"
Then set contact email as "aghosh@tribpub.com"
Then click on Review and Submit button
Then insert order notes as "CTC_Obit_Test"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then get the Run Dates Print In Order Line
Then get the Run Dates Online Ad Print in Order Line
Then get the total price
Then open a new tab and open the Adit URL
Then login to the Adit URL
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get all the Run Dates List from Adit
Then check if all the ADSS and ADIT rundates are same
Then check if ADSS and ADIT total prices are same for Loc users
Then swicth to the 1 tab
Then click on the same order Id
Then click on the edit link in the Edit Order Page
Then click on the Edit link in the Design Template page
Then fill in the text ctc obit
Then click on only the Save and Continue button
Then check if the New secion is displayed
Then click on the Submit button
Then check if the order number is generated after edit
Then check if the order number after editing is the same as the order number earlier generated
Then get the Run Dates Print In Order Line after Edit
Then get the Run Dates Online Ad Print in Order Line after Edit
Then get the total price
Then swicth to the 2 tab
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get all the Run Dates List after Edit from Adit
Then check if all the ADSS and ADIT rundates after Edit are same
Then check if ADSS and after Edit ADIT total prices are same for Loc users
Then swicth to the 1 tab
Then click on the same order Id
Then click on Renew Link
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click on Review and Submit button
Then insert order notes as "CTC_Obit_Renew_Test_" along with the order id
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then get the Run Dates Print In Order Line after Renew
Then get the Run Dates Online Ad in Order Line after Renew
Then get the total price
Then swicth to the 2 tab
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get the order pub dates in order line after renew
Then check if all the ADSS and ADIT rundates after Renew are same
Then check if ADSS and after Renew ADIT total prices are same for Loc users
Then change the order to processed, if the  renewed price is less than one dollar, else reject

@18
@Regression
Scenario: CTC verify Forgot Password Test
Given Open the URL to test the "CTC"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
Then click on Forgot Passsword link in the new login window
Then enter the registered user email in the forgot password window
Then click on the Send Link link button the forgot password window
Then check if the Reset Link is sent
Then close the Rest Sent Confirmation popup
#Then open a new tab and open the Gmail URL
#Then set the email id as the user email id
#Then click on Next
#Then set the email password as the user email password
#Then click on Next
#Then check if gmail login is successful
#Then open the Reset Email
#Then click on the Reset Link


@19
@BUToRunInProd
Scenario: SSCST verify Purchase Test
Given Open the URL to test the "SSCST"  Purchase Functionality
Then click on the Schedule tab
#Then set the Full Run Zone
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select Ad Size 
Then select the template
Then fill in the text sscst
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then click on save and continue button
Then click on the Next Ad Config Link
Then click on the second Schedule Tab
Then select one calendar date
Then click on the second Layout tab
Then select the second template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then fill in deatils for second template sscst
Then click on save and continue button
Then set click through URL for Ad
Then click on Review and Submit button
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then enter coupon code as "SSC50"
Then apply the coupon
Then insert order notes as "LAT_Test"

Then select new credit card
Then select the credit card checkbox
Then fill in the billing info
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then get the Run Dates Print In Order Line
Then get the Run Dates Online Ad Print in Order Line
Then note the 1 and 2 order price order lines
Then open a new tab and open the Adit URL
Then login to the Adit URL
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then capture the Rundates Value for SSCST
Then check if ADSS and ADIT rundates are same for SSCST
Then check if the ADSS and Adit orderlines price are same for SSCST
Then check if ADSS and ADIT total prices are same for SSCST
Then change the order to processed, if price is less than one dollar, else reject

@20
@Regression
@Regression03
Scenario: OSC verify Purchase and Renew Test without using Adit
Given Open the URL to test the "OSC"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then click on Review and Submit button
Then enter coupon code as "MG100ALL"
Then apply the coupon
Then insert order notes as "OSC_Purchase_Test"

Then select new credit card
Then select the credit card checkbox
Then fill in the billing info
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then click on the same order Id
Then click on Renew Link
Then clear the default dates
Then select one calendar date
Then click on Review and Submit button
Then enter coupon code as "MG100ALL"
Then apply the coupon
Then insert order notes as "OSC_Renew_Test_" along with the order id
Then select new credit card
Then select the credit card checkbox
Then fill in the billing info
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated



@21
@Regression
@Regression01
Scenario: ADSS2-1245 Validating if user is able to register in ADSS by putting password of certain different combinations
Given Open the URL to test the "CTCdashboard"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then check if the register window opens
Then enter an email in the Email Address field in the register window
Then enter a password not containing any special characters or capital letters in the Create Your Password field in the register window
Then enter a username in the Create Your Username field in the register window
Then enter data as "testfirst" in the "First Name" field in the register window
Then enter data as "testlast" in the "Last Name" field in the register window
Then enter data as "12345" in the "Zip Code" field in the register window
Then check the checkbox beside the "I agree to the Chicago Tribune's" section in the register window
Then click on the "Submit" button in the register window
Then check if registration is successful, and the dashboard page opens properly
Then click on Signout
When click on the "Sign Up" link at the top right corner of the page
Then check if the register window opens
Then enter an email in the Email Address field in the register window
Then enter a password not containing any capital letters, but containing one or more special characters in the Create Your Password field in the register window
Then enter a username in the Create Your Username field in the register window
Then enter data as "testfirst" in the "First Name" field in the register window
Then enter data as "testlast" in the "Last Name" field in the register window
Then enter data as "12345" in the "Zip Code" field in the register window
Then check the checkbox beside the "I agree to the Chicago Tribune's" section in the register window
Then click on the "Submit" button in the register window
Then check if registration is successful, and the dashboard page opens properly
Then click on Signout
When click on the "Sign Up" link at the top right corner of the page
Then check if the register window opens
Then enter an email in the Email Address field in the register window
Then enter a password not containing any special characters, but containing one or more capital letters in the Create Your Password field in the register window
Then enter a username in the Create Your Username field in the register window
Then enter data as "testfirst" in the "First Name" field in the register window
Then enter data as "testlast" in the "Last Name" field in the register window
Then enter data as "12345" in the "Zip Code" field in the register window
Then check the checkbox beside the "I agree to the Chicago Tribune's" section in the register window
Then click on the "Submit" button in the register window
Then check if registration is successful, and the dashboard page opens properly
Then click on Signout
When click on the "Sign Up" link at the top right corner of the page
Then check if the register window opens
Then enter an email in the Email Address field in the register window
Then enter a password containing one or more special characters and one or more capital letters in the Create Your Password field in the register window
Then enter a username in the Create Your Username field in the register window
Then enter data as "testfirst" in the "First Name" field in the register window
Then enter data as "testlast" in the "Last Name" field in the register window
Then enter data as "12345" in the "Zip Code" field in the register window
Then check the checkbox beside the "I agree to the Chicago Tribune's" section in the register window
Then click on the "Submit" button in the register window
Then check if registration is successful, and the dashboard page opens properly
Then click on Signout

@22
@Regression
@Regression03
Scenario Outline: ADSS2-1316 To validate if error message comes on entering invalid password combination during ADSS Registration
Given Open the URL to test the "CTCdashboard"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then check if the register window opens
Then in the password field in the registration window, enter password as "test1"
Then click on the in the Create Your Username input field in the registration window
Then check if the "<ErrorMsg1>" comes in the registration window
Then in the password field in the registration window, enter password as "abcdegfgh"
Then click on the in the Create Your Username input field in the registration window
Then check if the "<ErrorMsg2>" comes in the registration window
Then in the password field in the registration window, enter password as "12345678"
Then click on the in the Create Your Username input field in the registration window
Then check if the "<ErrorMsg3>" comes in the registration window
Then in the password field in the registration window, enter password as "12345"
Then click on the in the Create Your Username input field in the registration window
Then check if the "<ErrorMsg4>" comes in the registration window
Then in the password field in the registration window, enter password as "abcde"
Then click on the in the Create Your Username input field in the registration window
Then check if the "<ErrorMsg5>" comes in the registration window

Examples:
| ErrorMsg1 | ErrorMsg2 | ErrorMsg3 | ErrorMsg4 | ErrorMsg5 |
| Password must be a minimum 7 characters and contain at least 1 letter and 1 number. | Password must be a minimum 7 characters and contain at least 1 letter and 1 number. | Password must be a minimum 7 characters and contain at least 1 letter and 1 number. | Password must be a minimum 7 characters and contain at least 1 letter and 1 number. | Password must be a minimum 7 characters and contain at least 1 letter and 1 number. |

@23
@Regression
@Regression01
Scenario: Scenario for Cancel icon on Lost Your Password pop up window
Given Open the URL to test the "BCS"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
Then click on Forgot Passsword link in the new login window
Then click on the "Cancel" button present in the forgot password popup
When click on "Cancel" button in the login window
When click on the "Log In" link at the top right corner of the page

@24
@Regression
@Regression01
Scenario: Scenario for cancel link on Register pop up window
Given Open the URL to test the "OSC"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then click on the Close icon in the Register popup window
When click on the "Log In" link at the top right corner of the page

@25
@Regression
@Regression01
Scenario: Scenario for close button present on register pop up window
Given Open the URL to test the "OSC"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then click on the Close icon in the Register popup window
When click on the "Log In" link at the top right corner of the page

@26
@Regression
@Regression01
Scenario: Scenario for the links which are present on register pop up
Given Open the URL to test the "DPRObit"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then click on the "privacy policy" link in the Registration popup window
Then switch to the window containing the title "PRIVACY POLICY"
Then check if the text of the page title "PRIVACY POLICY" is also showing in the page header
Then close the window which has the current focus
Then switch to the window containing the title "Advertising self service"
Given Open the URL to test the "OSC"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then click on the "terms of service" link in the Registration popup window
Then switch to the window containing the title "TERMS OF SERVICE"
Then check if the text of the page title "TERMS OF SERVICE" is also showing in the page header
Then close the window which has the current focus
Then switch to the window containing the title "Advertising self service"
Given Open the URL to test the "CTC"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then click on the "terms & conditions" link in the Registration popup window
Then switch to the window containing the title "TERMS & CONDITIONS"
Then check if the text of the page title "TERMS & CONDITIONS" is also showing in the page header

@27
@Regression
@Regression01
Scenario: Scenario for View ad creation guidelines link in design or upload material section on configure page
Given Open the URL to test the "OSC"  Purchase Functionality
Then click Layout tab
Then click on the View Ad Creation Guidelines page in the Configure page
Then check if the Ad Creation Guidelines popup is showing


@28
@Regression
@Regression01
Scenario: Scenario for both cancel links
Given Open the URL to test the "BCS"  Purchase Functionality
Then click Layout tab
Then select Ad Size
Then click on upload button
Then check if the close icon is present in the upload popup
Then check if the "Cancel" button is present in the upload popup

@29
@Regression
@Regression01
Scenario: Scenario for cancel changes link and close button present on 'crop image' window
Given Open the URL to test the "BCS"  Purchase Functionality
Then click Layout tab
Then select Ad Size
Then click on upload button
Then check if the close icon is present in the upload popup
Then check if the "Cancel" button is present in the upload popup

@30
@Regression
@Regression01
Scenario: Scenario for Accept and continue button and close button present on crop image window
Given Open the URL to test the "BCS"  Purchase Functionality
Then click Layout tab
Then select Ad Size
Then click on upload button
Then check if the close icon is present in the upload popup
Then check if the "accept & continue" button is present in the upload popup
Then check if the "Cancel" button is present in the upload popup

@31
@Regression
@Regression01
Scenario Outline: Scenario for uploading file other than JPG, JPEG, PDF, PNG or GIF file on Design your material page
Given Open the URL to test the "CTC"  Purchase Functionality
Then click Layout tab
Then select the required Design Template
Then click on upload button
Then upload a file other than Jpg, Jpeg, Pdf, Png or Gif format
Then check if the appropriate error message "<ErrorMsg>" comes in the Upload PopUp in the Design your material page

Examples:
| ErrorMsg |
| File(s) has invalid extension, allowed extensions are - jpg, jpeg, pdf, png, gif |

@32
@Regression
@Regression01
Scenario Outline: Scenario for uploading file other than JPEG, TIFF or PDF
Given Open the URL to test the "HTF"  Purchase Functionality
Then click Layout tab
Then select Ad Size
Then click on upload button
Then upload an image other than Jpeg, Tiff or Pdf format
Then check if the appropriate error message "<ErrorMsg>" comes in the Upload PopUp in the Design your material page

Examples:
| ErrorMsg |
| File(s) has invalid extension, allowed extensions are - pdf, jpeg, tiff |

@33
@Regression
@Regression01
Scenario: Scenario for Print receipt link on Confirmation page
Given Open the URL to test the "BCS"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select Ad Size
Then click on upload button
Then upload photo
Then click on Review and Submit button
Then insert order notes as "BCS_Test"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then check if the "Receipt" button is present in the Confirmation page

@34
@Regression
@Regression01
Scenario: Scenario for Show password check box present on register pop up window

Given Open the URL to test the "CTCdashboard"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then check if the register window opens
Then enter a password not containing any special characters or capital letters in the Create Your Password field in the register window
Then click on the show or hide your password icon beside the password field in the register window
Then check if the password is displayed in the register window

@35
@Regression
@Regression01
Scenario: Scenario for order having zero dollar price
Given Open the URL to test the "BCS"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select Ad Size
Then click on upload button
Then upload photo
Then click on Review and Submit button
Then check if the total price is not showing as "$0.00"
Then enter coupon code as "ADSS100"
Then apply the coupon
Then check if the discounted price is now showing as "$0.00"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then check if the discounted price is now showing as "$0.00"

@36
@Regression
@Regression02
Scenario: Scenario for validation check on Family and verification contact information text box
Given Open the URL to test the "CTCObit"  Purchase Functionality
Then click on the Other Info tab
Then in the Other Info tab, set the "Funeral Home/Crematory Name" input field value as "test"
Then in the Other Info tab, set the "Funeral Home/Crematory Contact Phone" input field value as "9856457898"
Then set city as "Miami"
Then set contact name as "TestName"
Then set contact email as "aghosh@tribpub.com"

@37
@Regression
@Regression02
Scenario: On designing the template, verify that Preview, Edit Your Template, Start Over links behaviour
Given Open the URL to test the "CTC"  Purchase Functionality
Then click Layout tab
Then select the required Design Template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then fill in the text ctc
Then click on the Save and Continue button
Then check the image shown in the update Material section
Then click on the "zoom_in" button in the update Material section
Then check if the image is same in the Preview Ad Material popup
Then click on the close icon in the Preview Ad Material popup
Then click on the "edit" button in the update Material section
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then click on the Save and Continue button
Then click on the "undo" button in the update Material section
Then click on the "CONTINUE" button link in the Start Over or Please Confirm popup
Then select the required Design Template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then fill in the text ctc
Then click on the Save and Continue button
Then check the image shown in the update Material section

@38
@Regression
@Regression02
Scenario: On uploading image, no validation is present for Preview, Crop Material, Upload diff. material, Start over
Given Open the URL to test the "CTC"  Purchase Functionality
Then click Layout tab
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then check the image shown in the update Material section
Then click on the "zoom_in" button in the update Material section
Then check if the image is same in the Preview Ad Material popup
Then click on the close icon in the Preview Ad Material popup
Then click on the "crop" button in the update Material section
Then click on Accept and Continue button
Then click on the "fiber_new" button in the update Material section
Then upload photo
Then click on Accept and Continue button
Then click on the "undo" button in the update Material section
Then click on the "CONTINUE" button link in the Start Over or Please Confirm popup
Then select the required Design Template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then fill in the text ctc
Then click on the Save and Continue button
Then check the image shown in the update Material section

@39
@Regression
@Regression02
Scenario: Scenario for cancel changes link on edit your material page

Given Open the URL to test the "CTC"  Purchase Functionality
Then click on the Schedule tab
Then set zones
Then select one calendar date
Then click Layout tab
Then select the required Design Template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then fill in the text ctc
Then click on the Save and Continue button
Then set click through URL for Ad
Then click on Review and Submit button
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then enter coupon code as "MG100ALL"
Then apply the coupon
Then insert order notes as "HTF_Test"
Then select new credit card
Then select the credit card checkbox
Then fill in the billing info
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then note all the run dates online and note the first order price line for CTC Order Edit
Then click on the same order Id
Then click on Edit Link in Order Detail Page
Then click on Edit Link in Edit Ad Material Image
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then click on the "Cancel" button in the Edit Ad Material
Then click on the "CONTINUE" button link in the Start Over or Please Confirm popup
Then click on Edit Link in Edit Ad Material Image

@40
@Regression
@Regression02
Scenario: Edit Profile Page
Given Open the URL to test the "CTCdashboard"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on Edit Account Info link
Then edit some info in the Account
Then click on Save button in the edit window
Then click on Signout
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on Edit Account Info link
Then edit some info in the Account
Then click on Save button in the edit window
Then click on Signout

@41
@Regression
@Regression02
Scenario: Scenario for cancel link on Edit Profile page
Given Open the URL to test the "CTCdashboard"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on Edit Account Info link
Then edit some info in the Account
Then click on the Close icon in the Edit Account Info window
Then click on the "CONTINUE" button link in the Start Over or Please Confirm popup
Then click on Signout
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on Edit Account Info link
Then edit some info in the Account
Then click on the Close icon in the Edit Account Info window
Then click on the "CONTINUE" button link in the Start Over or Please Confirm popup
Then click on Signout

@42
@Regression
@Regression02
Scenario: Check for MY ACCOUNT, DRAFTS, ORDERS link.
Given Open the URL to test the "BCS"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the User Profile link
Then wait for 6 seconds
Then click on the "Profile" link from the User Profile options
Then wait for 10 seconds
Then click on "DRAFTS" menu option in My Dashboard
Then check if datas are present under the menu option in My Dashboard
Then click on "ORDERS" menu option in My Dashboard
Then check if datas are present under the menu option in My Dashboard
Then click on Edit Account Info link
Then edit some info in the Account

@43
@Regression
@Regression02
Scenario: Scenario for preview ad refresh icon
Given Open the URL to test the "CTC"  Purchase Functionality
Then click Layout tab
Then select the required Design Template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then check the image shown in the Material Preview Section in the Design Your Material
Then click on the "refresh" icon in the Material Preview Section in the Design Your Material
Then wait for 10 seconds
Then check if the same image is shown in the Material Preview Section in the Design Your Material
Then check the image shown in the Material Preview Section in the Design Your Material
Then click on the "refresh" icon in the Material Preview Section in the Design Your Material
Then wait for 10 seconds
Then check if the same image is shown in the Material Preview Section in the Design Your Material


@44
@Regression
@Regression02
Scenario: Scenario for Renew- Next Respective date selection on renewing the ad
Given Open the URL to test the "OSC"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then note the next available date
Then click Layout tab
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then click on Review and Submit button
Then enter coupon code as "MG100ALL"
Then apply the coupon
Then insert order notes as "OSC_Purchase_Test"

Then select new credit card
Then select the credit card checkbox
Then fill in the billing info
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then note all the run dates online and note the first order price line
Then click on the same order Id
Then click on Renew Link
Then wait for 15 seconds
Then check if the earlier noted date is selected and highlighted in yellow

@45
@Regression
@Regression02
Scenario: Verification needed for order summary whether it is displaying items correctly Selected on configure page 

Given Open the URL to test the "OSC"  Purchase Functionality
Then click on the expand icon in the Current Total section
Then check if on expanding the icon in the Current Total section, details are displayed

@46
@Regression
@Regression02
@LOCTest
Scenario: Scenario for Vendor with LOC

Given Open the URL to test the "BCS"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select Ad Size
Then click on upload button
Then upload photo
Then click on Review and Submit button
Then insert order notes as "BCS_Test"
Then select the Payment option as "Bill to my account"
Then check if the Billing options are displayed
Then select the Payment option as "Credit Card"
Then check if the Credit Card options are displayed

@47
@Regression
@Regression02
@LOCTest
Scenario: Scenario for selecting Type of card using the icon displayed above the text box of type of card

Given Open the URL to test the "BCS"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select Ad Size
Then click on upload button
Then upload photo
Then click on Review and Submit button
Then insert order notes as "BCS_Test"
Then select the Payment option as "Credit Card"
Then check if the Credit Card options are displayed
Then choose the "Visa" Credit Card option
Then check if the "Use a new credit card" is not checked

@48
@Regression
@Regression01
Scenario: AAS9624 Validate Print receipt popup should be closed if user clicks on cancel button

Given Open the URL to test the "CTC"  Purchase Functionality
Then click on the Schedule tab
Then set zones
Then select one calendar date
Then click Layout tab
Then select the required Design Template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then fill in the text ctc
Then click on the Save and Continue button
Then set click through URL for Ad
Then click on Review and Submit button
Then wait for 8 seconds
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then enter coupon code as "MG100ALL"
Then apply the coupon
Then insert order notes as "CTC_Test"

Then select new credit card
Then select the credit card checkbox
Then fill in the billing info
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then wait for 3 seconds
Then click on the "Receipt" button in the Confirmation page
Then wait for 5 seconds
Then check if 3 windows are present
Then switch to the 3 tab
Then wait for 3 seconds
Then close the focussed tab
Then wait for 3 seconds
Then check if 1 windows are present
Then switch to the 1 tab
Then click on Signout
Then wait for 3 seconds
Given Open the URL to test the "BCS"  Purchase Functionality
Then wait for 5 seconds
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select Ad Size
Then click on upload button
Then upload photo
Then click on Review and Submit button
Then insert order notes as "BCS_Test"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then wait for 5 seconds
Then click on the "Receipt" button in the Confirmation page
Then wait for 5 seconds
Then check if 3 windows are present
Then switch to the 3 tab
Then wait for 3 seconds
Then close the focussed tab
Then wait for 3 seconds
Then check if 1 windows are present
Then switch to the 1 tab
Then click on Signout


@49
@Regression
@Regression02
Scenario: AAS-9428 Validate error message for Material Builder error for choosing particular Adsize for different BUs

Given Open the URL to test the "BCS" Purchase Functionality having "CNGTotalMktHSJobsCD" package
Then check if the not opened error messages are showing in the page
| Sorry, an error has occurred. |
| Error code: 3002 |
| Suggestions: |
| Contact customer support at AdSS-AdSupport@tribpub.com for additional information. Please include the error code and description in your email. |
| Please try your request at a later time. |
Given Open the URL to test the "HTF" Purchase Functionality having "HTF-LGL-FRSUN" package
Then check if the not opened error messages are showing in the page
| Sorry, an error has occurred. |
| Error code: 3018 |
| Suggestions: |
| Contact customer support at AdSS-AdSupport@tribpub.com for additional information. Please include the error code and description in your email. |
| Please try your request at a later time. |
Given Open the URL to test the "HTF" Purchase Functionality having "HTF-LGL-FORE" package
Then check if the not opened error messages are showing in the page
| Sorry, an error has occurred. |
| Error code: 3002 |
| Suggestions: |
| Contact customer support at AdSS-AdSupport@tribpub.com for additional information. Please include the error code and description in your email. |
| Please try your request at a later time. |

@50
@Regression
@Regression02
Scenario: AAS9638 Validate new Master card should be added starting within a given range and having sixteen digitals in order details page
Given Open the URL to test the "CTC"  Purchase Functionality
Then click on the Schedule tab
Then set zones
Then select one calendar date
Then click Layout tab
Then select the required Design Template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then fill in the text ctc
Then click on the Save and Continue button
Then set click through URL for Ad
Then click on Review and Submit button
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then enter coupon code as "MG100ALL"
Then apply the coupon
Then insert order notes as "HTF_Test"
Then select new credit card
Then select the credit card checkbox
Then fill in the billing info having credit card number starting with two, and some additional details in the expiry date and CVV
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated

@51
@Regression
@Regression02
@LOCTest
Scenario: Validate no yellow messaging on bottom shows configuration error on Layout for completed ad
Given Open the URL to test the "BCS"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then check if the yellow color highlighted error message is showing on bottom
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then select Ad Size
Then click on upload button
Then upload photo
Then check if the yellow color highlighted error message is not showing on bottom
Then click on Review and Submit button
Given Open the URL to test the "CTC"  Purchase Functionality
Then click on the Schedule tab
Then check if the yellow color highlighted error message is showing on bottom
Then click on the Schedule tab
Then set zones
Then select one calendar date
Then click Layout tab
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then set click through URL for Ad
Then note the Draft Id
Then click Layout tab
Then check if the yellow color highlighted error message is not showing on bottom
Then click on Review and Submit button


@52
@Regression
@Regression02
Scenario: AAS9876 Validate if a button is present that allows a customer to clear all the formatting in their dynamic text box
Given Open the URL to test the "NDNOBT"  Purchase Functionality
Then click Layout tab
Then select the required Design Template
Then clear the text in the textarea paragraph field
Then select the "bold" section above the textarea paragraph field
Then enter text as "test" in the textarea paragraph field, &, hit Enter
Then check if the entered text is highlighted in bold
Then unselect the "bold" section above the textarea paragraph field
Then select the "italic" section above the textarea paragraph field
Then enter text as "Testing" in the textarea paragraph field, &, hit Enter
Then check if the entered text is highlighted in italic
Then unselect the "italic" section above the textarea paragraph field
Then select the "underline" section above the textarea paragraph field
Then enter text as "Test It" in the textarea paragraph field, &, hit Enter
Then check if the entered text is highlighted in underline
Then unselect the "underline" section above the textarea paragraph field
Then click the clean button above the textarea paragraph field
Then click on the "CONTINUE" option from the Confirm Text Formatting popup
Then check if none of the texts are highlighted in bold, italic or underline

@53
@Regression
@Regression02
Scenario: AAS9689 Validate if only SCHEDULE & OTHER INFO Tabs are present for online only packages that do not need layout tab
Given Open the URL to test the "CTCOBITTESTONLINE"  Purchase Functionality
Then check if the Layout tab is not visible	

@54
@Regression
@Regression02
Scenario: AAS9879 Validation of adding special characters to Digital Settings Option, by adding Special Characters
Given login to the "DigitalSettings" page
Then wait for 5 seconds
Then enter new credentials in the windows authentication popup in the new incognito window
Then click on the expand icon beside the "HTML Editor Style Management" field in the Digital Settings page
Then click on the dropdown in the "HTML Editor Style Management" section, &, choose value as "New York Daily News (NDN)"
Then check the mentioned checkboxes if not checked
| bold |
| italic |
| underline |
| special characters |
Then clear the textareas beside the mentioned checkboxes
| bold |
| italic |
| underline |
| special character |
Then click on the "Save" button in the Digital Settings page
Then click on the "Ok" button in the Confirmation popup in the Digital Settings page
Given Open the URL to test the "NDNOBT"  Purchase Functionality
Then click Layout tab
Then select the required Design Template
Then check if the mentioned symbols are present in the Template Page
| Heart |
| Star |
| Diamond |
| Bullet |

@55
@Regression
@Regression02
Scenario: AAS9611 Validate Edit ad material page from adss is returning to Adit when we update the material opening in a new tab from insertion line for online package
Given Open the URL to test the "CTC"  Purchase Functionality
Then click on the Schedule tab
Then set zones
Then select one calendar date
Then click Layout tab
Then select the required Design Template
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then fill in the text ctc
Then click on the Save and Continue button
Then set click through URL for Ad
Then click on Review and Submit button
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then enter coupon code as "MG100ALL"
Then apply the coupon
Then insert order notes as "HTF_Test"

Then select new credit card
Then select the credit card checkbox
Then fill in the billing info
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then note all the run dates online and note the first order price line
Then open a new tab and open the Adit URL
Then login to the Adit URL
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get the order pub dates in order line
Then note all the adit orderlines price
Then check if ADSS and ADIT rundates are same
Then check if the ADSS and Adit orderlines price are same
Then check if the ADSS and Adit totalPrice price are same for non-Loc users
Then click on the Material Preview Image in the Insertions table
Then click on the Open In New Window button in the Material Review Pop Up
Then switch to the 3 tab
Then click on the Edit link in the Material Order Id in the new window
Then click on the "file_upload" button in the Edit Order Page in the new window
Then upload photo
Then click on Accept and Continue button
Then click on the "SUBMIT" button in the Edit Order Page in the new window
Then wait for 20 seconds
Then change the order to processed, if price is less than one dollar, else reject

@56
@Regression
@Regression02
Scenario: ADSS2-498 TC_02 Validating if legacy auto insertions are getting generated on placing orders via ADSS, on selecting Legacy
  
 Given open the Adit URL
 Then login to the Adit URL
 Then click on the Product Admin Module
 Then click on "Orlando Sentinel" from the list of available BUs
Then click on Auto Insertion Management tab from General Information Section in the BU
Then wait for 30 seconds
Then in the Auto Insertion Management table, double click on the data having the "Matched Section" as "Main News", and, "Matched Ad Type" as "Block"
Then in the Update popup, select the Type as "OwnLocal", and Save if not selected
Given Open the URL to test the "OSC1"  Purchase Functionality
When click on the "Log In" link at the top right corner of the page
When enter required "NonLocUser" in the "email" field in the sign in window
When enter required "NonLocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then click on Review and Submit button
Then enter coupon code as "MG100ALL"
Then apply the coupon
Then insert order notes as "OSC_Purchase_Test"

Then select new credit card
Then select the credit card checkbox
Then fill in the billing info
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then open a new tab and open the Adit URL
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then click on the Approve button, followed by Confirm Approve button in the popup
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then check if the order Status is now showing as "Processed"
  
 @Sanity
  Scenario: OSC Purchase Test
  Given Open the URL to test the "OSC"  Purchase Functionality
  When click on the "Log In" link at the top right corner of the page
  When enter required "LocUser" in the "email" field in the sign in window
  When enter required "LocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  Then click on the Schedule tab
  Then clear the default dates
  Then select one calendar date
  Then click Layout tab
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then click on Review and Submit button  
  Then enter coupon code as "MG100ALL"
  Then apply the coupon
  Then insert order notes as "OSC_Purchase_Test"
  Then check the Proof Read checkbox
  Then click book ad button in the confirmation page
  Then check if the order number is generated
  Then note all the run dates online and note the first order price line
  Then open a new tab and open the Adit URL
  Then login to the Adit URL
  Then open the "Order Entry" link section in Adit list link
  Then search for the same Order Number in Adit
  Then click on the same Order Number link from the table
  Then get the order pub dates in order line
  Then note all the adit orderlines price
  Then check if ADSS and ADIT rundates are same
  Then get the total price adit
	Then change the order to processed, if price is less than one dollar, else reject
	Then search for the same Order Number in Adit
	Then click on the same Order Number link from the table
  Then Kill Order from Adit
  

@Sanity
@registration
Scenario: Customer registration in ADSS
Given Open the URL to test the "CTCdashboard"  Purchase Functionality
When click on the "Sign Up" link at the top right corner of the page
Then check if the register window opens
Then enter an email in the Email Address field in the register window
Then enter a password not containing any special characters or capital letters in the Create Your Password field in the register window
Then enter a username in the Create Your Username field in the register window
Then enter data as "testfirst" in the "First Name" field in the register window
Then enter data as "testlast" in the "Last Name" field in the register window
Then enter data as "12345" in the "Zip Code" field in the register window
Then check the checkbox beside the "I agree to the Chicago Tribune's" section in the register window
Then click on the "Submit" button in the register window
Then check if registration is successful, and the dashboard page opens properly
Then click on Signout
  
#realtive product automation
@57
@Regression
@relativeProductTC1
Scenario: Relative Product test Display as primary
Given open the Adit URL
Then login to the Adit URL
Then Search For the Relative product group for product group "Display" in Adit
Then sort by product in relative product group
Then copy the query String of product "display" in relative product group
Then copy the query Strings of relative products for product type "display" in relative product group
Then Open the Related Product QueryString URL in ADSS for "SSC" view
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then wait for 12 seconds
Then Verify all the relative product displayed in ADSS
Then Scroll to Schedule tab
Then click on the Schedule tab
Then clear the default dates
Then select one calendar date
Then click Layout tab
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then wait for 5 seconds
Then Add or remove product 1 from relative product
Then verify header value of product count displayed 2 of 2 in relative product
Then verify button Value changed to "DELETE" for product 1 after addition
Then Add or remove product 2 from relative product
Then verify header value of product count displayed 3 of 3 in relative product
Then verify button Value changed to "DELETE" for product 1 after addition
Then verify button Value changed to "DELETE" for product 2 after addition
Then Refresh the Page
Then click on Previous product product in multiple product page
Then wait for 3 seconds
Then verify header value of product count displayed 2 of 3 in relative product
Then Refresh the Page
Then click on Previous product product in multiple product page
Then wait for 3 seconds
Then verify header value of product count displayed 1 of 3 in relative product
Then Refresh the Page
Then click on Next product product in multiple product page
Then wait for 3 seconds
Then verify header value of product count displayed 2 of 3 in relative product
Then Refresh the Page
Then click on Next product product in multiple product page
Then wait for 3 seconds
Then verify header value of product count displayed 3 of 3 in relative product
#Then Refresh the Page
Then Add or remove product 2 from relative product
Then click Continue on Delete product in multiple product page
Then verify header value of product count displayed 1 of 2 in relative product
Then verify button Value changed to "DELETE" for product 1 after addition
Then verify button Value changed to "ADD" for product 2 after addition
Then Refresh the Page
Then Scroll to Progress summary
Then click on the Schedule tab 2 in multiple product
Then clear the default dates
Then select one calendar date
Then click on the Layout tab 2 in multiple product
Then verify and configure material in multiple product
#Then click on Review and Submit button 
Then click on Review and Submit button  
#Then enter coupon code as "435PROD100"
#Then apply the coupon
Then insert order notes as "OSC_Purchase_Test"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then note all the run dates online and note the first order price line
Then open a new tab and open the Adit URL
#Then login to the Adit URL
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get the order pub dates in order line
Then note all the adit orderlines price
Then check if ADSS and ADIT rundates are same
#Then check if the ADSS and Adit totalPrice price are same for non-Loc users
Then Approve the order

@58
@Regression
@relativeProductTC2
Scenario: Relative Product test Online as primary
Given open the Adit URL
Then login to the Adit URL
Then Search For the Relative product group for product group "Online" in Adit
Then sort by product in relative product group
Then sort by product in relative product group
Then sort by product in relative product group
Then copy the query String of product "online" in relative product group
Then copy the query Strings of relative products for product type "online" in relative product group
Then Open the Related Product QueryString URL in ADSS for "OSC" view
When click on the "Log In" link at the top right corner of the page
When enter required "LocUser" in the "email" field in the sign in window
When enter required "LocPassword" in the "password" field in the sign in window
When click on "Log in" button in the login window
Then wait for 12 seconds
Then Verify all the relative product displayed in ADSS
Then Scroll to Schedule tab
Then click on the Schedule tab
#Then clear the default dates
Then select one calendar date
Then click Layout tab
Then Type Click through url for online product
Then click on upload button
Then upload photo
Then click on Accept and Continue button
Then wait for 5 seconds
Then Add or remove product 1 from relative product
Then verify header value of product count displayed 2 of 2 in relative product
Then verify button Value changed to "DELETE" for product 1 after addition
Then Add or remove product 2 from relative product
Then verify header value of product count displayed 3 of 3 in relative product
Then verify button Value changed to "DELETE" for product 1 after addition
Then verify button Value changed to "DELETE" for product 2 after addition
Then Refresh the Page
Then click on Previous product product in multiple product page
Then wait for 3 seconds
Then verify header value of product count displayed 2 of 3 in relative product
Then Refresh the Page
Then click on Previous product product in multiple product page
Then wait for 3 seconds
Then verify header value of product count displayed 1 of 3 in relative product
Then Refresh the Page
Then click on Next product product in multiple product page
Then wait for 3 seconds
Then verify header value of product count displayed 2 of 3 in relative product
Then Refresh the Page
Then click on Next product product in multiple product page
Then wait for 3 seconds
Then verify header value of product count displayed 3 of 3 in relative product
Then Refresh the Page
Then Add or remove product 2 from relative product
Then click Continue on Delete product in multiple product page
Then verify header value of product count displayed 1 of 2 in relative product
Then verify button Value changed to "DELETE" for product 1 after addition
Then verify button Value changed to "ADD" for product 2 after addition
Then Refresh the Page
Then Scroll to Progress summary
Then click on the Schedule tab 2 in multiple product
Then select one calendar date
Then click on the Layout tab 2 in multiple product
Then verify and configure material in multiple product
Then click on Review and Submit button  
#Then enter coupon code as "435PROD100"
#Then apply the coupon
Then insert order notes as "OSC_Purchase_Test"
Then check the Proof Read checkbox
Then click book ad button in the confirmation page
Then check if the order number is generated
Then note all the run dates online and note the first order price line
Then open a new tab and open the Adit URL
Then open the "Order Entry" link section in Adit list link
Then search for the same Order Number in Adit
Then click on the same Order Number link from the table
Then get the order pub dates in order line
Then note all the adit orderlines price
Then check if ADSS and ADIT rundates are same
#Then check if the ADSS and Adit totalPrice price are same for non-Loc users
Then Approve the order


  @59
  @Regression
  @Smoke
  @Regression03
  @Smoke1
  Scenario: CTC Verify Purchase Test
  Given Open the URL to test the "CTC"  Purchase Functionality
  Then click on the Schedule tab
  Then set zones
  Then select one calendar date
  Then click Layout tab
  Then select the required Design Template
  Then click on upload button
  Then upload photo
  Then click on Accept and Continue button
  Then fill in the text ctc
  Then click on the Save and Continue button
  Then set click through URL for Ad
  Then click on Review and Submit button
  When click on the "Log In" link at the top right corner of the page
  When enter required "NonLocUser" in the "email" field in the sign in window
  When enter required "NonLocPassword" in the "password" field in the sign in window
  When click on "Log in" button in the login window
  #Then enter coupon code as "MG100ALL"
  #Then apply the coupon
  Then insert order notes as "HTF_Test"
  Then select new credit card
  Then select the credit card checkbox
  Then fill in the billing info
  Then check the Proof Read checkbox
   Then click book ad button in the confirmation page