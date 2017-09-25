#Author: hhomayounfar@qaconsultants.com
Feature: Trello Team operations

  Scenario: Create a new team
    Given User clicks on create new team link
    When User enters Name and Description
    Then The new team is created and displayed

  Scenario: Delete the team
    Given User is in the Team page
    When User selects Setting tab
    Then Click Delete this team
    And Confirm and delete the team
