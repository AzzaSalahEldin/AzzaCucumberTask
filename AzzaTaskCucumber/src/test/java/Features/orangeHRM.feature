
  Feature: Test the OrangeHRM functionality
  @Sanity @Regression
  Scenario: Login with valid username and password
    Given User opens the website and navigates to the login page
    When User enters valid username "Admin" and password "admin123"
    Then User should be navigated to the home page

  @Regression
  Scenario: Navigate to Admin tab and get initial user record count
    When User clicks on the Admin tab in the left menu
    Then User gets and stores the current number of user records

  @Regression
  Scenario: Add a new user to the system
    When User clicks on the Add button
    And User fills in the required data:
      | Employee Name | Username     | Password   | Confirm Password |
      | Orange test      | TestEmpUser  | P@ssw0rd  | P@ssw0rd        |
    And User clicks the Save button
    Then The number of user records should increase by 1

  @Regression
  Scenario: Search for the newly added user
    When User searches for the username "TestEmpUser"
    Then The user "TestEmpUser" should appear in the search results

  @Regression
  Scenario: Delete the newly added user and verify
    When User deletes the user "TestEmpUser"
    Then The number of user records should decrease by 1