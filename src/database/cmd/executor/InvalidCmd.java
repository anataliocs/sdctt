package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
import database.data.DataWrapper;

import java.util.Optional;

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
        PrintCmdOutputSvc.printMsg(Optional.of(errorMessage));
    }
}
