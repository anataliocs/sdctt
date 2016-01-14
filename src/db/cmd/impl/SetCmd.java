package db.cmd.impl;

import db.data.DataValues;
import db.data.DataWrapper;
import db.data.TransactionManager;

/**
 * Created by canatalio on 1/14/16.
 */
public class SetCmd implements Cmd {
    private String name;
    private String value;

    public SetCmd(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute(DataWrapper container) {
        DataValues currentDataValues = container.getDataValues();
        TransactionManager transactionManager = container.getTransactionManager();

        if (!currentDataValues.isKeyDeleted(name)) {
            //get oldValue
            String oldValue = currentDataValues.getKeyValue(name);
            if (oldValue == null) {
                oldValue = transactionManager.getMostRecentValueForKey(name);
            }
            //decrement oldValue count
            if (oldValue != null) {
                Integer decrementedOccurrenceCount = getOccurrenceCountFromAllTransaction(oldValue, container) - 1;
                currentDataValues.setValueCount(oldValue, decrementedOccurrenceCount);
            }
        }

        //set new value and update value count
        Integer occurrences = getOccurrenceCountFromAllTransaction(value, container);
        currentDataValues.setValueCount(value, occurrences + 1);

        currentDataValues.setData(name, value);
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
