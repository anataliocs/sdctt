package database.cmd.impl;

import database.cmd.msg.PrintCmdOutput;
import database.data.DataValues;
import database.data.DataWrapper;
import database.data.TransactionManager;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class GetCmd implements Cmd {
    private String name;

    private static final String VALUE_NOT_FOUND = "NULL";

    public GetCmd(String name) {
        this.name = name;
    }

    @Override
    public void execute(DataWrapper container) {
        DataValues dataValues = container.getDataValues();
        TransactionManager transactionManager = container.getTransactionManager();

        if (dataValues.isKeyDeleted(name)) {
            //key flagged as deleted in a transaction
            PrintCmdOutput.printMsg(Optional.of(VALUE_NOT_FOUND));
        } else {
            //find key in most recent transaction
            String value = dataValues.getKeyValue(name);
            if (value == null) {
                value = transactionManager.getMostRecentValueForKey(name);
            }

            if (value == null) {
                PrintCmdOutput.printMsg(Optional.of(VALUE_NOT_FOUND));
            } else {
                PrintCmdOutput.printMsg(Optional.of(value));
                //cache value located
                dataValues.setData(name, value);
            }
        }
    }

}
