#Author: hhomayounfar@qaconsultants.com
Feature: Trello displaying a board and its card

  Scenario: Login and display teams
    Given List of teams and boards is displayed in the main page
    When User clicks on MyBoard box
    Then Page MyBoard is loaded and it displays all the info

  Scenario: Click on a card to display info
    Given User clicks on first card
    When Card info is loaded and popped up
    Then User closes the card popup
