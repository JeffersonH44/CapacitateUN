/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.UserRegister;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
