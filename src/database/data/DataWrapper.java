package database.data;

/**
 * Container that stores the past transaction (TransactionManager) and the current used dataValues (DataValues)
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

    public void updateDataToNewTransaction() {
        dataValues = transactionMgr.begin(dataValues);
    }
}
