package info.istamendil.study.simpleplayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class SimplePlaylist implements Playlist {
    protected ArrayList<Track> tracks;
    protected String name = "Default playlist";

    SimplePlaylist() {
        tracks = new ArrayList<>();
    }

    SimplePlaylist(String name) {
        this();
        this.name = name;
    }

    public SimplePlaylist(ArrayList<Track> tracks, String name) {
        this.tracks = tracks;
        this.name = name;
    }

    @Override
    public boolean add(Track track) {
        tracks.add(track);
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Playlist ");
        stringBuilder.append(name);
        stringBuilder.append(":\n");
        /**
         * 1
         */
        Object[] rez1 = this.tracks.stream().map(x -> "*) " + x.toString()  + "\n").toArray();
        //String[] tracks = (String[]) rez1;
        /**
         * 3
         */
        stringBuilder.append(Arrays.stream(rez1).
                reduce((x, y) -> x.toString() + y.toString()).
                orElse(0).
                toString());
        return stringBuilder.toString();
    }

    public void saveToDisk() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(this.name + ".playlist"));
        pw.write(this.toString());
        pw.close();
    }

    public SimplePlaylist filterByStyle(String styleName) {
        /**
         * 2
         */
        return new SimplePlaylist(new ArrayList<Track>() {{
                    for (Object t : tracks.stream().filter(x -> x.getStyle().equals(styleName)).toArray()) {
                        add((Track) (t));
                    }
                }},
                this.name + ":style-" + styleName);
    }
}
