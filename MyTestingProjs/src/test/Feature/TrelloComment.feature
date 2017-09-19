#Author: hhomayounfar@qaconsultants.com
Feature: Trello Comment operations

  Scenario: Add a comment to a card
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User types some comments
    And User clicks on save button
    And Comment is added
    And User closes the card popup

  Scenario: Remove a comment from the card
    Given User clicks on first card
    When User clicks on comment delete link
    Then Comment is removed
    And User closes the card popup
    