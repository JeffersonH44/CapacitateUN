/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.WebService;

import DataAccess.DAO.CourseDAO.CoursesDAO;
import DataAccess.DAO.ExperienceDAO.ExperienceDAO;
import DataAccess.DAO.TopicDAO.TopicDAO;
import DataAccess.DAO.UserDAO.UserDAO;
import DataAccess.DAO.UserRegistryDAO.UserRegisterDAO;
import DataAccess.Entity.ExperienceRegister;
import DataAccess.Entity.Topic;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author arqsoft2016
 */

@ManagedBean
@ViewScoped
public class ManageCourseFromHR {
    
    @EJB
    private UserDAO userDAO;
    @EJB
    private CoursesDAO courseDAO;
    @EJB
    private TopicDAO topicDAO;
    @EJB
    private ExperienceDAO experienceDAO;
    @EJB
    private UserRegisterDAO registryDAO;
    
    public boolean createCourse(Course courseToCreate/*, UserDAO userDAO, CoursesDAO courseDAO, 
                                TopicDAO topicDAO, ExperienceDAO exRegistryDAO, 
                                UserRegisterDAO registryDAO*/) {
        // check company (user)
        String companyName = courseToCreate.getStringId();
        DataAccess.Entity.User trainer = userDAO.searchByUsername(companyName);
        if(trainer == null) {
            trainer = new DataAccess.Entity.User();
            trainer.setRole(DataAccess.Entity.User.TRAINER);
            trainer.setUsername(courseToCreate.getStringId());
            trainer.setId(courseToCreate.getId());
            trainer.setPassword(companyName);
            
            trainer = userDAO.persist(trainer);
        }
        
        // new topic
        DataAccess.Entity.Topic topic = new Topic();
        topic.setArea(courseToCreate.getDescription());
        topic.setName(courseToCreate.getDescription());
        topic = topicDAO.persist(topic);
        
        // register new experience to the trainer
        DataAccess.Entity.ExperienceRegister exRegister = new ExperienceRegister();
        exRegister.setTopicID(topic);
        exRegister.setUserID(trainer);
        experienceDAO.persist(exRegister);
        
        // create or add existing user
        List<DataAccess.Entity.User> students = new ArrayList<>();
        for(User user : courseToCreate.getUsers()) {
            DataAccess.Entity.User currentStudent = userDAO.searchByUsername(user.getStringDocument());
            
            if(currentStudent == null) {
                currentStudent = new DataAccess.Entity.User();
                currentStudent.setId(user.getDocument());
                currentStudent.setUsername(user.getStringDocument());
                currentStudent.setFirstname(user.getFirstname());
                currentStudent.setLastname(user.getLastname());
                currentStudent.setRole(DataAccess.Entity.User.USER);
                currentStudent.setPassword(user.getStringDocument());
                
                currentStudent = userDAO.persist(currentStudent);
            }
            
            students.add(currentStudent);
        }
        
        // create void course
        DataAccess.Entity.Courses course = new DataAccess.Entity.Courses();
        
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 15);
        date = cal.getTime();
        
        course.setTrainerID(trainer);
        course.setName(courseToCreate.getDescription());
        course.setTopicID(topic);
        course.setDate(date);
        
        course = courseDAO.persist(course);
        
        for(DataAccess.Entity.User student : students) {
            DataAccess.Entity.UserRegister currentRegister = 
                    new DataAccess.Entity.UserRegister();
            currentRegister.setCoursesID(course);
            currentRegister.setUserID(student);
            currentRegister.setStatus(DataAccess.Entity.UserRegister.ACTIVE);
            
            registryDAO.persist(currentRegister);
        }
        
        return true;
    }
}
