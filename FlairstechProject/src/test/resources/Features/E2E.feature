Feature: E2E Scenario on OrangeHRM
  Scenario: E2E Scenario on OrangeHRM
    Given Login to Orange HRM Website
    
    When Click on Admin tab on the left side menu
    And Get the number of records found
    And Click on add button
    And Fill the required data
    And Click on save button
    Then Verify that the number of records increased by 1
    
    
    When Delete the new user
    Then Verify that the number of records decreased by 1
    
    
    
    
    