/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Courses;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Fachada de cursos para CRUD de administración. (clase autogenerada)
 * @author Jefferson
 */
@Stateless
public class CoursesFacade extends AbstractFacade<Courses> {

    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoursesFacade() {
        super(Courses.class);
    }
    
}
