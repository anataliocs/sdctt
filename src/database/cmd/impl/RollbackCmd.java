package database.cmd.impl;

import database.data.DataValues;
import database.data.DataWrapper;

/**
 * Created by canatalio on 1/14/16.
 */
public class RollbackCmd implements Cmd {

    @Override
    public void execute(DataWrapper dataWrapper) {
        //the last transaction is discarded
        DataValues dataValues =  dataWrapper.getTransactionManager().rollback();
        if (dataValues == null) {
            System.out.println("NO TRANSACTION");
        } else {
            dataWrapper.setDataValues(dataValues);
            System.out.println();
        }
    }
}