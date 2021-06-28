package Optional;

import Entities.Genres;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class RepositoryCreator<T> {
    public RepositoryCreator(){
    }
    public void createObj(T obj, EntityManagerFactory session){
        EntityManager entityManager = session.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public T findObjById(Long id,Class<T> aux,EntityManagerFactory session){
        EntityManager entityManager = session.createEntityManager();
        entityManager.getTransaction().begin();
        T repository = entityManager.find(aux, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        return repository;
    }

    public List<T> findObjByName(String name,Class<T> aux, EntityManagerFactory session) {
        EntityManager entityManager = session.createEntityManager();
        String className=aux.getName();
        var data=className.split("\\.");
        entityManager.getTransaction().begin();
        Query q = entityManager.createNamedQuery(data[data.length-1]+".findByName").setParameter("title", "%"+name+"%");
        entityManager.getTransaction().commit();

        List<T> ansList = q.getResultList();

        entityManager.close();

        return ansList;
    }
}
