#Author: hhomayounfar@qaconsultants.com
Feature: Trello Checklist operations

  Scenario: Create a checklist on a card
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User click on Add checklist
    And User enters a the new checklist title and click on Add button
    And Checklist is added to the card

  Scenario: Delete the checklist from the card
    Given User is in the card detail popup
    When User clicks on delete link
    Then Checklist is deleted
		And User closes the card popup
