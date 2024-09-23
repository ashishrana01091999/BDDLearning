Feature: Fill Basic Form


  Scenario: Fill and submit PTO form
    Given I open the PTO form
    When I fill the form with data from Google Sheets
    And I upload the required documents
    Then I submit the form
    And I verify the form submission


