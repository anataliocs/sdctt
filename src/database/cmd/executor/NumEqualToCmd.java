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
public class NumEqualToCmd implements Cmd {
    private final String value;

    public static final DataCommand CMD_STRING = DataCommand.NUMEQUALTO;

    public NumEqualToCmd(String value) {
        this.value = value;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        DataValues currentDataValues = dataWrapper.getDataValues();
        TransactionMgr transactionMgr = dataWrapper.getTransactionMgr();

        // Get count
        Integer currentCount = currentDataValues.getValueCount(value);
        if (currentCount == null) {
            currentCount = transactionMgr.getNumOfTimesValIsPresent(value);
            // Cache count value
            currentDataValues.setValueCount(value, currentCount);
        }
        PrintCmdOutputSvc.printMsg(Optional.of(currentCount.toString()));
    }
}
