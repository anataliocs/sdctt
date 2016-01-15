package database.cmd.interpreter;

import database.model.DataCommand;
import database.cmd.impl.*;

/**
 * Parses the user input and return a Command that will be executed by the database.
 */
public class BasicCmdInterpreter implements CmdInterpreter {
    public static final String DELIMITER = " ";

    @Override
    public Cmd getCommand(String rawCommand) {
        if (rawCommand == null) {
            return new InvalidCmd("Invalid Command");
        }
        rawCommand = rawCommand.trim().toUpperCase();

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
                    return new InvalidCmd("Invalid # of arguments");
                case GET:
                    if (args.length == 1) {
                        return new GetCmd(args[0]);
                    }
                    return new InvalidCmd("Invalid # of arguments");
                case NUMEQUALTO:
                    if (args.length == 1) {
                        return new NumEqualToCmd(args[0]);
                    }
                    return new InvalidCmd("Invalid # of arguments");
                case UNSET:
                    if (args.length == 1) {
                        return new UnsetCmd(args[0]);
                    }
                    return new InvalidCmd("Invalid # of arguments");
                default:
                    break;
            }
        }
        return new InvalidCmd("Invalid Command");
    }
}
