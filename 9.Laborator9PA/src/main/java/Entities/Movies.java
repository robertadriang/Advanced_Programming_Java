package Entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@NamedQuery(name="Movies.findByName",
        query="SELECT movies from Movies movies WHERE movies.title like :title")
@NamedQuery(name="Movies.listAllMoviesByTitle",
        query="SELECT movies from Movies movies ORDER BY title" )
@NamedQuery(name="Movies.listAllMoviesByScore",
        query="SELECT movies from Movies movies ORDER BY score" )
@NamedQuery(name="Movies.listAllMoviesByDuration",
        query="SELECT movies from Movies movies ORDER BY duration" )
@NamedQuery(name="Movies.listAllMoviesByReleaseDate",
        query="SELECT movies from Movies movies ORDER BY releaseDate" )
public class Movies {
    private Long id;
    private String title;
    private Time releaseDate;
    private Long duration;
    private Long score;

    public Movies(Long id, String title, Time releaseDate, Long duration, Long score) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
    }

    public Movies() {

    }

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "RELEASE_DATE")
    public Time getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Time releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "DURATION")
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "SCORE")
    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return Objects.equals(id, movies.id) && Objects.equals(title, movies.title) && Objects.equals(releaseDate, movies.releaseDate) && Objects.equals(duration, movies.duration) && Objects.equals(score, movies.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, duration, score);
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                ", score=" + score +
                '}';
    }
}
