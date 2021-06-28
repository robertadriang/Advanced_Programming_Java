package Entities;

import javax.persistence.*;

@Entity
@Table(name = "MOVIES_ACTORS", schema = "STUDENT", catalog = "")
public class MoviesActors {
    private Long id;
    private Movies moviesByMovieId;
    private Actors actorsByActorId;

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
    @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ID", nullable = false)
    public Actors getActorsByActorId() {
        return actorsByActorId;
    }

    public void setActorsByActorId(Actors actorsByActorId) {
        this.actorsByActorId = actorsByActorId;
    }
}
