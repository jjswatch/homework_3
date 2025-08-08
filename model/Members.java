package model;

public class Members {
	private int id;
	private String name;
	private String email;
	private String password;
	private String username;
	
	public Members() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Members(String name, String email, String password, String username) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	
}
