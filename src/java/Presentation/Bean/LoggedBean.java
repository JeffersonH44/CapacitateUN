/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.LoginService;
import DataAccess.Entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jefferson
 */
@ManagedBean
@ViewScoped
public class LoggedBean {
    LoginService login = new LoginService();
    
    public int getLoggedUserRole() {
        User user = login.getUserLogged();
        
        if(user == null) {
            return -1;
        } else {
            return user.getRole();
        }
    }
}
