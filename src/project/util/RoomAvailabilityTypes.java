package project.util;

import java.util.Arrays;

public enum RoomAvailabilityTypes {
	DAY_USE(1, "Day Use"),
	NIGHT_USE(2, "Night Use"),
	DAY_AND_NIGHT_USE(3, "Day and Night Use");
	
	private final int id;
	private final String value;

	RoomAvailabilityTypes(int id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public int id() {
		return id;
	}
	
	public String value() {
		return value;
	}
	
	public static String[] names() {
		return Arrays.stream(values())
				.map(RoomAvailabilityTypes::value)
				.toArray(String[]::new);
	}
	
	public static RoomAvailabilityTypes type(String name) {
		return Arrays.stream(values())
				.filter(type -> type.value.equals(name))
				.findFirst()
				.orElse(DAY_USE);
	}
}
