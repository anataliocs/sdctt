package database.cmd.interpreter;

import database.cmd.executor.*;
import database.model.DataCommand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parses the user input and return a Command that will be executed by the database.
 */
public class BasicCmdInterpreter implements CmdInterpreter {
    private static final String DELIMITER = " ";

    private static final String INVALID_NUM_OF_ARGS = "Invalid # of arguments";
    private static final String INVALID_CMD = "Invalid Command";

    @Override
    public Cmd getCommand(String rawCommand) {
        if (rawCommand == null || rawCommand.isEmpty()) {
            return new InvalidCmd(INVALID_CMD);
        }

        List<String> rawCommands = Arrays.asList(rawCommand.trim().toUpperCase().split(DELIMITER));

        final List<String> args = rawCommands.stream()
                .filter(rc -> rc != null && !rc.isEmpty())
                .collect(Collectors.toList());

        final DataCommand command = DataCommand.getCommandFromType(args.get(0));

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
                    if (args.size() == 3) {
                        return new SetCmd(args.get(1), args.get(2));
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case GET:
                    if (args.size() == 2) {
                        return new GetCmd(args.get(1));
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case NUMEQUALTO:
                    if (args.size() == 2) {
                        return new NumEqualToCmd(args.get(1));
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case UNSET:
                    if (args.size() == 2) {
                        return new UnsetCmd(args.get(1));
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                default:
                    break;
            }
        }
        return new InvalidCmd(INVALID_CMD);
    }
}
