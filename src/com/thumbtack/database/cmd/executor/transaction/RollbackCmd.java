package com.thumbtack.database.cmd.executor.transaction;

import com.thumbtack.database.cmd.executor.Cmd;
import com.thumbtack.database.cmd.msg.PrintCmdOutputSvc;
import com.thumbtack.database.data.DataValues;
import com.thumbtack.database.data.DataWrapper;
import com.thumbtack.database.model.DataCommand;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class RollbackCmd implements Cmd {

    public static final DataCommand CMD_STRING = DataCommand.ROLLBACK;

    @Override
    public void execute(DataWrapper dataWrapper) {
        // Discard last transaction
        DataValues dataValues = dataWrapper.getTransactionMgr().rollback();
        if (dataValues == null) {
            PrintCmdOutputSvc.printMsg(Optional.of(NO_TRANSACTION));
        } else {
            dataWrapper.setDataValues(dataValues);
            PrintCmdOutputSvc.printMsg(Optional.<String>empty());
        }
    }
}
