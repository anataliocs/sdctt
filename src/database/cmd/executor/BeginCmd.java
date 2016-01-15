package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
import database.data.DataWrapper;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class BeginCmd implements Cmd {
    @Override
    public void execute(DataWrapper dataWrapper) {
        dataWrapper.updateDataToNewTransaction();

        PrintCmdOutputSvc.printMsg(Optional.<String>empty());
    }
}
