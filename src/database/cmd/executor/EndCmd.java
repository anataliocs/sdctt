package database.cmd.executor;

import database.data.DataWrapper;
import database.model.DataCommand;

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
