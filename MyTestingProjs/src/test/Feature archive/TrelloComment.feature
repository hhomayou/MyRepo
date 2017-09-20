#Author: hhomayounfar@qaconsultants.com
Feature: Trello Comment operations

  Scenario: Add a comment to a card
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User types some comments
    And User clicks on save button
    And Comment is added

  Scenario: Edit a comment in the card
    Given User clicks on first card
    When User clicks on comment edit link 
    Then User edits the comment and saves
		And Comment is updated
		And Revers the update

  Scenario: Remove a comment from the card
    Given User clicks on first card
    When User clicks on comment delete link
    Then Comment is removed
    