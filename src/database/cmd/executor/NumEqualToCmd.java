package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
import database.data.DataValues;
import database.data.DataWrapper;
import database.data.TransactionMgr;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class NumEqualToCmd implements Cmd {
    private String value;

    public NumEqualToCmd(String value) {
        this.value = value;
    }

    @Override
    public void execute(DataWrapper dataWrapper) {
        DataValues currentDataValues = dataWrapper.getDataValues();
        TransactionMgr transactionMgr = dataWrapper.getTransactionMgr();

        //get value count
        Integer currentCount = currentDataValues.getValueCount(value);
        if (currentCount == null) {
            currentCount = transactionMgr.getOccurrencesForValue(value);
            //cache count value in current transaction
            currentDataValues.setValueCount(value, currentCount);
        }
        PrintCmdOutputSvc.printMsg(Optional.of(currentCount.toString()));
    }
}