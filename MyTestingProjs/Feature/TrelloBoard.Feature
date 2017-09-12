#Author: hhomayounfar@qaconsultants.com
Feature: Trello Team functions 

  Scenario: Login to Trello
    Given User opens Trello login page
    When User enters login info
    Then List of teams and boards is displayed in the main page

  Scenario: Create a new board
    Given User is in the main page
    When User clicks on create new board box
    Then Create Board popup appears
    Then User enters Title and selects first Team
    Then The new board is created
    Then The page closes

#  Scenario: Test ends
#    Given The test is over
#    When There is no more test
