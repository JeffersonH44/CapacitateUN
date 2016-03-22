/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase encargada del acceso a datos de usuarios.
 * @author Jefferson
 */
@Stateless
public class UserDAO {
    
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    //public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    /**
     * Guarda un nuevo usuario en la base de datos
     * @param user
     * @return verdadero en caso de que guarde exitosamente.
     */
    public boolean persist(User user) {
        //em = emf.createEntityManager();
        em.persist(user);
        return true;
    }
    
    /**
     * Actualiza un usuario a partir de su identificador, actualiza nombres, apellidos y contraseña.
     * @param user
     * @return verdadero en caso de que se actualize correctamente.
     */
    public boolean update(User user) {
        User dbUser = this.getById(user.getId());
        
        dbUser.setFirstname(user.getFirstname());
        dbUser.setLastname(user.getLastname());
        dbUser.setPassword(user.getPassword());
        
        return true;
    }
    
    /**
     * Obtiene el usuario a partir del identificador
     * @param id - identificador de usuario
     * @return El usuario encontrado o null en caso contrario.
     */
    public User getById(int id) {
        TypedQuery<User> q = em.createNamedQuery("User.findById", User.class);
        q.setParameter("id", id);
        
        return q.getSingleResult();
    }
    
    /**
     * Obtiene el usuario a partir de su nombre de usuario.
     * @param username - nombre de usuario
     * @return El usuario encontrado o null en caso contrario.
     */
    public User searchByUsername(String username) {
        User user;
        Query query = em.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        
        user = (User) query.getSingleResult();
        return user;
    }  
}
