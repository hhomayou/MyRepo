#Author: hhomayounfar@qaconsultants.com
Feature: Trello Checklist item operations

  Scenario: Add an item to a checklist
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User clicks on add an item box of mychecklist and enters item
    And Item is added to the checklist

  Scenario: Complete an item of a checklist
    Given User clicks on first card
    When User clicks on box left to the item
    Then Item is completed

  Scenario: Delete the item from the checklist
    Given User clicks on first card
    When User clicks on the item
    Then Users clicks on Delete link
		And Item is deleted
    And Go back to main page
		