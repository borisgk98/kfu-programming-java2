package info.istamendil.study.simpleplayer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTrack implements Track {
    protected Path path;
    protected String name;

    FileTrack(String path) {
        this.path = Paths.get(path);
        String[] splited = path.split(File.separator);
        this.name = splited[splited.length - 1];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStyle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return name;
    }
}
