#Author: hhomayounfar@qaconsultants.com
Feature: Trello Team operations

  Scenario: Login to Trello
    Given User opens Trello login page
    When User enters login info
    Then List of teams and boards is displayed in the main page

  Scenario: Create a new team
    Given User is in the main page
    When User clicks on create new team link
    Then User enters Name and Description
    And The new team is created and displayed

  Scenario: Delete the team
    Given User is in the Team page
    When User selects Setting tab
    Then Click Delete this team
    And Confirm and delete the team
    And A confirmation is displayed

  Scenario: Test ends
    Given The test is over
    When There is no more test
    Then The page closes
