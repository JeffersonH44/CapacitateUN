/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.TopicManagement;

import DataAccess.Entity.Topic;

/**
 *
 * @author Manu
 */
public class HandleTopic {
    
    public Topic createTopic(String name, String area){
        
        Topic topic = new Topic();
        topic.setName(name);
        topic.setArea(area);
        if(name.length()<50 && area.length()<50){
            return topic;
        }
        return null;
    }
}
