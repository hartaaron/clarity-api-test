@Login
Feature: Login

  Scenario: Valid user

	Given a valid user with username "clarity-external-testing@hart.com" and password "Cl@rity1"
	When I login to Clarity
	Then I should be logged in to Clarity