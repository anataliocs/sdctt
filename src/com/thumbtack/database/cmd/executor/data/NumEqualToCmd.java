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
public class NumEqualToCmd implements Cmd {
    public static final DataCommand CMD_STRING = DataCommand.NUMEQUALTO;
    private final String value;

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
