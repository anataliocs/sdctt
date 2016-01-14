package db.commands.impl;

import db.data.DataValues;
import db.data.DataWrapper;
import db.data.TransactionManager;

public class UnsetCommand implements Command {
    private String name;

    public UnsetCommand(String name) {
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
