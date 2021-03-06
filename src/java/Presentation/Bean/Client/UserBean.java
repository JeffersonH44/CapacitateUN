/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Client;

import BusinessLogic.CourseManagement.CoursesRegister;
import DataAccess.DAO.CourseDAO.CoursesDAO;
import DataAccess.DAO.UserRegistryDAO.UserRegisterDAO;
import DataAccess.Entity.Courses;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;

/**
 * Bean encargado de la vista principal de los cliente de la aplicación.
 * @author Jefferson
 */
@ManagedBean
@ViewScoped
public class UserBean implements Serializable {
    @EJB
    private CoursesDAO courseDAO;
    private List<Courses> myRegisteredCourses;
    private List<Courses> unregisteredCourses;
    private List<Courses> myRegisteredCoursesByDate;
    
    /**
     * Método para actualizar las listas de cursos de los usuarios para la
     * página inicial.
     */
    @PostConstruct
    public void update() {
        CoursesRegister cr = new CoursesRegister();
        myRegisteredCourses = cr.getRegisteredCoursesByUser(courseDAO);
        setUnregisteredCourses(cr.getUnregisteredCoursesByUser(courseDAO));
        setMyRegisteredCoursesByDate(cr.getRegisteredCoursesByDate(courseDAO));
    }

    /**
     * @return the myRegisteredCourses
     */
    public List<Courses> getMyRegisteredCourses() {
        update();
        return myRegisteredCourses;
    }

    /**
     * @param myRegisteredCourses the myRegisteredCourses to set
     */
    public void setMyRegisteredCourses(List<Courses> myRegisteredCourses) {
        this.myRegisteredCourses = myRegisteredCourses;
    }

    /**
     * @return the unregisteredCourses
     */
    public List<Courses> getUnregisteredCourses() {
        return unregisteredCourses;
    }

    /**
     * @param unregisteredCourses the unregisteredCourses to set
     */
    public void setUnregisteredCourses(List<Courses> unregisteredCourses) {
        this.unregisteredCourses = unregisteredCourses;
    }

    /**
     * @return the myRegisteredCoursesByDate
     */
    public List<Courses> getMyRegisteredCoursesByDate() {
        return myRegisteredCoursesByDate;
    }

    /**
     * @param myRegisteredCoursesByDate the myRegisteredCoursesByDate to set
     */
    public void setMyRegisteredCoursesByDate(List<Courses> myRegisteredCoursesByDate) {
        this.myRegisteredCoursesByDate = myRegisteredCoursesByDate;
    }
}
