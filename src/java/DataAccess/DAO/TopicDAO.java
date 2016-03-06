/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Courses;
import DataAccess.Entity.Topic;
import DataAccess.Entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jefferson
 */
public class TopicDAO {
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public Topic searchByCourse(Courses course) {
        EntityManager em = emf.createEntityManager();
        
        Topic topic;
        Query q = em.createNamedQuery("Topic.findById");
        q.setParameter("id", course.getTopicID());
        
        topic = (Topic) q.getSingleResult();
                
        return topic;
    }
    
    public List<Topic> getTopicsByTrainer(User trainer) {
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Topic> q = em.createNamedQuery("Topic.findByTrainer", Topic.class);
        q.setParameter("trainer_id", trainer);
        
        List<Topic> result = q.getResultList();
        return result;
    }
}
