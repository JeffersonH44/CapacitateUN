/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.TopicManagement;

import DataAccess.Entity.User;
import DataAccess.Entity.Topic;
import DataAccess.Entity.ExperienceRegister;

/**
 * Clase encargada del manejo de experiencias.
 * @author Manu
 */
public class HandleExperience {
    
    /**
     * Crea una nueva experiencia en el registro de experiencias.
     * @param topicID - Tópico relacionado
     * @param userID - Capacitador
     * @return El nuevo registro de experiencias.
     */
    public ExperienceRegister CreateExperience(Topic topicID, User userID){
        
        ExperienceRegister experienceRegister = new ExperienceRegister();
        experienceRegister.setTopicID(topicID);
        experienceRegister.setUserID(userID);
        return experienceRegister;     
    }
}
