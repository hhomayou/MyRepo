#Author: hhomayounfar@qaconsultants.com
Feature: Trello Due date operations

  Scenario: Add a due date to a card
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User clicks on duedate button
    And User sets the date and click on save button
    And Date is set
		And Users closes the duedate popup

  Scenario: Remove a due date from the card
    Given User clicks on duedate button
    When User clicks on remove button
    And Duedate is removed
		And Users closes the duedate popup
    