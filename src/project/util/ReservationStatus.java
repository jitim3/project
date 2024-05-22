package project.util;

public enum ReservationStatus {
	PENDING("Pending"),
	CONFIRMED("Confirmed"),
	DECLINED("Decline"),
	CANCELLED("Pending");
	
	private final String value;
	
	private ReservationStatus(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
