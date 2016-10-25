@PatientSearch
Feature: PatientSearch

  Scenario: Find a single patient

	Given I am logged in to Clarity
	When I search for patient "ZZITESTSJM,HARTONE"
	Then I get the patient list