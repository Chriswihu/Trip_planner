package facades;

import entities.Guide;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class GuideFacade {

    private static GuideFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private GuideFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GuideFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GuideFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Guide create(Guide guide){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(guide);
            em.getTransaction().commit();
            return guide;
        }finally {
            em.close();
        }
    }

    public Guide getById(long id){
        EntityManager em = getEntityManager();
        try{
            return em.find(Guide.class, id);
        }finally {
            em.close();
        }
    }

    public List<Guide> getAll(){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Guide> query = em.createQuery("SELECT g FROM Guide g", Guide.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    public Guide edit(Guide guide){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(guide);
            em.getTransaction().commit();
            return guide;
        }finally {
            em.close();
        }
    }

    public Guide delete(long id){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Guide guide = em.find(Guide.class, id);
            em.remove(guide);
            em.getTransaction().commit();
            return guide;
        }finally {
            em.close();
        }
    }



}
