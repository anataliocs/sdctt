package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
import database.data.DataWrapper;
import database.model.DataCommand;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class BeginCmd implements Cmd {

    public static final DataCommand CMD_STRING = DataCommand.BEGIN;

    @Override
    public void execute(DataWrapper dataWrapper) {

        dataWrapper.updateNewTransaction();
        PrintCmdOutputSvc.printMsg(Optional.<String>empty());
    }
}
