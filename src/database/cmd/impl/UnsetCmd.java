package database.cmd.impl;

import database.data.DataValues;
import database.data.DataWrapper;
import database.data.TransactionManager;

/**
 * Created by canatalio on 1/14/16.
 */
public class UnsetCmd implements Cmd {
    private String name;

    public UnsetCmd(String name) {
        this.name = name;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        DataValues currentDataValues = dataWrapper.getDataValues();
        TransactionManager transactionManager = dataWrapper.getTransactionManager();

        //get old value and decrement it's count
        String oldValue = currentDataValues.getKeyValue(name);
        if (oldValue == null) {
            oldValue = transactionManager.getMostRecentValueForKey(name);
        }
        if (oldValue != null) {
            Integer decrementedOccurrenceCount = getOccurrenceCountFromAllTransaction(oldValue, dataWrapper) - 1;
            currentDataValues.setValueCount(oldValue, decrementedOccurrenceCount);
        }

        //delete and mark key as deleted
        currentDataValues.unsetKey(name);
        System.out.println();
    }

    private Integer getOccurrenceCountFromAllTransaction(String value, DataWrapper container) {
        Integer occurrenceCount = container.getDataValues().getValueCount(value);
        if (occurrenceCount == null) {
            occurrenceCount = container.getTransactionManager().getOccurrencesForValue(value);
        }
        return occurrenceCount;
    }
}
