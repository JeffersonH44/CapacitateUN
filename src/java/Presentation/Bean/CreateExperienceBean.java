/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleExperience;
import BusinessLogic.Controller.HandleTopic;
import BusinessLogic.Controller.LoginService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jefferson
 */
@ManagedBean
@ViewScoped
public class CreateExperienceBean {
    private String name;
    private String area;
    private String message;

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
    
    public String createExperience() {
        HandleTopic topicHandler = new HandleTopic();
        HandleExperience he = new HandleExperience();
        LoginService user = new LoginService();
        message = he.CreateExperience(topicHandler.createTopic(name, area), user.getUserLogged());
        return "index.xhtml";
    }
}
