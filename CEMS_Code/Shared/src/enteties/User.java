package enteties;

public class User {
    //in DB:
	private String id;
	private String name;
	private String username;
	private String password;
	private String premission;
	private String loggedin; // yes/no
	//not in DB:
	
	//for FX:
	
	/**
	 * empty constructor.
	 
	public User() {super();} */
	
	/**
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 * @param premission
	 * @param loggedin
	 */
	public User(String id, String name, String username, String password, String premission, String loggedin) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.premission = premission;
		this.loggedin = loggedin;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the premission
	 */
	public String getPremission() {
		return premission;
	}

	/**
	 * @param premission the premission to set
	 */
	public void setPremission(String premission) {
		this.premission = premission;
	}

	/**
	 * @return the loggedin
	 */
	public String getLoggedin() {
		return loggedin;
	}

	/**
	 * @param loggedin the loggedin to set
	 */
	public void setLoggedin(String loggedin) {
		this.loggedin = loggedin;
	}
	
	@Override
	public boolean equals(Object obj) {
		User u = (User) obj;
		return id.equals(u.getId());
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", premission=" + premission + ", loggedin=" + loggedin + "]";
	}
}