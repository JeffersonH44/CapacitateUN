/*
<<<<<<< HEAD
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
=======
 * To change this template, choose Tools | Templates
>>>>>>> ExperienceManagement
 * and open the template in the editor.
 */
package DataAccess.DAO;


import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import java.util.List;
import javax.ejb.Stateless;
<<<<<<< HEAD
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
=======
import DataAccess.Entity.Topic;
import javax.persistence.EntityManager;
>>>>>>> d07bb8fd8cf3f253089a4ee57efed17aa3fe4434
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manu
 */
@Stateless
public class TopicDAO {
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
<<<<<<< HEAD
    
    public Topic searchByCourse(Courses course) {
=======
    //public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public boolean persist(Topic topic) {
        //em = emf.createEntityManager();
        em.persist(topic);
        return true;
    }
    
    public Topic searchByCourse(Courses course) {
        //EntityManager em = emf.createEntityManager();
        
>>>>>>> d07bb8fd8cf3f253089a4ee57efed17aa3fe4434
        Topic topic;
        Query q = em.createNamedQuery("Topic.findById");
        q.setParameter("id", course.getTopicID());
        
        topic = (Topic) q.getSingleResult();
                
        return topic;
    }
    
    public List<Topic> getTopicsByTrainer(User trainer) {
<<<<<<< HEAD
=======
        //EntityManager em = emf.createEntityManager();
        
>>>>>>> d07bb8fd8cf3f253089a4ee57efed17aa3fe4434
        TypedQuery<Topic> q = em.createNamedQuery("Topic.findByTrainer", Topic.class);
        q.setParameter("trainer_id", trainer);
        
        List<Topic> result = q.getResultList();
        return result;
    }
    
    public Topic getById(int id) {
        TypedQuery<Topic> q = em.createNamedQuery("Topic.findById", Topic.class);
        q.setParameter("id", id);
        
        Topic topic = q.getSingleResult();
        return topic;
    }
}
