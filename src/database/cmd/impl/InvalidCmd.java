package database.cmd.impl;

import database.data.DataWrapper;

/**
 * Created by canatalio on 1/14/16.
 */
public class InvalidCmd implements Cmd {
    private String errorMessage;

    public InvalidCmd(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        System.out.println(errorMessage);
    }
}
