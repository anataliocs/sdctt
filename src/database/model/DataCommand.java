package database.model;

import java.util.Arrays;

public enum DataCommand {
    SET("SET"),
    UNSET("UNSET"),
    GET("GET"),
    NUMEQUALTO("NUMEQUALTO"),
    END("END"),
    BEGIN("BEGIN"),
    COMMIT("COMMIT"),
    ROLLBACK("ROLLBACK"),
    INVALID("INVALID");

    private final String command;

    private DataCommand(String command) {
        this.command = command;
    }

    public static DataCommand getCommandFromType(final String type) {
        return Arrays.asList(DataCommand.values()).stream()
                .filter(v -> v.getCommand().equals(type))
                .findFirst().orElse(null);
    }

    String getCommand() {
        return command;
    }
}
