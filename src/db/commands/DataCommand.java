package db.commands;

public enum DataCommand {
    SET("set"),
    UNSET("unset"),
    GET("get"),
    NUMEQUALTO("numequalto"),
    END("end"),
    BEGIN("begin"),
    COMMIT("commit"),
    ROLLBACK("rollback"),
    INVALID("invalid");

    private String command;

    private DataCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static DataCommand getCommandFromType(String type) {
        for (DataCommand validCommand : DataCommand.values()) {
            if (validCommand.getCommand().equals(type)) {
                return validCommand;
            }
        }

        return null;
    }
}
