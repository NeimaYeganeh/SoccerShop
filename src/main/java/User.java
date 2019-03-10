
public class User {
	
	// TODO
	public static enum UserType {};

	private String userId;
	private String email;
	private String lastName;
	private String firstName;
	private UserType type;
	
	public User(String userId, String email, String lastName, String firstName, UserType type) {
		this.userId = userId;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.type = type;
	}
	
	// basic constructor
	public User() {
		
	}
	
	public String getUserId() {
		return userId;
	}
	public String getEmail() {
		return email;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public UserType getUserType() {
		return type;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setUserType(UserType type) {
		this.type = type;
	}
	
}
