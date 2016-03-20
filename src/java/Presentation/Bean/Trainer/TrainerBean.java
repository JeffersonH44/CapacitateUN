/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Trainer;

import BusinessLogic.CourseManagement.CoursesRegister;
import DataAccess.DAO.CoursesDAO;
import DataAccess.DAO.TopicDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.Topic;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jefferson
 */
@Named
@ConversationScoped
public class TrainerBean implements Serializable {
    
    @EJB
    private CoursesDAO coursesDAO;
    @EJB
    private TopicDAO topicDAO;
    @Inject
    private Conversation conversation;
    
    private List<Courses> myCourses;
    private List<Topic> myExperience;
    private Courses currentCourse;
    private Topic currentTopic;
    
    @PostConstruct
    public void update() {
        CoursesRegister cr = new CoursesRegister();
        myCourses = cr.getCoursesByTrainer(coursesDAO);
        setMyExperience(cr.getAvailableTopics(topicDAO));
    }
    
    public void initConversation() {
        conversation.begin();
    }
    
    private String endConversation() {
        conversation.end();
        return "trainerIndex.xhtml";
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
    
    /**
     * @return the currentCourse
     */
    public Courses getCurrentCourse() {
        return currentCourse;
    }

    /**
     * @param currentCourse the currentCourse to set
     */
    public void setCurrentCourse(Courses currentCourse) {
        this.currentCourse = currentCourse;
    }

    /**
     * @return the currentTopic
     */
    public Topic getCurrentTopic() {
        return currentTopic;
    }

    /**
     * @param currentTopic the currentTopic to set
     */
    public void setCurrentTopic(Topic currentTopic) {
        this.currentTopic = currentTopic;
    }
    
    public String editCourse(Courses selectedCourse) {
        this.setCurrentCourse(selectedCourse);
        this.initConversation();
        return "editCourse.xhtml?faces-redirect=true";
    }
    
    public String editTopic(Topic selectedTopic) {
        this.setCurrentTopic(selectedTopic);
        return "editTopic.xhtml?faces-redirect=true";
    }
    
    public String saveCourseChanges() {
        this.endConversation();
        
        coursesDAO.update(currentCourse);
        
        //this.update();
        return "trainerIndex.xhtml";
    }
    
    public String removeCourse(Courses course) {
        coursesDAO.remove(course);
        return "trainerIndex.xhtml?faces-redirect=true";
    }
    
}
