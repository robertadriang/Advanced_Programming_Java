package compulsory;

public class Picture extends Item implements java.io.Serializable{
    double length;
    double width;


    public Picture(String name, String path, String isbn, double price, double rating, double length, double width) {
        super(name, path, isbn, price, rating);
        this.length = length;
        this.width = width;
    }

    public Picture() {
        super(".png");
        this.length = 220;
        this.width = 220;
    }

    public Picture(String name) {
        super(name,".png");
        this.length = 220;
        this.width = 220;
    }

    public Picture(String extension, double length, double width) {
        super(".png");
        this.length = length;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", length=" + length +
                ", width=" + width +
                '}';
    }
}
