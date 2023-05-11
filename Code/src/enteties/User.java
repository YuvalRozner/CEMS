package enteties;

public class User {

	private int id;
	private String name;
	private String userName;
	private String password;
	private String premission;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}