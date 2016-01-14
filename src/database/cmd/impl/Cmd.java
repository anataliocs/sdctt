package database.cmd.impl;

import database.data.DataWrapper;

/**
 * Created by canatalio on 1/14/16.
 */
public interface Cmd {
    public abstract void execute(DataWrapper dataWrapper);
}
