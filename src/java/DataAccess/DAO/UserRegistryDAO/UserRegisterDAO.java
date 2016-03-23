/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO.UserRegistryDAO;

import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import DataAccess.Entity.UserRegister;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase encargada del acceso a datos del registro de usuarios.
 * @author Jefferson
 */
@Stateless
public class UserRegisterDAO {
    
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    
    /**
     * Guarda un nuevo registro a un curso.
     * @param registry - registro del curso
     * @return verdadero en caso de que se haya guardado exitosamente
     */
    public boolean persist(UserRegister registry) {
        em.persist(registry);
        return true;
    }
    
    /**
     * Actualiza el estado de un registro.
     * @param registry - registro a cambiar estado
     * @return verdadero en caso de que el registro se actualice correctamente
     */
    public boolean update(UserRegister registry) {
        
        TypedQuery<UserRegister> q = em.createNamedQuery("UserRegister.findRegister", UserRegister.class);
        q.setParameter("course_id", registry.getCoursesID());
        q.setParameter("user_id", registry.getUserID());
        
        UserRegister ur = q.getSingleResult();
        ur.setStatus(registry.getStatus());
        
        return true;
    }
}
