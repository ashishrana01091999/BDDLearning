Feature: Permitting Module test cases


  Scenario: Client login and fill out the permitting form and move the request to delivered
    Given user is on speed platform
    When user login with client credentials
    Then user should see the Permitting form
    Then user will fill the form and submit the form
    Then user will logout from profile
    Then user login with wattmonk admin credentials
    Then user clicks on permitting module
    Then wattmonk admin will accept and assign request to operator in permitting new section
    Then user will logout from profile
    Then user login with interconnection operator credentials
    Then user clicks on permitting module
    Then operator will complete the pending permitting process
    Then operator will submit request for final inspection
    And operator will complete the final inspection for permitting
    Then user will logout from profile
    Then user login with client credentials
    Then client will check permitting request is completed