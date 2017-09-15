#Author: hhomayounfar@qaconsultants.com
Feature: Trello Checklist item operations

  Scenario: Login to Trello
    Given User opens Trello login page
    When User enters login info
    Then List of teams and boards is displayed in the main page

  Scenario: Add an item to a checklist
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User clicks on add an item box of mychecklist and enters item
    And Item is added to the checklist

  Scenario: Complete an item of a checklist
    Given User is in the card detail popup
    When User clicks on box left to the item
    Then Item is completed

  Scenario: Delete the checklist from the card
    Given User is in the card detail popup
    When User clicks on the item
    Then Users clicks on Delete link
		And Item is deleted
		
  Scenario: Test ends
    Given The test is over
    When There is no more test
    Then The page closes
