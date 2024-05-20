package project.util;

public enum RoomTypes {
	NORMAL("Normal"), FAMILY("Family");
	
	private final String value;
	
	RoomTypes(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
