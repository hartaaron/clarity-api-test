package clarity.api.endpoints.patientsearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientSearchResponse
{
	public List<Map<String, Object>> items;
	
	public HashMap<String, Object> _links;
	
	public HashMap<String, Object> page;
}
