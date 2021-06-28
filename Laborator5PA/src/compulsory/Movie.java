package compulsory;

public class Movie extends Item implements java.io.Serializable{
    double duration;
    String genre;
    double releaseYear;

    public Movie(){
        super(".mov");
        duration=100;
        genre="Comedy";
        releaseYear=2020;
    }

    public Movie(String name){
        super(name,".mov");
        duration=100;
        genre="Comedy";
        releaseYear=2020;
    }
    public Movie(String name, String path, String isbn, double price, double rating, double duration, String genre, double releaseYear) {
        super(name+".mov", path, isbn, price, rating);
        this.duration = duration;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
