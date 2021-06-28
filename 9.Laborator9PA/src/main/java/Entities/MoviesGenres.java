package Entities;

import javax.persistence.*;

@Entity
@Table(name = "MOVIES_GENRES", schema = "STUDENT")
public class MoviesGenres {
    private Long id;
    private Movies moviesByMovieId;
    private Genres genreByGenresId;

    public MoviesGenres() {
    }
    public MoviesGenres(Long l, Movies m, Genres g) {
        this.id=l;
        this.moviesByMovieId=m;
        this.genreByGenresId=g;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID", nullable = false)
    public Movies getMoviesByMovieId() {
        return moviesByMovieId;
    }

    public void setMoviesByMovieId(Movies moviesByMovieId) {
        this.moviesByMovieId = moviesByMovieId;
    }

    @ManyToOne
    @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID", nullable = false)
    public Genres getGenresByGenreId() {
        return genreByGenresId;
    }

    public void setGenresByGenreId(Genres genreByGenresId) {
        this.genreByGenresId = genreByGenresId;
    }
}
