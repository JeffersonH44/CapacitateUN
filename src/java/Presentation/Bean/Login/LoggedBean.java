/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Login;

import BusinessLogic.UserManagement.PrivilegeVerifier;
import DataAccess.Entity.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jefferson
 */
@ManagedBean
@SessionScoped
public class LoggedBean implements Serializable {
    PrivilegeVerifier login = new PrivilegeVerifier();
    
    public int getLoggedUserRole() {
        User user = login.getUserLogged();
        
        if(user == null) {
            return -1;
        } else {
            return user.getRole();
        }
    }
    
    public String getLoggedUserNames() {
        User user = login.getUserLogged();
        
        if(user == null) {
            return "";
        } else {
            return user.getFirstname() + " " + user.getLastname();
        }
    }
    
    public int getLoggedUserID() {
        User user = login.getUserLogged();
        
        if(user == null) {
            return -1;
        } else {
            return user.getId();
        }
    }
    
    public String getMainPageByLoggedUser() {
        User user = login.getUserLogged();
        
        switch (user.getRole()) {
            case User.ADMIN:
                return "http://localhost:8080/CapacitateUN/faces/pages/admin/adminIndex.xhtml";
            case User.TRAINER:
                return "http://localhost:8080/CapacitateUN/faces/pages/trainer/trainerIndex.xhtml";
            case User.USER:
                return "http://localhost:8080/CapacitateUN/faces/pages/user/userIndex.xhtml";
        }
        
        return "";
    }
    
    public String getTemplateByLoggedUser() {
        User user = login.getUserLogged();
        
        switch (user.getRole()) {
            case User.ADMIN:
                return "admin/admin.xhtml";
            case User.TRAINER:
                return "trainer/trainer.xhtml";
            case User.USER:
                return "user/user.xhtml";
        }
        
        return "";
        
    }
}
