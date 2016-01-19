package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
import database.data.DataValues;
import database.data.DataWrapper;
import database.data.manager.TransactionMgr;
import database.model.DataCommand;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class UnsetCmd implements Cmd {

    public static final DataCommand CMD_STRING = DataCommand.UNSET;

    private final String name;

    public UnsetCmd(String name) {
        this.name = name;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        DataValues currentDataValues = dataWrapper.getDataValues();
        TransactionMgr transactionMgr = dataWrapper.getTransactionMgr();

        // Get and decrement old value
        String oldVal = currentDataValues.getKeyValue(name);
        if (oldVal == null) {
            oldVal = transactionMgr.getCurrentValForKey(name);
        }
        if (oldVal != null) {
            Integer decrementedOccurrenceCount = getCountFromAllTransaction(oldVal, dataWrapper) - 1;
            currentDataValues.setValueCount(oldVal, decrementedOccurrenceCount);
        }

        // Delete key
        currentDataValues.unsetKey(name);
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
