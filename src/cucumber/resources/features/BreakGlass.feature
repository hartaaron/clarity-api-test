Feature: Break Glass

  Scenario: Direct patient care
	Given I am logged in to Clarity
	When I search for patient "ZZITESTSJM,HARTONE"
	And I break the glass for patient "ZZITESTSJM,HARTONE"
	Then I should get an access log token
