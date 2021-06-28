package Compulsory;

import Entities.Movies;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class MovieRepository {
    public MovieRepository() {
    }

    public static void createMovie(Movies movies, EntityManagerFactory session) {
        EntityManager entityManager = session.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(movies);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Movies findMovieById(Long id, EntityManagerFactory session) {
        EntityManager entityManager = session.createEntityManager();
        entityManager.getTransaction().begin();
        Movies movie = entityManager.find(Movies.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        return movie;
    }

    public static List<Movies> findMovieByTitle(String title, EntityManagerFactory session) {
        EntityManager entityManager = session.createEntityManager();

        entityManager.getTransaction().begin();
        Query q = entityManager.createNamedQuery("Movies.findByName").setParameter("title", "%"+title+"%");
        entityManager.getTransaction().commit();

        List<Movies> gr = q.getResultList();

        entityManager.close();

        return gr;
    }

    public static List<Movies> listAllMovies(String orderCriteria,boolean asc, EntityManagerFactory session){
        EntityManager entityManager = session.createEntityManager();
        entityManager.getTransaction().begin();
        String selectName="Movies.listAllMoviesBy" + orderCriteria;
        Query q = entityManager.createNamedQuery(selectName);
        List<Movies> movies = q.getResultList();
        entityManager.close();
        if(!asc)
            Collections.reverse(movies);
        return movies;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
