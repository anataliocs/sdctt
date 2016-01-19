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
public class CommitCmd implements Cmd {

    public static final DataCommand CMD_STRING = DataCommand.COMMIT;

    @Override
    public void execute(DataWrapper dataWrapper) {
        DataValues dataValues = dataWrapper.getDataValues();
        TransactionMgr transactionMgr = dataWrapper.getTransactionMgr();

        DataValues mergedTransaction = transactionMgr.commit(dataValues);
        if (mergedTransaction == null) {
            PrintCmdOutputSvc.printMsg(Optional.of(NO_TRANSACTION));
        } else {
            dataWrapper.setDataValues(mergedTransaction);
            transactionMgr.flush();
            PrintCmdOutputSvc.printMsg(Optional.<String>empty());
        }
    }
}
