package clarity.api.model;

import clarity.api.model.chart.*;

import java.util.Date;
import java.util.List;

public class ClarityPatientData
{
	// demographics
	public String gender;
	public Integer age;
	public Date DOB;
	public String phoneNumber;
	public String language;
	public String smokingStatus;
	public String hasAllergies;

	public Address primaryAddress;
	public Address secondaryAddress;
	public String primaryPhone;
	public String secondaryPhone;

	public Contact emergencyContact;

	public Insurance insurance;

	//charts
	public Allergies allergies;
	public Immunizations immunizatins;
	public Medications medications;
	public Problems problems;
	public Vitals vitals;
	public List<Laboratory> labs;
	public BloodBank bloodBank;
	public Microbiology microbiology;
	public Pathology pathology;
	public Documents documents;
	public Diagnostics diagnostics;
}
