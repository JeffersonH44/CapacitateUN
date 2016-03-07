/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ExperienceDAO;
import DataAccess.Entity.User;
import DataAccess.Entity.Topic;
import DataAccess.Entity.ExperienceRegister;

/**
 *
 * @author ArqSoft
 */
public class HandleExperience {
    
    public String CreateExperience(Topic topicID, User userID){
        
        ExperienceRegister experienceRegister = new ExperienceRegister();
        experienceRegister.setTopicID(topicID);
        experienceRegister.setUserID(userID);
        
        
        ExperienceDAO experienceDAO = new ExperienceDAO();
        boolean saved = experienceDAO.persist(experienceRegister);
        if (saved) {
            return "la expereriencia con ID: " + experienceRegister.getRegisterID()+ " ha sido guardada con exito .";
        } else {
            return "fallo en la creación de experiencia";
        }        
    }
    
    
}
