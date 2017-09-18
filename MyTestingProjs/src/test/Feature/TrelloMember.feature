#Author: hhomayounfar@qaconsultants.com
Feature: Trello Member operations

  Scenario: Remove a member from a card 
    Given User clicks on MyBoard box
    When User clicks on first card
    Then User clicks on Member button
    And User clicks on the Member to toggle status
    And Member is removed from the card
    
  Scenario: Add a member to a card
    Given User clicks on the Member to toggle status
    When Member is added from the card
		Then Users closes the members popup
    