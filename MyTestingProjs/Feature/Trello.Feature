#Author: hhomayounfar@qaconsultants.com
#Keywords Summary : Trello testing 

Feature: Testing Trello
	
	Scenario: Login to Trello
		Given A user wants to use Trello page
		When The user enters login info
		Then The page Boards is loaded and it displays all the boards and teams
	Scenario: Click on Board 
		Given The user is in the main page  
		When The user clicks on MyBoard box
		Then The page MyBoard is loaded and it displays all the info
	Scenario: Click on a card
		Given The user is in the MyBoard page  
		When The user clicks on first card
		Then The card info is loaded and popped up
		And The user closes the card popup
	Scenario: Create a new team
		Given The user is in the main page  
		When The user clicks on create new team link
		Then Create Team popup appears 
		And User enters Name and Description
		And The new team 
	Scenario: Create a new board
		Given The user is in the main page
		When The user clicks on create new board box
		Then Create Board popup appears 
		And User enters Title and selects first Team
		And The new board is created 
	Scenario: Test ends
		Then The page closes

