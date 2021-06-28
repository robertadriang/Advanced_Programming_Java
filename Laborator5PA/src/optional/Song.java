package optional;

public class Song extends Item implements java.io.Serializable{
    double duration;
    String genre;
    double releaseYear;

    public Song(){
        super(".mp4");
        duration=3.33;
        genre="Pop";
        releaseYear=2021;
    }

    public Song(String name){
        super(name+".mp4");
        duration=3.33;
        genre="Pop";
        releaseYear=2021;
    }


    public Song(String name, String path, String isbn, double price, double rating, double duration, String genre, double releaseYear) {
        super(name+".mp4", path, isbn, price, rating);
        this.duration = duration;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Song{" +
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
