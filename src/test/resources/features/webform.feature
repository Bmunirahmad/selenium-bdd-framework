Feature: Web Form Validation

  Scenario: User fills out and submits the web form successfully
    Given the user navigates to the Selenium Web Form page
    When the user fills in all required fields
    And submits the form
    Then a confirmation message is displayed

  Scenario: Validate the disabled input field cannot be interacted with
    Given the user navigates to the Selenium Web Form page
    When the user checks the disabled input field
    Then the field should be disabled

  Scenario: Validate radio button exclusivity behavior
    Given the user navigates to the Selenium Web Form page
    When the user selects the default radio button
    Then the previously selected radio button should be deselected

  Scenario: Validate the readonly field is indeed readonly
    Given the user navigates to the Selenium Web Form page
    Then the readonly input field should not be editable

  Scenario: Validate return to index link navigates correctly
    Given the user navigates to the Selenium Web Form page
    When the user clicks on the return to index link
    Then the user should be navigated to "index.html"