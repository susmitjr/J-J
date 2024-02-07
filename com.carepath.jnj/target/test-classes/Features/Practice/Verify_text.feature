Feature: Verify Text

Scenario: TC001_Verify that an element contains specific text
    
    Given Test data loaded from file "src/test/resources/TestData/One_Program.csv" for the Case "TC001_OneProgram" 
    
    When I navigate to URL
    Then I wait for 2 seconds
    Then I refresh the page

    Then I wait for 5 seconds
    Then I verify that "headerElement" is displayed
    And I capture Screenshot "Home"

    # When I enter "username" into the "usernameInput" field
    # And I enter "password" into the "passwordInput" field
    # And I click on the "loginButton" button
    # Then I verify that the "welcomeMessage" contains text "Welcome, User!"