Feature: Amazon Login page feature

  //Provide user name and password
  Scenario: Login with correct credentials
    Given user is on the Amazon login page
    When user enters the username ""
    And user click on Continue button
    And user enters the password ""
    And user clicks on Sign-In button
    Then the user is logged in to the application
