package db.commands.impl;

import db.data.DataValues;
import db.data.DataWrapper;

public class RollbackCommand implements Command {

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
