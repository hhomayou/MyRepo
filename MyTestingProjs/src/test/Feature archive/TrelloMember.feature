#Author: hhomayounfar@qaconsultants.com
Feature: Trello Member operations

  Scenario: Login to Trello
    Given User opens Trello login page
    When User enters login info
    Then List of teams and boards is displayed in the main page

  Scenario: Add a member to a card
    Given User is in the Board page
    When User clicks on a card
    Then User Card detail pops up
    And User clicks on Member button
    And User clicks on the Member to toggle status

  Scenario: Remove a member from the card 
    Given User is in the card detail popup
    When User clicks on Member button
    Then User clicks on box left to the item
    And User clicks on the Member to toggle status
	
  Scenario: Test ends
    Given The test is over
    When There is no more test
    Then The page closes
