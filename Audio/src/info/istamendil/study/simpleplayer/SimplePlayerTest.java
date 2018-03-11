package info.istamendil.study.simpleplayer;

import java.io.File;

import static org.junit.Assert.*;

public class SimplePlayerTest {

    @org.junit.Test
    public void play() {
        (new SimplePlayer()).play(new File("/home/boris/progs/kfu-programming-java2/Audio/1nylon-guitar-girl-from-ipanema.wav"));
    }
}