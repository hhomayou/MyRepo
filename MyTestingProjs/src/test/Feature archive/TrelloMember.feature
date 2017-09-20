#Author: hhomayounfar@qaconsultants.com
Feature: Trello Member operations

  Scenario: Remove a member from a card 
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User clicks on Member button
    And User clicks on the Member to toggle status
    And Member is removed from the card
		And Users closes the members popup
    
  Scenario: Add a member to a card
    Given User clicks on first card
    When User clicks on Member button
    Then User clicks on the Member to toggle status
    And Member is added to the card
		And Users closes the members popup
    