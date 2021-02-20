Feature: Verify Dresses page details

  
   Scenario: Verify product name, price and quantity
      Given The User is on the homepage
      When The user clicks on Dresses page link
      Then The user should see the following dress subcategories
           | Casual Dresses  |
           | Evening Dresses |
           | Summer Dresses  |
      And  The page title should be "Dresses - My Store"


      
      
     
  