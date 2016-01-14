package db.commands.impl;

import db.data.DataWrapper;

public class BeginCommand implements Command {
    @Override
    public void execute(DataWrapper dataWrapper) {
        dataWrapper.updateDataToNewTransaction();
        System.out.println();
    }
}
