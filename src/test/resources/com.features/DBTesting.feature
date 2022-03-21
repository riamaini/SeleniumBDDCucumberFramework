Feature: DB Testing feature

  Scenario: Validate the DB Records for number of cities in Afghanistan
    Given the DB connection is set up
    When the user executes the query for finding the number of cities in Afghanistan
    Then the DB should fetch the records and provide the value as 4

