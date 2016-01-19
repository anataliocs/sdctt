package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
import database.data.DataValues;
import database.data.DataWrapper;
import database.data.TransactionMgr;
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

        //get old value and decrement it's count
        String oldValue = currentDataValues.getKeyValue(name);
        if (oldValue == null) {
            oldValue = transactionMgr.getCurrentValForKey(name);
        }
        if (oldValue != null) {
            Integer decrementedOccurrenceCount = getOccurrenceCountFromAllTransaction(oldValue, dataWrapper) - 1;
            currentDataValues.setValueCount(oldValue, decrementedOccurrenceCount);
        }

        //delete and mark key as deleted
        currentDataValues.unsetKey(name);
        PrintCmdOutputSvc.printMsg(Optional.<String>empty());
    }

    private Integer getOccurrenceCountFromAllTransaction(String value, DataWrapper container) {
        Integer occurrenceCount = container.getDataValues().getValueCount(value);
        if (occurrenceCount == null) {
            occurrenceCount = container.getTransactionMgr().getNumOfTimesValIsPresent(value);
        }
        return occurrenceCount;
    }
}
