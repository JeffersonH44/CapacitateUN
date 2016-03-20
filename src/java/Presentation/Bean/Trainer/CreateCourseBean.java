/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Trainer;

import BusinessLogic.CourseManagement.CoursesRegister;
import BusinessLogic.UserManagement.UserRegister;
import BusinessLogic.UserManagement.PrivilegeVerifier;
import DataAccess.DAO.CoursesDAO;
import DataAccess.DAO.TopicDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.Topic;
import DataAccess.Entity.User;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jefferson
 */
@ManagedBean
@RequestScoped
public class CreateCourseBean implements Serializable {
    private String name;
    private Topic topic;
    private User user;
    private Date date;
    private List<Topic> availableTopics;
    
    private String message;
    @EJB
    private CoursesDAO courseDAO;
    @EJB
    private TopicDAO topicDAO;
    
    @PostConstruct
    public void init() {
        CoursesRegister cr = new CoursesRegister();
        availableTopics = cr.getAvailableTopics(topicDAO);
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
    
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String createCourse() {
        PrivilegeVerifier login = new PrivilegeVerifier();
        user = login.getUserLogged();
        
        CoursesRegister cr = new CoursesRegister();
        Courses course = cr.createCourse(name, topic, user, date);
        
        boolean saved = courseDAO.persist(course);
        if(saved) {
            message = "El curso " + name + " ha sido creado exitosamente!";
        } else {
            message = "El curso no se ha podido crear!";
        }
        
        return "trainerIndex.xhtml";
    }


}
