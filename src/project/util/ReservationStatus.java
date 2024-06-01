package project.util;

public enum ReservationStatus {
	PENDING("Pending"),
	CONFIRMED("Confirmed"),
	DECLINED("Declined"),
	CANCELLED("Cancelled"),
	UNKNOWN("Unknown");
	
	private final String value;
	
	private ReservationStatus(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
