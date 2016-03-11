/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.CourseManagement;

import BusinessLogic.UserManagement.PrivilegeVerifier;
import DataAccess.DAO.CoursesDAO;
import DataAccess.DAO.TopicDAO;
import DataAccess.DAO.UserRegisterDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.Topic;
import DataAccess.Entity.User;
import DataAccess.Entity.UserRegister;
import java.util.List;

/**
 *
 * @author Jefferson
 */
public class CoursesRegister {
    
    public UserRegister addRegistry(Courses course, User user) {
        
        UserRegister registry = new UserRegister();
        registry.setCoursesID(course);
        registry.setUserID(user);
        registry.setStatus(UserRegister.ACTIVE);
        
        return registry;
    }
    
    public Courses createCourse(String name, Topic topicID, User trainerID) {
        Courses course = new Courses();
        course.setName(name);
        course.setTopicID(topicID);
        course.setTrainerID(trainerID);
        
        return course;
    }
    
    public List<Topic> getAvailableTopics(TopicDAO topicDAO) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        //TopicDAO topicDAO = new TopicDAO();
        
        return topicDAO.getTopicsByTrainer(user);
    }
    
    public List<Courses> getCoursesByTrainer(CoursesDAO courseDAO) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        
        return courseDAO.getByTrainer(user);
    }
    
    /*private List<Integer> getUserRegistry(UserRegisterDAO registryDAO) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        
        return registryDAO.getUserRegistry(user);
    }*/
    
    public List<Courses> getRegisteredCoursesByUser(CoursesDAO courseDAO ) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        
        return courseDAO.getRegisteredCoursesByUser(user);
    }
    
    public List<Courses> getUnregisteredCoursesByUser(CoursesDAO courseDAO) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        
        return courseDAO.getUnregisteredCoursesByUser(user);
    }
    
    public UserRegister createRegister(User user, Courses course) {
        UserRegister ur = new UserRegister();
        ur.setCoursesID(course);
        ur.setUserID(user);
        ur.setStatus(UserRegister.ACTIVE);
        
        return ur;
    }
}
