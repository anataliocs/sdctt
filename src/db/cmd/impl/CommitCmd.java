package db.cmd.impl;

import db.cmd.msg.PrintCmdOutput;
import db.data.DataValues;
import db.data.DataWrapper;
import db.data.TransactionManager;

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
            System.out.println("NO TRANSACTION");
        } else {
            dataWrapper.setDataValues(mergedTransaction);
            transactionManager.cleanOldTransactions();
            PrintCmdOutput.lineBreak();
        }
    }
}
