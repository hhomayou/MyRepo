#Author: hhomayounfar@qaconsultants.com
#Keywords Summary : Trello testing 

Feature: Testing Trello
	
	Scenario: Login to Trello
		Given A user wants to use Trello page
		When The user enters login info
		Then The page Boards is loaded and it displays all the boards and teams
	Scenario:
		Given The user is in the Boards page  
		When The users clicks on MyBoard box
		Then The page MyBoard is loaded and it displays all the lists
		Then Test ends

