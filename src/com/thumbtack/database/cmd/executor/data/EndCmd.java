package com.thumbtack.database.cmd.executor.data;

import com.thumbtack.database.cmd.executor.Cmd;
import com.thumbtack.database.data.DataWrapper;
import com.thumbtack.database.model.DataCommand;

/**
 * Created by canatalio on 1/14/16.
 */
public class EndCmd implements Cmd {

    public static final DataCommand CMD_STRING = DataCommand.END;

    @Override
    public void execute(DataWrapper dataWrapper) {
        System.exit(0);
    }
}
