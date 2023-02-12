Feature: Example feature
  Scenario: Verify data in database
    Given the database is initialized
    When data is inserted into the database
    Then the data can be retrieved from the database
