package info.istamendil.notebook.test;

import info.istamendil.notebook.utils.ConsoleUserInteractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ConsoleUserInteractorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(System.in);
    }

    @Test
    public void readCommand() throws Exception {
        ConsoleUserInteractor consoleUserInteractor = new ConsoleUserInteractor();
        String command = "a";
        ByteArrayInputStream in = new ByteArrayInputStream("a\n".getBytes());
        System.setIn(in);
        assertEquals(command, consoleUserInteractor.readCommand());
    }

}