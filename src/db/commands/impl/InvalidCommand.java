package db.commands.impl;

import db.data.DataWrapper;

public class InvalidCommand implements Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        System.out.println(errorMessage);
    }
}
