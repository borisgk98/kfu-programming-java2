package info.istamendil.study.simpleplayer;

import info.istamendil.study.utils.*;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Code for studying purposes. Programming course. Kazan Federal University,
 * ITIS. http://study.istamendil.info/
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class App {

    public static void main(String[] args) {
        new App(new SimplePlayer(), new ConsoleUserInteractor());
    }

    private Path musicDir;
    private final Player player;
    private final UserInteractor terminal;
    private Map<String, Playlist> playlists;
    private String currentPlaylist;

    public App(Player player, UserInteractor interactor) {
        this.player = player;
        this.terminal = interactor;
        this.playlists = new HashMap<>();
        this.playlists.put("Default", new SimplePlaylist("Default"));
        this.currentPlaylist = "Default";
        this.start();
    }

    public void start() {
        try {
            try {
                this.musicDir = Paths.get(".").toAbsolutePath().normalize();
                // Examine directory for readability
            } catch (InvalidPathException ex) {
                this.terminal.print("Can't read directory");
                System.exit(1);
            }
            String command;
            label:
            while ((command = this.terminal.readCommand()) != null) {
                switch (command) {
                    case "play":
                        File[] tracks = this.musicDir.toFile().listFiles(new MusicFileFilter());
                        if (tracks.length > 0) {
                            this.terminal.print("Starting to play: " + tracks[0].getName());
                            this.player.play(tracks[0]);
                        }
                        break;
                    case "stop":
                        this.player.stop();
                        break;
                    case "add":
                        String[] args = terminal.readCommand().split(" +");
                        playlists.get(currentPlaylist).add(new SimpleTrack(args[0], args[1], args[2], args[3], args[4]));
                        break;
                    case "addplaylist":
                        String newPlaylistName = terminal.readCommand();
                        playlists.put(newPlaylistName, new SimplePlaylist(newPlaylistName));
                        break;
                    case "changeplaylist":
                        String newCurrentPlaylist = terminal.readCommand();
                        if (playlists.containsKey(newCurrentPlaylist)) {
                            currentPlaylist = newCurrentPlaylist;
                        }
                        break;
                    case "printcurrentplaylist":
                        System.out.println(playlists.get(currentPlaylist).toString());
                        break;
                    case "savecurrentplaylist":
                        playlists.get(currentPlaylist).saveToDisk();
                        break;
                    case "sortcurrentplaylistbystyle":
                        Playlist newpl = playlists.get(currentPlaylist).filterByStyle(terminal.readCommand());
                        playlists.put(newpl.getName(), newpl);
                        break;
                    case "list":
                        for (String s : playlists.keySet()) {
                            System.out.println(s);
                        }
                        break;
                    case "exit":
                        break label;
                    default:
                        this.terminal.print("Unkown command");
                }
            }
        } catch (UserInteractorReadException ex) {
            System.out.println("Can't read user input due error:");
            System.err.println(ex.getMessage());
            System.exit(1);
        } catch (UserInteractorWriteException ex) {
            System.out.println("Can't print data to user due error:");
            System.err.println(ex.getMessage());
            System.exit(1);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private static class MusicFileFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            String[] parts = pathname.getName().split("\\.");
            if (parts.length > 0) {
                return parts[parts.length - 1].equals("wav");
            }
            return false;
        }

    }
}
