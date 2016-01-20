package database.model;

import java.util.Arrays;

public enum DataCommand {
    SET("SET", 3),
    UNSET("UNSET", 2),
    GET("GET", 2),
    NUMEQUALTO("NUMEQUALTO", 2),
    END("END", 0),
    BEGIN("BEGIN", 0),
    COMMIT("COMMIT", 0),
    ROLLBACK("ROLLBACK", 0),
    INVALID("INVALID", 0);

    private final String command;
    private final int requiredNumOfArgs;

    private DataCommand(String command, int args) {

        this.command = command;
        this.requiredNumOfArgs = args;
    }

    public static DataCommand getCommandFromType(final String type) {
        return Arrays.asList(DataCommand.values()).stream()
                .filter(v -> v.getCommand().equals(type))
                .findFirst().orElse(null);
    }

    String getCommand() {
        return command;
    }

    public int getRequiredNumOfArgs() {
        return requiredNumOfArgs;
    }
}
