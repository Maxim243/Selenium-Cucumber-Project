Feature: Try to register an existing user

  Scenario: Verify that user can't register with existing username

    Given CRA Environment Login Page is opened
    When Login with valid environment credentials
    Then User login page should be displayed
    When Entering valid username "John193" and valid password "Maxim123"
    And Clicking on Register
    Then HomePage should be displayed
    And Clicking on Logout
    When Entering valid username "John193" and valid password "Maxim123"
    And Clicking on Register
    Then “Registration failed! Check your username or password.” message is displayed.
