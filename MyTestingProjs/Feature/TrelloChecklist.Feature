#Author: hhomayounfar@qaconsultants.com
Feature: Trello Checklist operations

  Scenario: Login to Trello
    Given User opens Trello login page
    When User enters login info
    Then List of teams and boards is displayed in the main page

  Scenario: Create a checklist on a card
    Given User is in the board page
    When User clicks on a card
    Then Card details popup appears
    When User click on Add checklist
    And User enters a the new checklist title and click on Add button
    Then Checklist is added to the card 

  Scenario: Delete the checklist from the card
    Given User is in the card detail popup
    When User clicks on delete link
    Then Checklist is deleted

  Scenario: Test ends
    Given The test is over
    When There is no more test
    Then The page closes
