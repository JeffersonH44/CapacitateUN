/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Trainer;

import BusinessLogic.TopicManagement.HandleExperience;
import BusinessLogic.TopicManagement.HandleTopic;
import BusinessLogic.UserManagement.PrivilegeVerifier;
import DataAccess.DAO.ExperienceDAO.ExperienceDAO;
import DataAccess.DAO.TopicDAO.TopicDAO;
import DataAccess.Entity.ExperienceRegister;
import DataAccess.Entity.Topic;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bean encargado de la creación de experiencias del capacitador. (createExperience.xhtml)
 * @author Manu
 */
@ManagedBean
@ViewScoped
public class CreateExperienceBean implements Serializable {
    private String name;
    private String area;
    private String message;
    @EJB
    private ExperienceDAO experienceDAO;
    @EJB
    private TopicDAO topicDao;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the lastname to set
     */
    public void setArea(String area) {
        this.area = area;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Crea una nueva experiencia de acuerdo a los parámetros del bean.
     * @return Página principal de los capacitadores.
     */
    public String createExperience() {
        HandleTopic topicHandler = new HandleTopic();
        HandleExperience he = new HandleExperience();
        PrivilegeVerifier user = new PrivilegeVerifier();
        
        Topic topic = topicHandler.createTopic(name, area);
        topicDao.persist(topic);
        ExperienceRegister exp = he.CreateExperience(topic, user.getUserLogged());
        exp = experienceDAO.persist(exp);
        
        if (exp != null) {
            message = "la expereriencia con ID: " + exp.getRegisterID()+ " ha sido guardada con exito .";
        } else {
            message = "fallo en la creación de experiencia";
        }
        return "trainerIndex.xhtml";
    }
}
