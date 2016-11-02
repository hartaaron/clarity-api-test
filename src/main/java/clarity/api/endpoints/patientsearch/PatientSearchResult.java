package clarity.api.endpoints.patientsearch;

import clarity.api.endpoints.JsonEntity;
import clarity.api.model.ClarityPatient;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientSearchResult extends JsonEntity<PatientSearchResult>
{
	public List<Map<String, Object>> items;
	
	public HashMap<String, Object> _links;
	
	public HashMap<String, Object> page;
	
	public List<ClarityPatient> getPatients()
	{
		List<ClarityPatient> patients;
		
		String itemsJson = gson.toJson(items);
	
		Gson gson = new GsonBuilder().setLenient().create();
		
		Type type = new TypeToken<ArrayList<ClarityPatient>>(){}.getType();
		patients = gson.fromJson(itemsJson, type);
		
		return patients;
	}
}
