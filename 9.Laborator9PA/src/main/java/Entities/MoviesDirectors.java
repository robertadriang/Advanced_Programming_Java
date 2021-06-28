package Entities;

import javax.persistence.*;

@Entity
@Table(name = "MOVIES_DIRECTORS", schema = "STUDENT", catalog = "")
public class MoviesDirectors {
    private Long id;
    private Movies moviesByMovieId;
    private Directors directorsByDirectorId;

    @Id
    @GeneratedValue
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
    @JoinColumn(name = "DIRECTOR_ID", referencedColumnName = "ID", nullable = false)
    public Directors getDirectorsByDirectorId() {
        return directorsByDirectorId;
    }

    public void setDirectorsByDirectorId(Directors directorsByDirectorId) {
        this.directorsByDirectorId = directorsByDirectorId;
    }
}
