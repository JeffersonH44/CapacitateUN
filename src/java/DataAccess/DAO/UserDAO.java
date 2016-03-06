/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.User;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

/**
 *
 * @author ArqSoft
 */
public class UserDAO {
    
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public boolean persist(User user) {
        
        EntityManager em = emf1.createEntityManager();
        //em.getTransaction().begin();
        
        //try {
        em.persist(user);
        //em.getTransaction().commit();
        return true;
        //} catch(RollbackException e) {
        //    em.getTransaction().rollback();
        //    return false;
        //} finally {
            //em.close();
        //}
    }
    
    public User searchByUsername(String username) {
        
        EntityManager em = emf1.createEntityManager();
        User user = null;
        Query query = em.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        
        user = (User) query.getSingleResult();
        return user;
    }  
    public User searchByUserId(Integer idUser) {
        
        EntityManager em = emf1.createEntityManager();
        User user = null;
        Query query = em.createNamedQuery("User.findById");
        query.setParameter("id", idUser);
        
        user = (User) query.getSingleResult();
        return user;
    }
    
}

