package com.thumbtack.database.cmd.executor;

import com.thumbtack.database.data.DataWrapper;

/**
 * Created by canatalio on 1/14/16.
 */
public interface Cmd {

    public static final String NO_TRANSACTION = "NO TRANSACTION";

    public abstract void execute(DataWrapper dataWrapper);
}
