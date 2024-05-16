package project;

public class User {
	private final Long id;
	private final String username;
	private final int userTypeId;
	
	public User(Long id, String username, int userTypeId) {
		this.id = id;
		this.username = username;
		this.userTypeId = userTypeId;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public int getUserTypeId() {
		return userTypeId;
	}
}
