package db.cmd.impl;

import db.data.DataValues;
import db.data.DataWrapper;
import db.data.TransactionManager;

/**
 * Created by canatalio on 1/14/16.
 */
public class NumEqualToCmd implements Cmd {
    private String value;

    public NumEqualToCmd(String value) {
        this.value = value;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        DataValues currentDataValues = dataWrapper.getDataValues();
        TransactionManager transactionManager = dataWrapper.getTransactionManager();

        //get value count
        Integer currentCount = currentDataValues.getValueCount(value);
        if (currentCount == null) {
            currentCount = transactionManager.getOccurrencesForValue(value);
            //cache count value in current transaction
            currentDataValues.setValueCount(value, currentCount);
        }
        System.out.println(currentCount);
    }
}
