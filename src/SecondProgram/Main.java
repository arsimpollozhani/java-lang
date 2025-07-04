package SecondProgram;
import java.util.Scanner;

class Song {
    private String title;
    private String author;
    private String performer;
    private int duration;

    public Song (String title, String author, String performer, int duration) {
        this.title = title;
        this.author = author;
        this.performer = performer;
        this.duration = duration;
    }

    public void input () {
        Scanner sc = new Scanner(System.in);
        title = sc.nextLine();
        author = sc.nextLine();
        performer = sc.nextLine();
        duration = sc.nextInt();
    }

    public void printSong () {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Performer: " + performer);
        System.out.println("Duration: " + duration);
    }

    public boolean same(Song other) {
        return this.title.equals(other.title)
                && this.author.equals(other.author)
                && this.performer.equals(other.performer)
                && this.duration == other.duration;
    }
    //getters and setters

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPerformer() {
        return performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

class Festival {
    private String name;
    private String city;
    private String date;
    private Song []songs;
    private int n;

    public Festival (String name, String city, String date, Song []songs, int n) {
        this.name = name;
        this.city = city;
        this.date = date;
        this.n = n;
        this.songs = new Song[this.n];
        System.arraycopy(songs, 0, this.songs, 0, this.n);
    }

    public void printFestival () {
        System.out.println("Festival: " + name);
        System.out.println("City: " + city);
        System.out.println("Date: " + date);
        System.out.println("Songs: ");

        for (int i = 0; i < n; i++) {
            songs[i].printSong();
        }
    }

    public void addSong (Song other) {
        if (n < songs.length) {
            songs[n++] = other;
        } else {
            System.out.println("Maximum capacity reached!");
        }
    }

    public void deleteSong (Song other) {
        if (n == 0) {
            System.out.println("Trying to delete from an empty list!");
            return;
        }

        Song[] tmp = new Song[n];

        int j = 0;

        for (int i = 0; i < n; i++) {
            if (!songs[i].same(other)) {
                tmp[j++] = songs[i];
            }
        }

        Song [] result = new Song[j];
        System.arraycopy(tmp, 0, result, 0, j);

        this.songs = result;
        this.n = j;
    }

    Festival notFromAuthor (String author) {
        Song[] tmp = new Song[this.n];
        int j = 0;

        for (int i = 0; i < this.n; i++) {
            if (!songs[i].getAuthor().equals(author)) {
                tmp[j++] = songs[i];
            }
        }

        Song [] filteredSongs = new Song[j];
        System.arraycopy(tmp, 0, filteredSongs, 0, j);

        return new Festival(name, city, date, filteredSongs, j);
    }

}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("How many songs do you want to enter: ");
        int n = sc.nextInt();
        sc.nextLine(); //clear the buffer

        Song [] songs = new Song[n];

        for (int i = 0; i < n; i++) {
            String title = sc.nextLine();
            String author = sc.nextLine();
            String performer = sc.nextLine();
            int duration = sc.nextInt();
            sc.nextLine();

            songs[i] = new Song(title, author, performer, duration);
        }

        for (Song s : songs) {
            s.printSong();
        }

        sc.close();



        Festival festival = new Festival("Coachella", "Riverside Couny", "10.04.2025", songs, songs.length);
        festival.deleteSong(songs[0]);



        System.out.println ("=====After deleting the first song=====");


        festival.printFestival();
    }
}


