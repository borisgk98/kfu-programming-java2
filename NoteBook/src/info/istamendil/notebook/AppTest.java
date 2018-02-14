package info.istamendil.notebook;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class AppTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(System.in);
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void main() throws Exception {
    }

    @Test
    public void init() throws Exception {
    }

    @Test
    public void start() throws Exception {
        String test = "save\n1\n3\nreadAll\nsave\n2\nreadAll";
        ByteArrayOutputStream out = new ByteArrayOutputStream(), err = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(out) ,errStream = new PrintStream(err);
        System.setOut(outStream);
        System.setErr(errStream);

        Field field = System.class.getDeclaredField("in");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        InputStream copyStream = System.in;
        field.set(null, new ByteArrayInputStream(test.getBytes()));

        App app = new App(new String[]{""});
        assertEquals(out.toString(), ">> Unkown command\n>> [1]\n>> [1, 2]\nCan't read user input due error:");
        assertEquals(err.toString(), "Read error: No line found\n");

        field = System.class.getDeclaredField("in");
        field.setAccessible(true);
        modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, copyStream);
    }

}