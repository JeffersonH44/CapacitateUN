/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.CoursesRegister;
import BusinessLogic.Controller.HandleUser;
import BusinessLogic.Controller.LoginService;
import DataAccess.Entity.Topic;
import DataAccess.Entity.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jefferson
 */
@ManagedBean
@ViewScoped
public class CreateCourseBean implements Serializable {
    private String name;
    private Topic topic;
    private User user;
    private String message;
    private List<Topic> availableTopics;
    
    @PostConstruct
    public void init() {
        CoursesRegister cr = new CoursesRegister();
        availableTopics = cr.getAvailableTopics();
    }

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
     * @return the topic
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
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
     * @return the available topics
     */    
    public List getAvailableTopics() {
        return availableTopics;
    }

    /**
     * @param availableTopics the availableTopics to set
     */
    public void setAvailableTopics(List availableTopics) {
        this.availableTopics = availableTopics;
    }
    
    public String createCourse() {
        LoginService login = new LoginService();
        user = login.getUserLogged();
        topic = availableTopics.get(0);
        
        CoursesRegister cr = new CoursesRegister();
        message = cr.createCourse(name, topic, user);
        
        return "trainerIndex.xhtml";
    }
}
