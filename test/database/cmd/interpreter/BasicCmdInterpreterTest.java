package database.cmd.interpreter;

import database.cmd.executor.*;
import junit.framework.TestCase;

public class BasicCmdInterpreterTest extends TestCase {

    BasicCmdInterpreter classUnderTest = new BasicCmdInterpreter();

    public static final String DEFAULT_VAR = " a";
    public static final String DEFAULT_VAL = " 10";

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testBeginCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                BeginCmd.CMD_STRING.name()
        );

        assertTrue(actualResponse instanceof BeginCmd);
    }

    public void testCommitCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                CommitCmd.CMD_STRING.name()
        );

        assertTrue(actualResponse instanceof CommitCmd);
    }

    public void testEndCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                EndCmd.CMD_STRING.name()
        );

        assertTrue(actualResponse instanceof EndCmd);
    }

    public void testGetCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                GetCmd.CMD_STRING.name() + DEFAULT_VAR
        );

        assertTrue(actualResponse instanceof GetCmd);
    }

    public void testInvalidCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                InvalidCmd.CMD_STRING.name()
        );

        System.out.println("actualResponse = " + actualResponse);

        assertTrue(actualResponse instanceof InvalidCmd);
    }

    public void testNumEqualToCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                NumEqualToCmd.CMD_STRING.name() + DEFAULT_VAL
        );

        assertTrue(actualResponse instanceof NumEqualToCmd);
    }

    public void testRollbackCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                RollbackCmd.CMD_STRING.name()
        );

        assertTrue(actualResponse instanceof RollbackCmd);
    }

    public void testSetCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                SetCmd.CMD_STRING.name() + DEFAULT_VAR + DEFAULT_VAL
        );

        assertTrue(actualResponse instanceof SetCmd);
    }

    public void testUnsetCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getCommand(
                UnsetCmd.CMD_STRING.name() + DEFAULT_VAR
        );

        assertTrue(actualResponse instanceof UnsetCmd);
    }
}