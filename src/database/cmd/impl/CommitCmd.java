package database.cmd.impl;

import database.cmd.msg.PrintCmdOutput;
import database.data.DataValues;
import database.data.DataWrapper;
import database.data.TransactionManager;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class CommitCmd implements Cmd {
    @Override
    public void execute(DataWrapper dataWrapper) {
        DataValues dataValues = dataWrapper.getDataValues();
        TransactionManager transactionManager = dataWrapper.getTransactionManager();

        DataValues mergedTransaction = transactionManager.commit(dataValues);
        if (mergedTransaction == null) {
            PrintCmdOutput.printMsg(Optional.of("NO TRANSACTION"));
        } else {
            dataWrapper.setDataValues(mergedTransaction);
            transactionManager.cleanOldTransactions();
            PrintCmdOutput.printMsg(Optional.<String>empty());
        }
    }
}
