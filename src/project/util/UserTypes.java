package project.util;

import java.util.Arrays;
import java.util.Optional;

public enum UserTypes {
    SUPER_ADMIN(1, "Super Administrator"),
    ADMIN(2, "Administrator"),
    CUSTOMER(3, "Customer");

    private final int id;
    private final String description;

    UserTypes(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int id() {
        return id;
    }

    public String description() {
        return description;
    }

    public static Optional<UserTypes> description(String description) {
        return Arrays.stream(values())
                .filter(userTypes -> userTypes.description.equals(description))
                .findFirst();
    }
}
