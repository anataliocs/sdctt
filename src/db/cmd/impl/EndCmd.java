package db.cmd.impl;

import db.data.DataWrapper;

/**
 * Created by canatalio on 1/14/16.
 */
public class EndCmd implements Cmd {

    @Override
    public void execute(DataWrapper dataWrapper) {
        System.exit(0);
    }
}
