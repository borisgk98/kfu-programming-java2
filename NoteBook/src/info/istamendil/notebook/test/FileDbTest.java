package info.istamendil.notebook.test;

import info.istamendil.notebook.data.FileDb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileDbTest {

    private final String path = "testFileDb.txt";
    private final FileDb db = new FileDb(path);
    private final String[] test = new String[]{"abacaba", "aba"};

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() throws Exception {
        db.save(test[0]);
        db.save(test[1]);
        String[] rez = (String[]) Files.readAllLines(Paths.get(path)).toArray();
        assertArrayEquals(rez, test);
    }

    @Test
    public void findAll() throws Exception {
        assertArrayEquals(db.findAll(), test);
    }

}