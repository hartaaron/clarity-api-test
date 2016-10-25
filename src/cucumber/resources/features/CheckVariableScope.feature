Feature: Check Variable Scope

Scenario: set in Given, accessed in When
  Given a variable with value 7
  When I read the variable
  Then I should get 7