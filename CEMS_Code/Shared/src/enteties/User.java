package enteties;

public class User {

	private String id;
	private String name;
	private String username;
	private String password;
	private String premission;
	private String loggedin; // yes/no

	public String getId() {
		return this.id;
	}

	public User(String id, String name, String username, String password, String premission, String loggedin) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.premission = premission;
		this.loggedin = loggedin;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPremission() {
		return this.premission;
	}

	public void setPremission(String premission) {
		this.premission = premission;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoggedin() {
		return loggedin;
	}

	public void setLoggedin(String loggedin) {
		this.loggedin = loggedin;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", premission=" + premission + ", loggedin=" + loggedin + "]";
	}

}