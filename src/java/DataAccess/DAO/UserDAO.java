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
 *
 * @author ArqSoft
 */
@Stateless
public class UserDAO {
    
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    //public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public boolean persist(User user) {
        //em = emf.createEntityManager();
        em.persist(user);
        return true;
    }
    
    public boolean update(User user) {
        User dbUser = this.getById(user.getId());
        
        dbUser.setFirstname(user.getFirstname());
        dbUser.setLastname(user.getLastname());
        dbUser.setPassword(user.getPassword());
        
        return true;
    }
    
    public User getById(int id) {
        TypedQuery<User> q = em.createNamedQuery("User.findById", User.class);
        q.setParameter("id", id);
        
        return q.getSingleResult();
    }
    
    public User searchByUsername(String username) {
        //em = emf.createEntityManager();
        
        User user;
        Query query = em.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        
        user = (User) query.getSingleResult();
        return user;
    }  
}
