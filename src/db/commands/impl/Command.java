package db.commands.impl;

import db.data.DataWrapper;

public interface Command {
    public abstract void execute(DataWrapper dataWrapper);
}
