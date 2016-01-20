package com.thumbtack.database.cmd.executor;

import com.thumbtack.database.cmd.msg.PrintCmdOutputSvc;
import com.thumbtack.database.data.DataWrapper;
import com.thumbtack.database.model.DataCommand;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class InvalidCmd implements Cmd {
    public static final DataCommand CMD_STRING = DataCommand.INVALID;
    private final String errorMessage;

    public InvalidCmd(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        PrintCmdOutputSvc.printMsg(Optional.of(errorMessage));
    }
}
