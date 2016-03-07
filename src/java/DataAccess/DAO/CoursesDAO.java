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
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jefferson
 */
@Stateless
public class CoursesDAO {
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    
    public boolean persist(Courses course) {
        em.persist(course);
        return true;
    }
    
    public Courses getById(int id) {
        TypedQuery<Courses> q = em.createNamedQuery("Courses.findById", Courses.class);
        q.setParameter("id", id);
        
        Courses course = q.getSingleResult();
        return course;
    }
    
    public List<Courses> getByUser(User user) {
        TypedQuery<Courses> q = em.createNamedQuery("Courses.findByUser", Courses.class);
        q.setParameter("trainer_id", user);
        
        List<Courses> myCourses = q.getResultList();
        return myCourses;
    }
}
