package com.thumbtack.database.cmd.interpreter;

import com.thumbtack.database.cmd.executor.*;
import com.thumbtack.database.cmd.executor.data.*;
import com.thumbtack.database.cmd.executor.transaction.BeginCmd;
import com.thumbtack.database.cmd.executor.transaction.CommitCmd;
import com.thumbtack.database.cmd.executor.transaction.RollbackCmd;
import junit.framework.TestCase;

public class BasicCmdInterpreterTest extends TestCase {

    public static final String DEFAULT_VAR = " a";
    public static final String DEFAULT_VAL = " 10";
    BasicCmdInterpreter classUnderTest = new BasicCmdInterpreter();

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testBeginCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                BeginCmd.CMD_STRING.name()
        );

        assertTrue(actualResponse instanceof BeginCmd);
    }

    public void testCommitCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                CommitCmd.CMD_STRING.name()
        );

        assertTrue(actualResponse instanceof CommitCmd);
    }

    public void testEndCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                EndCmd.CMD_STRING.name()
        );

        assertTrue(actualResponse instanceof EndCmd);
    }

    public void testGetCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                GetCmd.CMD_STRING.name() + DEFAULT_VAR
        );

        assertTrue(actualResponse instanceof GetCmd);
    }

    public void testInvalidCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                InvalidCmd.CMD_STRING.name()
        );

        System.out.println("actualResponse = " + actualResponse);

        assertTrue(actualResponse instanceof InvalidCmd);
    }

    public void testNumEqualToCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                NumEqualToCmd.CMD_STRING.name() + DEFAULT_VAL
        );

        assertTrue(actualResponse instanceof NumEqualToCmd);
    }

    public void testRollbackCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                RollbackCmd.CMD_STRING.name()
        );

        assertTrue(actualResponse instanceof RollbackCmd);
    }

    public void testSetCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                SetCmd.CMD_STRING.name() + DEFAULT_VAR + DEFAULT_VAL
        );

        assertTrue(actualResponse instanceof SetCmd);
    }

    public void testUnsetCmd() throws Exception {

        Cmd actualResponse = classUnderTest.getExecutableCommand(
                UnsetCmd.CMD_STRING.name() + DEFAULT_VAR
        );

        assertTrue(actualResponse instanceof UnsetCmd);
    }
}