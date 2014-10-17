package objects;

public class Account 
{
	private int id;
	private String name;
	private String login;
	private String password;
	
	public String getName()
	{
		return name;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	public Account(String _name, String _login, String _password)
	{
		name = _name;
		login = _login;
		password = _password;
		// TODO: Set automatically an ID
		//id = 
	}
	
	public Account(String _login, String _password)
	{
		login = _login;
		password = _password;
	}
	
	public static void CreateAccount(Account newAccount)
	{
		// Add to DB
		// Create cookies and stuff
	}
	
	
}
