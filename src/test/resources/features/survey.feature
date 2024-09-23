Feature: Survey Module test cases


  Scenario: Client login and fill out the survey request form
    Given user is on speed platform
    When user login with client credentials
    Then user should see the survey add form
    Then user fill out the survey request form and submit
    Then user will logout from profile
