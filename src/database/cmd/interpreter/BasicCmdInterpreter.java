package database.cmd.interpreter;

import database.cmd.executor.*;
import database.model.DataCommand;

/**
 * Parses the user input and return a Command that will be executed by the database.
 */
public class BasicCmdInterpreter implements CmdInterpreter {
    public static final String DELIMITER = " ";

    public static final String INVALID_NUM_OF_ARGS = "Invalid # of arguments";
    public static final String INVALID_CMD = "Invalid Command";

    @Override
    public Cmd getCommand(String rawCommand) {
        if (rawCommand == null) {
            return new InvalidCmd(INVALID_CMD);
        }
        rawCommand = rawCommand.trim().toUpperCase();

        DataCommand command;
        String[] args = new String[0];
        //parse input to get type and arguments
        if (rawCommand.contains(DELIMITER)) {
            Integer spacePos = rawCommand.indexOf(DELIMITER);
            String type = rawCommand.substring(0, spacePos);
            command = DataCommand.getCommandFromType(type);
            String arguments = rawCommand.substring(spacePos + 1);
            args = arguments.trim().split(" ");
        } else {
            command = DataCommand.getCommandFromType(rawCommand);
        }

        //create command
        if (command != null) {
            switch (command) {
                case END:
                    return new EndCmd();
                case BEGIN:
                    return new BeginCmd();
                case COMMIT:
                    return new CommitCmd();
                case ROLLBACK:
                    return new RollbackCmd();
                case SET:
                    if (args.length == 2) {
                        return new SetCmd(args[0], args[1]);
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case GET:
                    if (args.length == 1) {
                        return new GetCmd(args[0]);
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case NUMEQUALTO:
                    if (args.length == 1) {
                        return new NumEqualToCmd(args[0]);
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case UNSET:
                    if (args.length == 1) {
                        return new UnsetCmd(args[0]);
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                default:
                    break;
            }
        }
        return new InvalidCmd(INVALID_CMD);
    }
}
