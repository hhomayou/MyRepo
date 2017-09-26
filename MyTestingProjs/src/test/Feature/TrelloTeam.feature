#Author: hhomayounfar@qaconsultants.com
Feature: Trello Team operations

  Scenario: Create a new team
    Given User clicks on create new team link
    When User enters name and description
    Then The new team is created and displayed

  Scenario: Delete the team
    Given User is in the team page
    When User selects setting tab
    Then User clicks delete this team
    And User confirms and deletes the team
