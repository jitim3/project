package project.util;

/**
 * @author Julian Jupiter
 */
public enum TownEnum {
    CARCAR(1, "Carcar"),
    BARILI(2, "Barili"),
    MOALBOAL(3, "Moalboal"),
    ALCOY(4, "Alcoy"),
    SANTANDER(5, "SanTander"),
    OSLOB(6, "Oslob");

    private final int id;
    private final String value;

    TownEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int id() {
        return id;
    }

    public String value() {
        return value;
    }
}
