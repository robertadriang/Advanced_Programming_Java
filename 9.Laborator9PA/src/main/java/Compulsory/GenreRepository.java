package Compulsory;

import Entities.Genres;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class GenreRepository {
    public GenreRepository() {
    }

    public static void createGenre(Genres genres, EntityManagerFactory session) {
        EntityManager entityManager = session.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(genres);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Genres findGenreById(Long id, EntityManagerFactory session) {
        EntityManager entityManager = session.createEntityManager();
        entityManager.getTransaction().begin();
        Genres genreRepository = entityManager.find(Genres.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        return genreRepository;
    }

    public static List<Genres> findGenreByName(String name, EntityManagerFactory session) {
        EntityManager entityManager = session.createEntityManager();

        entityManager.getTransaction().begin();
        Query q = entityManager.createNamedQuery("Genres.findByName").setParameter("name", name);
        entityManager.getTransaction().commit();

        List<Genres> gr = q.getResultList();

        entityManager.close();

        return gr;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
