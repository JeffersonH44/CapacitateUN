/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.ExperienceRegister;
import DataAccess.Entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

/**
 *
 * @author Manu
 */
public class ExperienceDAO {
    
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public boolean persist(ExperienceRegister experience) {
        
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        //try {
        em.persist(experience);
        em.getTransaction().commit();
        return true;
        //} catch(RollbackException e) {
        //    em.getTransaction().rollback();
        //    return false;
        //} finally {
            //em.close();
        //}
    }
    
    public ExperienceRegister searchByTrainerID( User Trainer) {
        
        EntityManager em = emf1.createEntityManager();
        ExperienceRegister experienceRegister = null;
        Query query = em.createNamedQuery("ExperienceRegister.findByTrainerID");
        query.setParameter("TrainerID", Trainer.getId());
        
        experienceRegister = (ExperienceRegister) query.getSingleResult();
        return experienceRegister;
    }  
}
