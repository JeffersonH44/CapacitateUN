/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.CourseManagement;

import BusinessLogic.UserManagement.PrivilegeVerifier;
import DataAccess.DAO.CourseDAO.CoursesDAO;
import DataAccess.DAO.TopicDAO.TopicDAO;
import DataAccess.DAO.UserRegistryDAO.UserRegisterDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.Topic;
import DataAccess.Entity.User;
import DataAccess.Entity.UserRegister;
import java.util.Date;
import java.util.List;

/**
 * Clase encargada de lo que esté relacionado con las capacitaciones
 * @author Jefferson
 */
public class CoursesRegister {
    
    /**
     * Crea una nueva inscripción a un curso por parte de un usuario.
     * @param course - curso a inscribirse.
     * @param user - usuario inscrito.
     * @return el registro del curso.
     */
    public UserRegister addRegistry(Courses course, User user) {
        
        UserRegister registry = new UserRegister();
        registry.setCoursesID(course);
        registry.setUserID(user);
        registry.setStatus(UserRegister.ACTIVE);
        
        return registry;
    }
    
    /**
     * Crea un nuevo curso.
     * @param name - Nombre del curso.
     * @param topicID - Tópico del curso. 
     * @param trainerID - Capacitador del curso.
     * @param date - Fecha de la capacitación.
     * @return El nuevo curso.
     */
    public Courses createCourse(String name, Topic topicID, User trainerID, Date date) {
        Courses course = new Courses();
        course.setName(name);
        course.setTopicID(topicID);
        course.setTrainerID(trainerID);
        course.setDate(date);
        
        return course;
    }
    
    /**
     * Obtiene los tópicos que maneja el capacitador que está en la sesión.
     * @param topicDAO - Para realizar la consulta a la base de datos.
     * @return La lista de tópicos del capacitador.
     */
    public List<Topic> getAvailableTopics(TopicDAO topicDAO) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        //TopicDAO topicDAO = new TopicDAO();
        
        return topicDAO.getTopicsByTrainer(user);
    }
    
    /**
     * Obtiene los cursos dado el capacitador que está en la sesión
     * @param courseDAO - Para realizar la consulta a la base de datos.
     * @return La lista de cursos que dicta el capacitador.
     */
    public List<Courses> getCoursesByTrainer(CoursesDAO courseDAO) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        
        return courseDAO.getByTrainer(user);
    }
    
    /**
     * Obtiene los cursos que esta registrado el usuario de la sesión.
     * @param courseDAO - Para realizar la consulta a la base de datos.
     * @return La lista de cursos del usuario.
     */
    public List<Courses> getRegisteredCoursesByUser(CoursesDAO courseDAO ) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        
        return courseDAO.getRegisteredCoursesByUser(user);
    }
    
    /**
     * Obtiene los cursos que no esta registrado el usuario de la sesión.
     * @param courseDAO - Para realizar la consulta a la base de datos.
     * @return La lista de cursos en la que no está registrado el usuario.
     */
    public List<Courses> getUnregisteredCoursesByUser(CoursesDAO courseDAO) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        
        return courseDAO.getUnregisteredCoursesByUser(user);
    }
    
    /**
     * Cambia un registro a "retirado"
     * @param user - Usuario a retirar.
     * @param course - Curso del cual desea retirarse.
     * @return el nuevo registro con el estado "retirado".
     */
    public UserRegister removeRegister(User user, Courses course) {
        UserRegister ur = new UserRegister();
        ur.setCoursesID(course);
        ur.setUserID(user);
        ur.setStatus(UserRegister.RETIRED);
        
        return ur;
    }
    
    /**
     * Obtiene todos los cursos registrados por el usuario de la sesion a partir de la fecha de hoy.
     * @param courseDAO - Para realizar la consulta.
     * @return lista de cursos inscritos cuya realización es después de la fecha.
     */
    public List<Courses> getRegisteredCoursesByDate(CoursesDAO courseDAO) {
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
        
        return courseDAO.getRegisteredCoursesByDate(user, new Date());
    }
}
