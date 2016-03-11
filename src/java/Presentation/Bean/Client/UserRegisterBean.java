/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Client;

import BusinessLogic.CourseManagement.CoursesRegister;
import BusinessLogic.UserManagement.PrivilegeVerifier;
import DataAccess.DAO.UserRegisterDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import DataAccess.Entity.UserRegister;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jefferson
 */

@ManagedBean
@ViewScoped
public class UserRegisterBean {
    @EJB
    private UserRegisterDAO registerDAO;
    private Courses course;
    private User user;
    private String message;

    /**
     * @return the course
     */
    public Courses getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Courses course) {
        this.course = course;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
    
    public String createRegister() {
        CoursesRegister cr = new CoursesRegister();
        PrivilegeVerifier login = new PrivilegeVerifier();
        User user = login.getUserLogged();
                
        UserRegister ur = cr.createRegister(user, course);
        
        boolean saved = registerDAO.persist(ur);
        if(saved) {
            message = "Se ha registrado en el curso exitosamente";
        } else {
            message = "No se ha podido registrar en el curso";
        }
        
        return "userIndex.xhtml";
    }

}