package database.cmd.msg;

import java.util.Optional;

/**
 * Created by canatalio on 1/14/16.
 */
public class PrintCmdOutput {

    public static void printMsg(Optional<String> msg) {
        System.out.println(msg.orElse(""));
    }
}
