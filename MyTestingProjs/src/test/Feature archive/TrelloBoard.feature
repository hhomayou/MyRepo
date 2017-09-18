#Author: hhomayounfar@qaconsultants.com
Feature: Trello Board operations 

  Scenario: Create a new board
    Given User clicks on create new board box
    When User enters Title and selects first Team
    Then The new board is created

  Scenario: Delete the board
    Given User is in the board page
    When User clicks on more link
    Then User clicks Close Board link and Close button
    And Page displays the board is closed 
    And User clicks on Permanently Delete Board link and delete button
    And Board is deleted and page displays Board not found
    