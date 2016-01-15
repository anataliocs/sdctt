package database.cmd.impl;

import database.cmd.msg.PrintCmdOutput;
import database.data.DataWrapper;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class BeginCmd implements Cmd {
    @Override
    public void execute(DataWrapper dataWrapper) {
        dataWrapper.updateDataToNewTransaction();

        PrintCmdOutput.printMsg(Optional.<String>empty());
    }
}
