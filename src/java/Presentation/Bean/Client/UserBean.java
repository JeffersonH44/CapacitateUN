/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Client;

import BusinessLogic.CourseManagement.CoursesRegister;
import DataAccess.DAO.CoursesDAO;
import DataAccess.DAO.UserRegisterDAO;
import DataAccess.Entity.Courses;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jefferson
 */
@ManagedBean
@SessionScoped
public class UserBean {
    @EJB
    private CoursesDAO courseDAO;
    private List<Courses> myRegisteredCourses;
    private List<Courses> unregisteredCourses;
    
    @PostConstruct
    public void update() {
        CoursesRegister cr = new CoursesRegister();
        myRegisteredCourses = cr.getRegisteredCoursesByUser(courseDAO);
        setUnregisteredCourses(cr.getUnregisteredCoursesByUser(courseDAO));
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
}
