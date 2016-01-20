package com.thumbtack.database.cmd.executor.transaction;

import com.thumbtack.database.cmd.executor.Cmd;
import com.thumbtack.database.cmd.msg.PrintCmdOutputSvc;
import com.thumbtack.database.data.DataValues;
import com.thumbtack.database.data.DataWrapper;
import com.thumbtack.database.data.manager.TransactionMgr;
import com.thumbtack.database.model.DataCommand;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class CommitCmd implements Cmd {

    public static final DataCommand CMD_STRING = DataCommand.COMMIT;

    @Override
    public void execute(DataWrapper dataWrapper) {
        DataValues dataValues = dataWrapper.getDataValues();
        TransactionMgr transactionMgr = dataWrapper.getTransactionMgr();

        DataValues mergedTransaction = transactionMgr.commit(dataValues);

        if (mergedTransaction == null) {
            PrintCmdOutputSvc.printMsg(Optional.of(NO_TRANSACTION));
        } else {
            dataWrapper.setDataValues(mergedTransaction);
            transactionMgr.flush();
            PrintCmdOutputSvc.printMsg(Optional.<String>empty());
        }
    }
}
