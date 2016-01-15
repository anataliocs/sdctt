package database.data;

/**
 * Container that stores the past transaction (TransactionManager) and the current used dataValues (DataValues)
 */
public class DataWrapper {
    private TransactionMgr transactionMgr = new TransactionMgr();
    private DataValues dataValues = new DataValues();

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
        // the old dataValues is added to the list of transaction in transaction manager and a new dataValues is set
        dataValues = transactionMgr.begin(dataValues);
    }
}
