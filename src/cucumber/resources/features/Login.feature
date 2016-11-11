@Login
Feature: Login

	@ValidUser
	Scenario Outline: Valid User
		Given a user with email "<email>" and password "<password>"
		When I login to Clarity
		Then I should have access to Clarity

		Examples:
			| email                             | password |
			| clarity-external-testing@hart.com | Cl@rity1 |
#		| bill.leung@hart.com               | Password1!

	@InvalidUser
	Scenario Outline: Invalid User
		Given a user with email "<email>" and password "<password>"
		When I login to Clarity
		Then I should not have access to Clarity

		Examples:
			| email                 | password |
			| invalid-user@hart.com | Cl@rity1 |


	@InvalidPassword
	Scenario Outline: Invalid Password
		Given a user with email "<email>" and password "<password>"
		When I login to Clarity
		Then I should not have access to Clarity

		Examples:
			| email                             | password       |
			| clarity-external-testing@hart.com | wrong-password |
