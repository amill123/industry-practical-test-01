package musciapp;

import java.util.*;

/**
 * This the application for running the music app.
 * step b. - step i. modify this class appropriately
 *  @author Write your UPI here:  amil721
 */
public class MusicApp {
    // This is the main method. Do not change this!
    public static void main(String[] args) {
        new MusicApp().start();
    }

    // This is the start method. Do not change this!
    private void start() {
        System.out.println("Welcome to the 90's Music App.");
        int userChoice = -1;
        while (userChoice != 4) {
            System.out.println("===========================");
            mainMenu();
            userChoice = getUserMenuChoice();
            if (userChoice == 1) {
                System.out.println("Available Genres: Metal, Pop, Rap, Rock");
                String genre = getUserGenreChoice();
                Set<String> albumTitlesByGenre = getAlbumTitlesByGenre(AlbumCollection.ALBUMS, genre);
                System.out.println("-------------------------------------");
                System.out.println("All available " + genre.toLowerCase() + " albums:");
                System.out.println("-------------------");
                System.out.println(albumTitlesByGenre);
            }

            if (userChoice == 2) {
                System.out.println("Please enter any search terms: ");
                Set<Album> albums = findAlbumsByTitle(AlbumCollection.ALBUMS, Keyboard.readInput());
                System.out.println("-------------------------------------");
                if (albums.size() == 0) {
                    System.out.println("No albums found.");
                    continue;
                }
                System.out.println("Album(s) found:");
                System.out.println("-------------------");
                for (Album album : albums) {
                    System.out.println(album.getArtist() + ",  \"" + album.getTitle() + "\", " + album.getGenre() + ", " + album.getYear());
                }
            }

            if (userChoice == 3) {
                Map<String, Integer> artistsAndAlbums = processAlbums(AlbumCollection.ALBUMS);
                System.out.println("-------------------------------------");
                printAllArtists(artistsAndAlbums);
            }
        }
        System.out.println("Good bye! Please visit again!");
    }

    // This method displays the main menu. Do not change this!
    private void mainMenu() {
        System.out.println("1. Available Albums by Genre");
        System.out.println("2. Search Albums by Title");
        System.out.println("3. All Available Artists");
        System.out.println("4. Quit");
    }

    //step b. complete the validateGenre method
    private String validateGenre(String input) throws MusicGenreChoiceException {
        //If the input is zero or null throw MusicGenreChoiceException. You don't really need to handle null at this stage.
        if(input.length()==0 || input == null){
            throw new MusicGenreChoiceException("Empty Input!");
        }
        //compare the input case to the saved genres and return accordingly
        switch(input.toLowerCase()){
            case "metal":
                return AlbumCollection.METAL;
            case "rock":
                return AlbumCollection.ROCK;
            case "rap":
                return AlbumCollection.RAP;
            case "pop":
                return AlbumCollection.POP;
            default:
                throw new MusicGenreChoiceException("Invalid Genre!");
        }
    }

    // step c. modify the getUserGenreChoice method
    private String getUserGenreChoice() {
        String usersChoice = null;
        try {
            System.out.print("Please enter a genre: ");
            usersChoice = validateGenre(Keyboard.readInput());
            //Handle a MusicGenreChoiceException, re-call the getUserGenreChoice method to ensure that a valid genre gets entered
        } catch(MusicGenreChoiceException e){
            System.out.println(e.getMessage());
            usersChoice = getUserGenreChoice();
        }
        return usersChoice;
    }

    // step d. complete the validateUserInput method
    private int validateUserInput(String input) throws MenuInvalidChoiceException{
        //Throw a MenuInvalidChoiceException if there are no values entered.
        if(input.length() == 0){
            throw new MenuInvalidChoiceException("Empty Input!");
        }
        int userInput = 0;
        //Throw a MenuInvalidChoiceException if the userInput is not 1,2,3,4
        try {
            userInput = Integer.parseInt(input);
            if(userInput <1 || userInput > 4){
                throw new MenuInvalidChoiceException("Invalid number");
            }
        //Catch any values which are invalid numbers from trying to parse them to an integer. // Throw our custom MenuInvalidChoiceException in its pplace.
        } catch (NumberFormatException e) {
            throw new MenuInvalidChoiceException("Invalid number");
        }
        return userInput;
    }

    // step e. modify the getUserGenreChoice method
    private int getUserMenuChoice() {
        //initialise users choice
        int usersMenuChoice = 0;
        //introduce a try block so that you can handle the exceptions thrown from the validateUserInput method
        try {
                System.out.print("Please choose a number from the menu: ");
                usersMenuChoice = validateUserInput(Keyboard.readInput());

        } catch (MenuInvalidChoiceException e) {
            System.out.println(e.getMessage());
        }
        return usersMenuChoice;
    }

    // step f. complete the getAlbumTitlesByGenre method
    private Set<String> getAlbumTitlesByGenre(Album[] albums, String genre) {
        //Initialise a hashset
        Set<String> matchingAlbums = new HashSet<>();

        //Loop through the albums array and check if the entered genre matches the album genre. If yes add it to the hashset.
        for (int i = 0; i < albums.length; i++) {
            if(albums[i].getGenre().toLowerCase().equals(genre.toLowerCase())){
                matchingAlbums.add(albums[i].getTitle());
            }
        }
        return matchingAlbums;
    }

    //step g. complete the findAlbumsByTitle method
    private Set<Album> findAlbumsByTitle(Album[] albums, String query) {
        //We want to reask for a query if no value is provided or is null
        if(query.equals("") || query == null){
            System.out.print("Please enter a value: ");
            return findAlbumsByTitle(AlbumCollection.ALBUMS, Keyboard.readInput());

        } else {
            //Initialise a new hashset to store the albums that match the query
            Set<Album> albumsByTitle = new HashSet<>();

            //Loop through the albums array and check if they contain the query, if yes then they're added to the hashset.
            for (int i = 0; i < albums.length; i++) {
                if (albums[i].getTitle().toLowerCase().contains(query.toLowerCase())) {
                    albumsByTitle.add(albums[i]);
                }
            }

            return albumsByTitle;
        }

    }

    //step h. complete the processAlbums method
    private Map<String, Integer> processAlbums(Album[] albums) {
        //Initialise a hashmap to store the artist and the number of albums against the artist
        Map<String, Integer> mapAlbums = new HashMap<>();

        //For each album in the albums array we want to get the artist, check the hashmap to see if the artist already exists.
        //If the artist exists then increase the number of albums against the artist by 1. Otherwise initialise the artist's albums to 1
        for(Album album: albums){
            String artist = album.getArtist();
            if(mapAlbums.get(artist) == null){
                mapAlbums.put(artist,1);
            } else {
               int count = mapAlbums.get(artist);
               mapAlbums.put(artist, count+1);
            }
        }

        return mapAlbums;
    }

    //step i. complete the printAllArtists method
    private void printAllArtists(Map<String, Integer> artistsAndAlbums) {

    //Get all keys from the hashmap and get the associated value
        for(Map.Entry<String, Integer> artist: artistsAndAlbums.entrySet()){
            System.out.print("Artist: ");
            System.out.printf("%-20s   %20s", artist.getKey(),"Albums produced: "+artist.getValue()+"\n");
        }

    }

}
