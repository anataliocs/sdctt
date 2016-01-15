package database.cmd.impl;

import database.cmd.msg.PrintCmdOutput;
import database.data.DataValues;
import database.data.DataWrapper;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class RollbackCmd implements Cmd {

    @Override
    public void execute(DataWrapper dataWrapper) {
        //the last transaction is discarded
        DataValues dataValues =  dataWrapper.getTransactionManager().rollback();
        if (dataValues == null) {
            PrintCmdOutput.printMsg(Optional.of("NO TRANSACTION"));
        } else {
            dataWrapper.setDataValues(dataValues);
            PrintCmdOutput.printMsg(Optional.<String>empty());
        }
    }
}
