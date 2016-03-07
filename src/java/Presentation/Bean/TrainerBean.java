/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.CoursesRegister;
import DataAccess.DAO.CoursesDAO;
import DataAccess.Entity.Courses;
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
    private List<Courses> myCourses;
    
    @PostConstruct
    public void init() {
        CoursesRegister cr = new CoursesRegister();
        myCourses = cr.getCoursesByUser(coursesDAO);
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
    
    
}
