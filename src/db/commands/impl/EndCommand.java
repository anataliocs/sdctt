package db.commands.impl;

import db.data.DataWrapper;

public class EndCommand implements Command {

    @Override
    public void execute(DataWrapper dataWrapper) {
        System.exit(0);
    }
}
