/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

/**
 *
 * @author ArqSoft
 */
public class TopicDAO {
    
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public boolean persist(Topic topic) {
        
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        //try {
        em.persist(topic);
        em.getTransaction().commit();
        return true;
        //} catch(RollbackException e) {
        //    em.getTransaction().rollback();
        //    return false;
        //} finally {
            //em.close();
        //}
    }
    
    public Topic searchByName(String name) {
        
        EntityManager em = emf1.createEntityManager();
        Topic topic = null;
        Query query = em.createNamedQuery("Topic.findByName");
        query.setParameter("name", name);
        
        topic = (Topic) query.getSingleResult();
        return topic;
    }  
}
