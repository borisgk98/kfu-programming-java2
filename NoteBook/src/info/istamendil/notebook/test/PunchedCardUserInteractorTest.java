package info.istamendil.notebook.test;

import com.sun.nio.zipfs.ZipPath;
import info.istamendil.notebook.utils.PunchedCardUserInteractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PunchedCardUserInteractorTest {

    private final String pathString = "testPunchedCardUserInteractor.txt",
            test = "abacaba\naba";
    private final Path path = Paths.get(pathString);
    private PunchedCardUserInteractor punchedCardUserInteractor;


    @Before
    public void setUp() throws Exception {
        Files.write(path, test.getBytes());
        punchedCardUserInteractor = new PunchedCardUserInteractor(path);
        Class punchedCardUserInteractorClass = punchedCardUserInteractor.getClass();
        Field field = punchedCardUserInteractorClass.getDeclaredField("lines");

        // for final field
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        // Как при переименовании полей не ломать тестирование????

        String[] fieldValue = ((String[]) field.get(punchedCardUserInteractor));
        assertArrayEquals(fieldValue, test.split("\n"));
    }

    @After
    public void tearDown() throws Exception {
        Files.delete(path);
    }

    @Test
    public void readCommand() throws Exception {
        assertEquals(punchedCardUserInteractor.readCommand(), test.split("\n")[0]);
        assertEquals(punchedCardUserInteractor.readCommand(), test.split("\n")[1]);
    }

}