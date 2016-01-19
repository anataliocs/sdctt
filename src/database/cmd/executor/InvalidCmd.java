package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
import database.data.DataWrapper;
import database.model.DataCommand;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class InvalidCmd implements Cmd {
    private final String errorMessage;

    public static final DataCommand CMD_STRING = DataCommand.INVALID;

    public InvalidCmd(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        PrintCmdOutputSvc.printMsg(Optional.of(errorMessage));
    }
}
