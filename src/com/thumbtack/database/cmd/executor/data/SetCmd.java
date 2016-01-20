package com.thumbtack.database.cmd.executor.data;

import com.thumbtack.database.cmd.executor.Cmd;
import com.thumbtack.database.cmd.msg.PrintCmdOutputSvc;
import com.thumbtack.database.data.DataValues;
import com.thumbtack.database.data.DataWrapper;
import com.thumbtack.database.data.manager.TransactionMgr;
import com.thumbtack.database.model.DataCommand;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class SetCmd implements Cmd {

    public static final DataCommand CMD_STRING = DataCommand.SET;

    private final String name;
    private final String value;

    public SetCmd(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute(DataWrapper container) {
        DataValues currentDataValues = container.getDataValues();
        TransactionMgr transactionMgr = container.getTransactionMgr();

        if (!currentDataValues.isKeyDeleted(name)) {
            // Get old value
            String oldVal = currentDataValues.getKeyValue(name);
            if (oldVal == null) {
                oldVal = transactionMgr.getCurrentValForKey(name);
            }
            // Decrement old value
            if (oldVal != null) {
                Integer decrementedOccurrenceCount = getCountFromAllTransaction(oldVal, container) - 1;
                currentDataValues.setValueCount(oldVal, decrementedOccurrenceCount);
            }
        }

        // Set new value and count
        Integer occurrences = getCountFromAllTransaction(value, container);
        currentDataValues.setValueCount(value, occurrences + 1);

        currentDataValues.setData(name, value);
        PrintCmdOutputSvc.printMsg(Optional.<String>empty());
    }

    private Integer getCountFromAllTransaction(String value, DataWrapper container) {
        Integer count = container.getDataValues().getValueCount(value);
        if (count == null) {
            count = container.getTransactionMgr().getNumOfTimesValIsPresent(value);
        }
        return count;
    }
}
