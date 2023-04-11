Feature: Limited Version

  Scenario: Verify that Limited version has a link to registration form
    Given CRA Environment Login Page is opened
    When Login with valid environment credentials
    Then User login page should be displayed
    When Clicking on Limited Version Button
    Then The CRA Limited Version should be displayed
    When Clicking on SingUp Button
    Then The Registration form should be opened.
