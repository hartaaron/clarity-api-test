//package clarity.api.hooks;
//
//import clarity.api.ClarityTestData;
//import clarity.api.World.World;
//import clarity.api.model.ClarityEnvironment;
//import clarity.api.model.ClarityUser;
//import clarity.api.unirest.UnirestClarityDriver;
//import cucumber.api.java.Before;
//
//import java.util.Properties;
//
//public class GlobalHooks
//{
//	Properties settings = new Properties();
//	World world;
//
//	@Before
//	public void setupEnvironment()
//	{
//		System.out.println("setupEnvironment");
//
//		world = new World();
//		world.loadSettings();
//
//		String CLARITY_ENV = settings.getProperty("clarity.env");
//		System.out.println("CLARITY_ENV: " + CLARITY_ENV);
//
//		// set default environment
//		if (CLARITY_ENV == null)
//		{
//			CLARITY_ENV = "TEST";
//		}
//
//		// initialize environment and Clarity driver
//		World.env = ClarityEnvironment.get(CLARITY_ENV);
//		World.clarity = new UnirestClarityDriver(World.env);
//		World.data = new ClarityTestData(settings);
//		world = new World(settings);
//	}
//
//	@Before("@existingUser")
//	public void getExistingUser()
//	{
//		System.out.println("get existing user");
//		setupEnvironmentFirst();
//
//		//TODO: implement get existing user
//	}
//
//	@Before("@newUser")
//	public void getNewUser()
//	{
//		System.out.println("get new user");
//		setupEnvironmentFirst();
//
//		//TODO: implement get new user
//	}
//
//	public void setupEnvironmentFirst()
//	{
//		if (World.clarity == null)
//		{
//			setupEnvironment();
//		}
//	}
//}
