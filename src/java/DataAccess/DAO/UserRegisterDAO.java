package DataAccess.DAO;

import DataAccess.Entity.Courses;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import DataAccess.Entity.UserRegister;
import java.util.ArrayList;
import java.util.Vector;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class UserRegisterDAO {
      
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public boolean persist(UserRegister userRegister) {
        
        EntityManager em = emf1.createEntityManager();
        //em.getTransaction().begin();
        
        //try {
        em.persist(userRegister);
        //em.getTransaction().commit();
        return true;
        //} catch(RollbackException e) {
        //    em.getTransaction().rollback();
        //    return false;
        //} finally {
            //em.close();
        //}
    }
    
    public Object[] searchAll() {
        
        EntityManager em = emf1.createEntityManager();
        UserRegister user = null;
        Query query = em.createNamedQuery("UserRegister.findAll");
        
        
        Object[] resultList =   query.getResultList().toArray();
       
        for (int i = 0; i < resultList.length; i++) {
            System.out.println(resultList[i]);
        }
        
        return resultList;
    } 
    
    
    public Object[] searchIdCourses(Courses idCourses) {
        
        EntityManager em = emf1.createEntityManager();
        UserRegister user = null;
        Query query = em.createNamedQuery("UserRegister.findByCourseID");
        query.setParameter("coursesID", idCourses);
        
        
        Object[] resultList =   query.getResultList().toArray();
       
        for (int i = 0; i < resultList.length; i++) {
            System.out.println(resultList[i]);
        }
        
        return resultList;
    }
    
   

    
}
