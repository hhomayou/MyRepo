#Author: hhomayounfar@qaconsultants.com
Feature: Trello Board  

  Scenario: Login to Trello
    Given User opens Trello login page
    When User enters login info
    Then List of teams and boards is displayed in the main page

  Scenario: Create a new board
    Given User is in the main page
    When User clicks on create new board box
    Then User enters Title and selects first Team
    And The new board is created

  Scenario: Delete the board
    Given User is in the board page
    When User clicks on more link
    Then User clicks Close Board link and Close button
    And Page displays the board is closed 
    And User clicks on Permanently Delete Board link and delete button
    And Board is deleted and page displays Board not found

  Scenario: Test ends
    Given The test is over
    When There is no more test
    Then The page closes
    