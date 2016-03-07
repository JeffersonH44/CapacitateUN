/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jefferson
 */
@Stateless
public class TrainerDAO {
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    
    public User searchByCourse(Courses course) {
        User trainer;
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", course.getTrainerID());
        
        trainer = (User) q.getSingleResult();
        
        return trainer;
    }
}
