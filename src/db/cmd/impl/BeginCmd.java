package db.cmd.impl;

import db.cmd.msg.PrintCmdOutput;
import db.data.DataWrapper;

/**
 * Created by canatalio on 1/14/16.
 */
public class BeginCmd implements Cmd {
    @Override
    public void execute(DataWrapper dataWrapper) {
        dataWrapper.updateDataToNewTransaction();

        PrintCmdOutput.lineBreak();
    }
}
