package Compulsory;

import Entities.Movies;
import Entities.MoviesGenres;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class MoviesGenresRepository {
    public MoviesGenresRepository() {
    }

    public static void createMovieGenre(MoviesGenres moviesGenres, EntityManagerFactory session) {
        EntityManager entityManager = session.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(moviesGenres);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
