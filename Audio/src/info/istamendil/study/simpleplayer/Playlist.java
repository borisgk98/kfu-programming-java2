package info.istamendil.study.simpleplayer;

import java.io.FileNotFoundException;

public interface Playlist {
    boolean add(Track track);
    String getName();
    void saveToDisk() throws FileNotFoundException;
    public SimplePlaylist filterByStyle(String styleName);
}
