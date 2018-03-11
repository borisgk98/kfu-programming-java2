package info.istamendil.study.simpleplayer;

public class SimpleTrack implements Track {
    protected String name, author, date, album, style;

    public SimpleTrack(String name, String author, String date, String album, String style) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.album = album;
        this.style = style;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStyle() {
        return this.style;
    }

    @Override
    public String toString() {
        return String.format("Name - %s, Author - %s, Date - %s, Album - %s, Style - %s",
                name, author, date, album, style);
    }
}
