package project.util;

public enum UserType {
	SUPER_ADMIN(1, "Super Administrator"),
	ADMIN(2, "Administrator"),
	CUSTOMER(3, "Customer");
	
	private final int id;
	private final String description;
	
	UserType(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int id() {
		return id;
	}
	
	public String description() {
		return description;
	}
}
