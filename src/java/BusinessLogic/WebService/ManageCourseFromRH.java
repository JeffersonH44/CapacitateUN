/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.WebService;

import BusinessLogic.UserManagement.LoginLDAP;
import DataAccess.DAO.CourseDAO.CoursesDAO;
import DataAccess.DAO.ExperienceDAO.ExperienceDAO;
import DataAccess.DAO.TopicDAO.TopicDAO;
import DataAccess.DAO.UserDAO.UserDAO;
import DataAccess.DAO.UserRegistryDAO.UserRegisterDAO;
import DataAccess.Entity.ExperienceRegister;
import DataAccess.Entity.Topic;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author arqsoft2016
 */
@WebService(serviceName = "ManageCourseFromRH")
public class ManageCourseFromRH {
    
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
    LoginLDAP conecction = new LoginLDAP();
    
    @WebMethod(operationName = "createCourse")
    public ROB createCourse(@WebParam(name = "course") Course courseToCreate) {
        // check company (user)
        try {
            String companyName = courseToCreate.getStringId();
            DataAccess.Entity.User trainer = userDAO.searchByUsername(companyName);
            if(trainer == null) {
                trainer = new DataAccess.Entity.User();
                trainer.setRole(DataAccess.Entity.User.TRAINER);
                trainer.setUsername(courseToCreate.getStringId());
                trainer.setId(courseToCreate.getId());
                trainer.setPassword(companyName);
                
                conecction.registrar(Integer.toString(trainer.getId()), trainer.getFirstname(), trainer.getLastname(), trainer.getPassword(), trainer.getUsername(), "703");
                
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

                    conecction.registrar(Integer.toString(currentStudent.getId()), currentStudent.getFirstname(), currentStudent.getLastname(), currentStudent.getPassword(), currentStudent.getUsername(), "702");
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

            EventInfo info = new EventInfo();
            info.setDate(date);
            info.setName(companyName);
            info.setParticipants(students.size());
            info.setTrainer(companyName);
            
            return new ROB(true, "no error", info);
        } catch(Exception e) {
            return new ROB(false, "Error en sistema de capacitaciones " + e.getMessage(), null);
        }
    }

}