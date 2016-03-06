/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.TopicDAO;
import DataAccess.Entity.Topic;

/**
 *
 * @author ArqSoft
 */
public class HandleTopic {
    
    public Topic createTopic(String name, String area){
        
        Topic topic = new Topic();
        topic.setName(name);
        topic.setArea(area);
        
        
        TopicDAO topicDAO = new TopicDAO();
        boolean saved = topicDAO.persist(topic);
        if (saved) {
            return topic;
        } else {
            return null;
        }        
    }
    
    
}
