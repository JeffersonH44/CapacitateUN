/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jefferson
 */
public class TrainerDAO {
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public User searchByCourse(Courses course) {
        EntityManager em = emf.createEntityManager();
        
        User trainer;
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", course.getTrainerID());
        
        trainer = (User) q.getSingleResult();
        
        return trainer;
    }
}
