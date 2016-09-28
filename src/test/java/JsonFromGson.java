import com.google.gson.Gson;
import com.hart.clarity.api.model.LoginCredentials;
import org.junit.Test;

public class JsonFromGson
{
	@Test
	public void convertToJson() {
		LoginCredentials credentials = new LoginCredentials();
		credentials.email = "test.user@hart.com";
		credentials.password = "secret";

		System.out.println(credentials.email);
		System.out.println(credentials.password);

		Gson gson = new Gson();
		System.out.println(gson.toJson(credentials));

	}

}
