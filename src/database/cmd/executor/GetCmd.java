package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
import database.data.DataValues;
import database.data.DataWrapper;
import database.data.TransactionMgr;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class GetCmd implements Cmd {
    private static final String VALUE_NOT_FOUND = "NULL";
    private final String name;

    public GetCmd(String name) {
        this.name = name;
    }

    @Override
    public void execute(DataWrapper container) {
        DataValues dataValues = container.getDataValues();
        TransactionMgr transactionMgr = container.getTransactionMgr();

        if (dataValues.isKeyDeleted(name)) {
            //key flagged as deleted in a transaction
            PrintCmdOutputSvc.printMsg(Optional.of(VALUE_NOT_FOUND));
        } else {
            //find key in most recent transaction
            String value = dataValues.getKeyValue(name);
            if (value == null) {
                value = transactionMgr.getMostRecentValueForKey(name);
            }

            if (value == null) {
                PrintCmdOutputSvc.printMsg(Optional.of(VALUE_NOT_FOUND));
            } else {
                PrintCmdOutputSvc.printMsg(Optional.of(value));
                //cache value located
                dataValues.setData(name, value);
            }
        }
    }

}
