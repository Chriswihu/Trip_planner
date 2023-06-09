package facades;

import dtos.GuideDto;
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

    private GuideFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GuideFacade getGuideFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GuideFacade();
        }
        return instance;
    }

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static GuideDto create(GuideDto guideDto){
        Guide guide = new Guide(guideDto.getName(), guideDto.getGender(), guideDto.getBirthyear(), guideDto.getProfile(), guideDto.getImageUrl());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(guide);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new GuideDto(guide);
    }





}
