@PatientSearch
Feature: PatientSearch

  @Bug
  Scenario: Patient name should have lastname first

	Given I am logged in to Clarity
	When I search for patient "ZZITESTSJM,HARTONE"
	Then I get a patient with name "ZZITESTSJM,HARTONE"

  Scenario: Single patient found

	Given I am logged in to Clarity
	When I search for patient "ZZITESTSJM,HARTONE"
	Then I get that patient in the patient list

  Scenario: No patient found

	Given I am logged in to Clarity
	When I search for patient "NOTFOUND,NONEXISTENT"
	Then I get no patients in the the patient list

  Scenario Outline: Search for patient by firstName, lastName, birthDate

	Given I am logged in to Clarity
	When I search for patient with "<lastName>" "<firstName>" "<birthDate>"
	Then I get that patient in the patient list
	Examples:
	  | lastName   | firstName | birthDate  |
	  | ZZITESTSJM  | HARTONE  | 12/02/1922 |
	  | ZZITESTSJM  | HARTFIVE | 04/20/1980 |