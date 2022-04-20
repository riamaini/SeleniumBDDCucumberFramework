Feature: Amazon Home page feature

  //Provide user name and password
  Scenario: Login with correct credentials and add the product to card and checkout
    Given user is on the Amazon login page
    When user enters the username ""
    And user click on Continue button
    And user enters the password ""
    And user clicks on Sign-In button
    Then the user is logged in to the application
    And the user navigates to Health, Household and Personal Care section
    And the user navigates to Diet And Nutrition Section
    And the user selects Whey Protein Powders
    When the user selects the quantity "2" of the product and clicks on Add to Cart
    Then the user is shown the option of Proceed to Buy
