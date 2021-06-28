package Optional;

import Compulsory.MovieRepository;
import Entities.Movies;

import java.util.Date;
import java.util.List;

public class Chart {

    private String name;
    private List<Movies> movieList;
    private Date dateCreated;

    public Chart(String name, List<Movies> movieList, Date dateCreated) {
        this.name = name;
        this.movieList = movieList;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movies> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movies> movieList) {
        this.movieList = movieList;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void print() {
        System.out.println("\nChart: " + name);
        System.out.println("Chart date created: " + dateCreated);
        int i = 0;
        for (Movies movie : movieList)
        {
            System.out.println();
            System.out.println(i + ". Title:" + movie.getTitle());
            System.out.println("   Release date: " + movie.getReleaseDate());
            System.out.println("   Length: " + movie.getDuration());
            System.out.println("   Score: " + movie.getScore());
            i++;
        }
    }
}
