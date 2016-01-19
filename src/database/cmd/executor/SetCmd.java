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
            //get oldValue
            String oldValue = currentDataValues.getKeyValue(name);
            if (oldValue == null) {
                oldValue = transactionMgr.getCurrentValForKey(name);
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
