package db.commands.parser;

import db.model.DataCommand;
import db.commands.impl.*;

/**
 * Parses the user input and return a Command that will be executed by the database.
 */
public class SimpleCommandParser implements CommandParser {
    public static final String DELIMITER = " ";

    @Override
    public Command getCommand(String rawCommand) {
        if (rawCommand == null) {
            return new InvalidCommand("The inserted command does not exist!");
        }
        rawCommand = rawCommand.trim().toLowerCase();

        DataCommand command;
        String[] args = new String[0];
        //parse input to get type and arguments
        if (rawCommand.contains(DELIMITER)) {
            Integer spacePos = rawCommand.indexOf(DELIMITER);
            String type = rawCommand.substring(0, spacePos);
            command = DataCommand.getCommandFromType(type);
            String arguments = rawCommand. substring(spacePos + 1);
            args = arguments.trim().split(" ");
        } else {
            command = DataCommand.getCommandFromType(rawCommand);
        }

        //create command
        if (command != null) {
            switch (command) {
                case END:
                    return new EndCommand();
                case BEGIN:
                    return new BeginCommand();
                case COMMIT:
                    return new CommitCommand();
                case ROLLBACK:
                    return new RollbackCommand();
                case SET:
                    if (args.length == 2) {
                        return new SetCommand(args[0], args[1]);
                    }
                    return new InvalidCommand("Invalid number of arguments");
                case GET:
                    if (args.length == 1) {
                        return new GetCommand(args[0]);
                    }
                    return new InvalidCommand("Invalid number of arguments");
                case NUMEQUALTO:
                    if (args.length == 1) {
                        return new NumEqualToCommand(args[0]);
                    }
                    return new InvalidCommand("Invalid number of arguments");
                case UNSET:
                    if (args.length == 1) {
                        return new UnsetCommand(args[0]);
                    }
                    return new InvalidCommand("Invalid number of arguments");
                default:
                    break;
            }
        }
        return new InvalidCommand("The inserted command does not exist!");
    }
}
