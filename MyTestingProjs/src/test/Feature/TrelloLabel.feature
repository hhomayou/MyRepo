#Author: hhomayounfar@qaconsultants.com
Feature: Trello Label operations

  Scenario: Assign a label to a card
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User click on labels button
    And User toggle blue label
    And Label is added to the card

  Scenario: Unassign a label from the card
    Given User clicks on first card
    When User click on labels button
    Then User toggle blue label
    And Label is removed from the card
    And Go back to main page
    