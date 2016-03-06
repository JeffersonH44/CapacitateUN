/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.UserRegister;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jefferson
 */
public class UserRegisterDAO {
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public boolean persist(UserRegister registry) {
        EntityManager em = emf.createEntityManager();
        em.persist(registry);
        return true;
    }
}
