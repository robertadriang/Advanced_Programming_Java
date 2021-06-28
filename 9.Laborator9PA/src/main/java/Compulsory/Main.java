package Compulsory;

import Entities.Genres;
import Entities.Movies;
import Entities.MoviesGenres;
import Optional.Chart;
import Optional.RepositoryCreator;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        var instance=Manager.getInstance();
//        GenreRepository.createGenre(new Genres(0L,"Manele"),instance);
//        GenreRepository.createGenre(new Genres(1L,"Manele"),instance);
//        MovieRepository.createMovie(new Movies(0L,"Shark Tale",null,120L,5L),instance);
//        MovieRepository.createMovie(new Movies(1L,"Godfather",null,150L,7L),instance);
        System.out.println(GenreRepository.findGenreById(0L,instance));
        System.out.println(GenreRepository.findGenreByName("Manele",instance));
//        System.out.println(MovieRepository.findMovieByTitle("Shark Tale",instance));
        System.out.println(MovieRepository.listAllMovies("Score",false,instance));
        System.out.println(MovieRepository.findMovieByTitle("Titanic",instance));
        Chart chart = new Chart("Ordered by score",MovieRepository.listAllMovies("Duration",false,instance),new Date(System.currentTimeMillis()));
        chart.print();

//        MoviesGenresRepository.createMovieGenre(new MoviesGenres(1L,new Movies(0L,"Shark Tale",null,120L,5L),new Genres(0L,"Comedy")),instance);

        RepositoryCreator<Movies> test=new RepositoryCreator<Movies>();
        System.out.println(test.findObjByName("Titanic",Movies.class,instance));
        RepositoryCreator<Genres> test2=new RepositoryCreator<Genres>();
        System.out.println(test2.findObjById(0L,Genres.class,instance));


    }
}
