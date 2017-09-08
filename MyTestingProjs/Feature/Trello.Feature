#Author: hhomayounfar@qaconsultants.com
#Keywords Summary : Trello testing 

Feature: Testing Login to Trello
	
	Scenario: Login to Trello
		Given A user open Trello page and login
		When Click on MyBoard
		Then It prints success
		Then Test ends

