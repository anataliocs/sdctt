package database.cmd.impl;

import database.data.DataValues;
import database.data.DataWrapper;
import database.data.TransactionManager;

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
            //key is marked as deleted in a transaction
            System.out.println(VALUE_NOT_FOUND);
        } else {
            //find key in most recent transaction
            String value = dataValues.getKeyValue(name);
            if (value == null) {
                value = transactionManager.getMostRecentValueForKey(name);
            }

            if (value == null) {
                System.out.println(VALUE_NOT_FOUND);
            } else {
                System.out.println(value);
                //cache found value
                dataValues.setData(name, value);
            }
        }
    }

}
