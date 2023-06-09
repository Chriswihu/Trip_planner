package facades;

import dtos.UserDto;
import entities.Role;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;

import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public static UserDto create(UserDto ud){
        User user = new User(ud.getUserName(), ud.getUserPass(), ud.getAddress(), ud.getPhone(), ud.getEmail(), ud.getBirthyear(), ud.getGender());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDto(user);
    }

    public UserDto updateUser(UserDto ud) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, ud.getUserName());
        user.setAddress(ud.getAddress());
        user.setPhone(ud.getPhone());
        user.setEmail(ud.getEmail());
        user.setBirthyear(ud.getBirthyear());
        user.setGender(ud.getGender());
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDto(user);
    }

    public List<UserDto> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        em.close();
        return UserDto.getDtos(users);
    }

    public UserDto getUserById(Long id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return new UserDto(user);
    }

    public UserDto deleteUser(Long id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDto(user);
    }



}
