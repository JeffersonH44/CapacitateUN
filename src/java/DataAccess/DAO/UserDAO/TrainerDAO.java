/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO.UserDAO;

import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Clase encargada del acceso a datos de los capacitadores.
 * @author Jefferson
 */
@Stateless
public class TrainerDAO {
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    
    /**
     * Obtiene el capacitador de un curso
     * @param course
     * @return el capacitador del curso o nulo en caso de no encontrarlo.
     */
    public User searchByCourse(Courses course) {
        User trainer;
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", course.getTrainerID());
        
        trainer = (User) q.getSingleResult();
        
        return trainer;
    }
}
