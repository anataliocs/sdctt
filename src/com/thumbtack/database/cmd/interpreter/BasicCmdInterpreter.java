package com.thumbtack.database.cmd.interpreter;

import com.thumbtack.database.cmd.executor.*;
import com.thumbtack.database.cmd.executor.data.*;
import com.thumbtack.database.cmd.executor.transaction.BeginCmd;
import com.thumbtack.database.cmd.executor.transaction.CommitCmd;
import com.thumbtack.database.cmd.executor.transaction.RollbackCmd;
import com.thumbtack.database.model.DataCommand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parses the user input and return a Command that will be executed by the com.thumbtack.database.
 */
public class BasicCmdInterpreter implements CmdInterpreter {
    private static final String DELIMITER = " ";

    private static final int FIRST_ARG = 1;
    private static final int SECOND_ARG = 2;

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

        return buildCommand(args);
    }

    private Cmd buildCommand(List<String> args) {
        final DataCommand command = DataCommand.getCommandFromType(args.get(0));

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
                    if (args.size() == DataCommand.SET.getRequiredNumOfArgs()) {
                        return new SetCmd(args.get(FIRST_ARG), args.get(SECOND_ARG));
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case GET:
                    if (args.size() == DataCommand.GET.getRequiredNumOfArgs()) {
                        return new GetCmd(args.get(FIRST_ARG));
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case NUMEQUALTO:
                    if (args.size() == DataCommand.NUMEQUALTO.getRequiredNumOfArgs()) {
                        return new NumEqualToCmd(args.get(FIRST_ARG));
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                case UNSET:
                    if (args.size() == DataCommand.UNSET.getRequiredNumOfArgs()) {
                        return new UnsetCmd(args.get(FIRST_ARG));
                    }
                    return new InvalidCmd(INVALID_NUM_OF_ARGS);
                default:
                    break;
            }
        }

        return new InvalidCmd(INVALID_CMD);
    }
}
