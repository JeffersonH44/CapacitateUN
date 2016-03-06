/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        //EntityManager em = emf.createEntityManager();
        em.persist(user);
        return true;
    }
    
    public User searchByUsername(String username) {
        //EntityManager em = emf.createEntityManager();
        
        User user;
        Query query = em.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        
        user = (User) query.getSingleResult();
        return user;
    }  
}
