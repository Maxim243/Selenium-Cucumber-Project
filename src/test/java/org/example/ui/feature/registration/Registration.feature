Feature: Register a new valid user

  Scenario: Try to Register a new valid user with valid username and valid password

    Given CRA Environment Login Page is opened
    When Login with valid environment credentials
    Then User login page should be displayed
    When Entering valid username "John131" and valid password "Maxim123"
    And Clicking on Register
    Then HomePage should be displayed
    And Clicking on Unregister

