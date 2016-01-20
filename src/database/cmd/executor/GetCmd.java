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
public class GetCmd implements Cmd {
    public static final DataCommand CMD_STRING = DataCommand.GET;
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
            // Flag key as deleted
            PrintCmdOutputSvc.printMsg(Optional.of(VALUE_NOT_FOUND));
        } else {
            // Find key
            String value = dataValues.getKeyValue(name);
            if (value == null) {
                value = transactionMgr.getCurrentValForKey(name);
            }

            if (value == null) {
                PrintCmdOutputSvc.printMsg(Optional.of(VALUE_NOT_FOUND));
            } else {
                PrintCmdOutputSvc.printMsg(Optional.of(value));
                // Cache value
                dataValues.setData(name, value);
            }
        }
    }

}
