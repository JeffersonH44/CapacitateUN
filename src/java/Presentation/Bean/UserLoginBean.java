/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
import DataAccess.Entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jefferson
 */

@SessionScoped
@ManagedBean
public class UserLoginBean {
    private String username;
    private String password;
    private String message;
    private User user;

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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
    
    public String login() {
        HandleUser userManager = new HandleUser();
        user = userManager.loginUser(username, password);
        message = user == null ? "No se pudo iniciar sesión" : "Se ha iniciado sesión correctamente";
        switch (user.getRole()) {
            case User.ADMIN:
                return "faces/pages/admin/adminIndex.xhtml";
            case User.USER:
                return "login.xhtml";
            default:
                return "login.xhtml";
        }
        
    }
}
