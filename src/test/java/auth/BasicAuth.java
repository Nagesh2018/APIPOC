package auth;
import java.util.Base64;

public class BasicAuth {

	
	public String getAuthString(String user) {
		String encodeToString = Base64.getEncoder().encodeToString(user.getBytes());
		return encodeToString;
	}
}
