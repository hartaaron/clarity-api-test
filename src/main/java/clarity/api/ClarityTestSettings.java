package clarity.api;

import clarity.api.driver.ClarityDriver;
import clarity.api.driver.UnirestClarityDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClarityTestSettings
{
	public Properties properties;
	
	private static Logger log = LogManager.getLogger();
	
	/**
	 * Attempt to load props from the test.properties file
	 * Override props with system properties (e.g. -DCLARITY_ENV=dev)
	 *
	 * @return
	 * @throws IOException
	 */
	public static ClarityTestSettings LoadSettings() throws IOException
	{
		// get props from properties file
		InputStream stream = ClarityTestSettings.class.getResourceAsStream("/test.properties");
		Properties properties = new Properties();
		properties.load(stream);
		
		// get props from system properties
		String CLARITY_ENV = System.getProperty("CLARITY_ENV");
		String CLARITY_USERS_JSON = System.getProperty("CLARITY_USERS_JSON");
		String CLARITY_PATIENTS_JSON = System.getProperty("CLARITY_PATIENTS_JSON");
		
		// system props overwrite file props
		if (CLARITY_ENV != null) { properties.setProperty("CLARITY_ENV", CLARITY_ENV); }
		if (CLARITY_USERS_JSON != null) { properties.setProperty("CLARITY_USERS_JSON", CLARITY_USERS_JSON); }
		if (CLARITY_PATIENTS_JSON != null) { properties.setProperty("CLARITY_PATIENTS", CLARITY_PATIENTS_JSON); }
		
		log.info("properties loaded: " + properties + "\n");
		
		ClarityTestSettings instance = new ClarityTestSettings();
		instance.properties = properties;
		
		return instance;
	}
	
	/**
	 * Return an instance of ClarityEnvironment based on the CLARITY_ENV property
	 *
	 * @return
	 */
	public ClarityEnvironment getClarityEnvironment() {
		String CLARITY_ENV = properties.getProperty("CLARITY_ENV");
		return ClarityEnvironment.get(CLARITY_ENV);
	}
	
	/**
	 * Return an instance of the ClarityDriver based on the CLARITY_ENV property
	 *
	 * @return
	 */
	public ClarityDriver getClarityDriver() {
		ClarityEnvironment env = getClarityEnvironment();
		return UnirestClarityDriver.forEnvironment(env);
	}
}
