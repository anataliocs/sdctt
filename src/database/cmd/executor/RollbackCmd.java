package database.cmd.executor;

import database.cmd.msg.PrintCmdOutputSvc;
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
        DataValues dataValues = dataWrapper.getTransactionMgr().rollback();
        if (dataValues == null) {
            PrintCmdOutputSvc.printMsg(Optional.of(NO_TRANSACTION));
        } else {
            dataWrapper.setDataValues(dataValues);
            PrintCmdOutputSvc.printMsg(Optional.<String>empty());
        }
    }
}
