/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.TopicManagement;

import DataAccess.Entity.User;
import DataAccess.Entity.Topic;
import DataAccess.Entity.ExperienceRegister;

/**
 *
 * @author Manu
 */
public class HandleExperience {
    
    public ExperienceRegister CreateExperience(Topic topicID, User userID){
        
        ExperienceRegister experienceRegister = new ExperienceRegister();
        experienceRegister.setTopicID(topicID);
        experienceRegister.setUserID(userID);
        return experienceRegister;
        /*if (saved) {
            return "la expereriencia con ID: " + experienceRegister.getRegisterID()+ " ha sido guardada con exito .";
        } else {
            return "fallo en la creación de experiencia";
        } */       
    }
}
