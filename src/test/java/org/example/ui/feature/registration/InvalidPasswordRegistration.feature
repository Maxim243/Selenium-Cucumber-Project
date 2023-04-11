Feature: Register a new invalid user

  Scenario Outline: Verify that user can't register with invalid password and valid username
    Given CRA Environment Login Page is opened
    When Login with valid environment credentials
    Then User login page should be displayed
    When Entering valid "<username>" and invalid "<password>"

    And Clicking on Register
    Then “Registration failed! Check your username or password.” message is displayed.

    Examples:
      | username | password  |
      | Maxim123 | john1     |
      | Maxim123 | JOHN12345 |
      | Maxim123 | john12345 |
      | Maxim123 | Johnjohn  |
      | Maxim123 |           |