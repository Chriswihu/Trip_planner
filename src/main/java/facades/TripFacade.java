package facades;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dtos.GuideDto;
import dtos.RenameMeDTO;
import dtos.TripDto;
import entities.Guide;
import entities.RenameMe;
import entities.Trip;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class TripFacade {

    private static EntityManagerFactory emf;
    private static TripFacade instance;

    private TripFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static TripFacade getTripFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TripFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static List<TripDto> getAll(List<Trip> trips){
        return TripDto.getDtos(trips);
    }
    
//    public static TripDto create(TripDto td){
//        Trip trip = new Trip(td.getName(), td.getDate(), td.getTime(), td.getLocation(), td.getDuration(), td.getPackinglist(), td.getGuideDto());
//
//        GuideDto guideDto = td.getGuideDto();
//
//        Guide guide = new Guide(guideDto.getName(), guideDto.getGender(), guideDto.getBirthyear(), guideDto.getProfile(), guideDto.getImageUrl());
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(trip);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        return new TripDto(trip);
//    }

    public static TripDto getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Trip trip = em.find(Trip.class, id);

        return new TripDto(trip);
    }

    public static List<TripDto> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t", Trip.class);
        List<Trip> trips = query.getResultList();
        return TripDto.getDtos(trips);
    }

//    public void assignUser(String json) {
//        Gson gson = new Gson();
//        JsonObject jo = gson.fromJson(json, JsonObject.class);
//        long tripId = jo.get("tripId").getAsLong();
//        long userId = jo.get("userId").getAsLong();
//        EntityManager em = emf.createEntityManager();
//        Trip trip = em.find(Trip.class, tripId);
//        trip.addUser();
//        try {
//            em.getTransaction().begin();
//            em.merge(trip);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }

    public static TripDto edit(TripDto td) {
        EntityManager em = emf.createEntityManager();
        Trip trip = em.find(Trip.class, td.getId());
        trip.setName(td.getName());
        trip.setDate(td.getDate());
        trip.setTime(td.getTime());
        trip.setLocation(td.getLocation());
        trip.setDuration(td.getDuration());
        trip.setPackinglist(td.getPackinglist());
//        trip.setGuide(td.getGuide());
//        trip.setUsers(td.getUsers());
        try {
            em.getTransaction().begin();
            em.merge(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDto(trip);
    }

    public static TripDto delete(long id) {
        EntityManager em = emf.createEntityManager();
        Trip trip = em.find(Trip.class, id);
        try {
            em.getTransaction().begin();
            em.remove(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDto(trip);
    }


//    public void createTrip(TripDto tripDto) {
//        EntityManager em = emf.createEntityManager();
//        Trip trip = new Trip(tripDto.getName(), tripDto.getDate(), tripDto.getTime(), tripDto.getLocation(), tripDto.getDuration(), tripDto.getPackinglist(), tripDto.getGuideDto());
//        try {
//            em.getTransaction().begin();
//            em.persist(trip);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
}
