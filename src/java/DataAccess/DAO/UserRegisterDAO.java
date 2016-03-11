/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import DataAccess.Entity.UserRegister;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jefferson
 */
@Stateless
public class UserRegisterDAO {
    
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    
    public boolean persist(UserRegister registry) {
        em.persist(registry);
        return true;
    }
    
    public List<Integer> getUserRegistry(User user) {
        TypedQuery<Integer> q = em.createNamedQuery("UserRegister.findByUser", Integer.class);
        q.setParameter("user", user);
        
        List<Integer> myRegisters = q.getResultList();
        return myRegisters;
    }
    
}
