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
        em.getTransaction().begin();
        
        //try {
            em.persist(user);
            em.getTransaction().commit();
            return true;
        //} catch(RollbackException e) {
        //    em.getTransaction().rollback();
        //    return false;
        //} finally {
            //em.close();
        //}
    }
    
    public User searchByUserId(int Id) {
        
        EntityManager em = emf1.createEntityManager();
        User user = null;
        
        try {
            user = em.find(User.class
                    , Id);
        } catch (Exception e){
        } finally {
            em.close();
            return user;
        }
    }
    
    
    /*public void edit(User user){
        User userNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        try {
           userNew = em.merge(em.find(User.class, user.getId())); 
            userNew.setBalance(user.getBalance());
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean editBalance(Long userNumber, int consigmentValue) {
        User userNew;
        EntityManager em = emf1.createEntityManager();  
        em.getTransaction().begin();
        boolean success = true;
        
        try {
            userNew = em.merge(em.find(User.class, userNumber)); 
            userNew.setBalance(userNew.getBalance()+ consigmentValue);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            success = false;
        } finally {
            em.close();
            return success;
        }
    }*/    
}
