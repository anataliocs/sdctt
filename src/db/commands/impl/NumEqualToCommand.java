package db.commands.impl;

import db.data.DataValues;
import db.data.DataWrapper;
import db.data.TransactionManager;

public class NumEqualToCommand implements Command {
    private String value;

    public NumEqualToCommand(String value) {
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
