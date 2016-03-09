/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.CoursesRegister;
import DataAccess.DAO.CoursesDAO;
import DataAccess.DAO.TopicDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.Topic;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jefferson
 */
@ManagedBean
@ViewScoped
public class TrainerBean {
    
    @EJB
    private CoursesDAO coursesDAO;
    @EJB
    private TopicDAO topicDAO;
    private List<Courses> myCourses;
    private List<Topic> myExperience;
    
    @PostConstruct
    public void init() {
        CoursesRegister cr = new CoursesRegister();
        myCourses = cr.getCoursesByUser(coursesDAO);
        setMyExperience(cr.getAvailableTopics(topicDAO));
    }

    /**
     * @return the myCourses
     */
    public List<Courses> getMyCourses() {
        return myCourses;
    }

    /**
     * @param myCourses the myCourses to set
     */
    public void setMyCourses(List<Courses> myCourses) {
        this.myCourses = myCourses;
    }

    /**
     * @return the myExperience
     */
    public List<Topic> getMyExperience() {
        return myExperience;
    }

    /**
     * @param myExperience the myExperience to set
     */
    public void setMyExperience(List<Topic> myExperience) {
        this.myExperience = myExperience;
    }
    
    
}
