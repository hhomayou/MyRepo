#Author: hhomayounfar@qaconsultants.com
Feature: Trello Due date operations

  Scenario: Set/Change a due date in a card
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User clicks on duedate button
    And User sets the date and click on save button
    And Date is set

  Scenario: Remove a due date from the card
    Given User clicks on first card
    When User clicks on duedate button
    Then User clicks on remove button
    And Duedate is removed
    And Go back to main page
    