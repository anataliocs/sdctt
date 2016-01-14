package db.commands.impl;

import db.data.DataValues;
import db.data.DataWrapper;
import db.data.TransactionManager;

public class CommitCommand implements Command {
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
            System.out.println();
        }
    }
}
