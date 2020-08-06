package musciapp;

/**
 * This class is used for storing each album entry.
 * Do not modify this class!
 *
 * @author Yu-Cheng Tu
 */
public class Album {
    private String genre;
    private String artist;
    private String title;
    private int year;

    public Album(String title, String artist, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year= year;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

}
