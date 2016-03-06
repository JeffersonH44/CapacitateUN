/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

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
    
    public String addRegistry(Courses course, User user) {
        
        UserRegister registry = new UserRegister();
        registry.setCoursesID(course);
        registry.setUserID(user);
        registry.setStatus(UserRegister.ACTIVE);
        
        UserRegisterDAO registryDAO = new UserRegisterDAO();
        boolean saved = registryDAO.persist(registry);
        
        if(saved) {
            return "El registro sobre el curso " + course.getName() + " ha sido exitoso!";
        } else { 
            return "No se ha podido completar el registro";
        }
    }
    
    public String createCourse(String name, Topic topicID, User trainerID) {
        Courses course = new Courses();
        course.setName(name);
        course.setTopicID(topicID);
        course.setTrainerID(trainerID);
        
        CoursesDAO courseDAO = new CoursesDAO();
        boolean saved = courseDAO.persist(course);
        if(saved) {
            return "El curso " + name + " ha sido creado exitosamente!";
        } else {
            return "El curso no se ha podido crear!";
        }
    }
    
    public List<Topic> getAvailableTopics() {
        LoginService login = new LoginService();
        User user = login.getUserLogged();
        TopicDAO topicDAO = new TopicDAO();
        
        return topicDAO.getTopicsByTrainer(user);
    }
}
