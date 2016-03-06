/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
import BusinessLogic.Controller.HandleUserRegister;
import DataAccess.Entity.UserRegister;
import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jefferson
 */

@ViewScoped
@ManagedBean
public class UserRegisternBean {
    private String username;
    private String idCourse;
   
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

        /**
     * @return the idCourse
     */
    public String getIdCourse() {
        return idCourse;
    }

    /**
     * @param idCourse the username to set
     */
    public void setIdCourse(String course) {
        this.idCourse = idCourse;
    }
    
     
    public void login() {
        HandleUserRegister userregister = new HandleUserRegister();
        Object[] us = userregister.searchAll();
        for (int i = 0; i < us.length; i++) {
            UserRegister ur = (UserRegister)us[i];
            setUsername(ur.getStatus());
        }
       searchUser();
    }
    
    
    public void searchUser(){
        Courses c = new Courses(1, "asdas");
        HandleUserRegister userregister = new HandleUserRegister();
        Object[] userRegister = userregister.searchIdCourses(c);
        HandleUser huser = new HandleUser();
        
        User user;
        for (int i = 0; i < userRegister.length; i++) {
            UserRegister ur = (UserRegister)userRegister[i];
            
            user = huser.searchUser(ur.getUserID().getId());
            setUsername(user.getUsername());
        }
    }
    
}
