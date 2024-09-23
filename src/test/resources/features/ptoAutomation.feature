Feature: PTO Module test cases


  Scenario: Client login and fill out the pto form and move request till delivered
    Given user is on speed platform
    When user login with client credentials
    Then user should see the PTO form
    When user fills out the PTO form
    And user submits the form
    Then user will logout from profile
    Then user login with wattmonk admin credentials
    Then user clicks on pto module
    Then wattmonk admin will accept and assign request to operator in pto new section
    Then user will logout from profile
    Then user login with interconnection operator credentials
    And operator will complete the process
    Then user will logout from profile
    When user login with client credentials
    Then client will send the request for pto
    Then user will logout from profile
    Then user login with interconnection operator credentials
    Then operator will complete the pto process
    Then user will logout from profile
    When user login with client credentials
    Then client will check the request is completed
