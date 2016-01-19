package database.data;

import database.data.manager.TransactionMgr;

/**
 * Stores the past transaction and the current data
 */
public class DataWrapper {
    private final TransactionMgr transactionMgr;
    private DataValues dataValues = new DataValues();

    {
        transactionMgr = new TransactionMgr();
    }

    public TransactionMgr getTransactionMgr() {
        return transactionMgr;
    }

    public DataValues getDataValues() {
        return dataValues;
    }

    public void setDataValues(DataValues dataValues) {
        this.dataValues = dataValues;
    }

    public void updateNewTransaction() {
        dataValues = transactionMgr.start(dataValues);
    }
}
