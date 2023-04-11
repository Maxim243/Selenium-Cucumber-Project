Feature: Register a new invalid user

  Scenario Outline: Register a new invalid user with invalid username and valid password
    Given CRA Environment Login Page is opened
    When Login with valid environment credentials
    Then User login page should be displayed
    When Entering invalid "<username>" and valid "<password>"

    And Clicking on Register
    Then “Registration failed! Check your username or password.” message is displayed.

    Examples:
      | username | password
      | John     | Maxim123
      | john12   | Maxim123
      | JOHN12   | Maxim123
      | JohnMike | Maxim123
      |          | Maxim123