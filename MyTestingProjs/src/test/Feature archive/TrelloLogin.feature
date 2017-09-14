#Author: hhomayounfar@qaconsultants.com
Feature: Trello login and viewing a board and a card

  Scenario: Login to Trello
    Given User opens Trello login page
    When User enters login info
    Then List of teams and boards is displayed in the main page

  Scenario: Click on Board to display info
    Given User is in the main page
    When User clicks on MyBoard box
    Then Page MyBoard is loaded and it displays all the info

  Scenario: Click on a card to display info
    Given User clicks on first card
    When Card info is loaded and popped up
    Then User closes the card popup
 
  Scenario: Test ends
  	When The test is over
    Then The page closes
