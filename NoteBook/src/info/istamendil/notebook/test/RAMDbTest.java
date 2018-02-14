package info.istamendil.notebook.test;

import info.istamendil.notebook.data.RAMDb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RAMDbTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() throws Exception {
        RAMDb ramDb = new RAMDb();
        String testData = new String("abacaba");
        ramDb.save(testData);
        Class ramdbClass = ramDb.getClass();
        Field field = ramdbClass.getDeclaredField("data");
        field.setAccessible(true);
        String fieldValue = (String) ((ArrayList) field.get(ramDb)).get(0);
        assertEquals(fieldValue, testData);
    }

    @Test
    public void findAll() throws Exception {
        RAMDb ramDb = new RAMDb();
        String testData = new String("abacaba");
        ramDb.save(testData);
        assertArrayEquals(ramDb.findAll(), new String[]{testData});
    }

}