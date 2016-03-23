/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Login;

import BusinessLogic.UserManagement.UserRegister;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Jefferson
 */

@SessionScoped
@ManagedBean(name = "userLoginBean",eager = true)
public class UserLoginBean implements Serializable {    
    private String username;
    private String password;
    private String message;
    private User user;
    @EJB
    private UserDAO userDAO;

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
        UserRegister userManager = new UserRegister();
        try {
            user = userManager.loginUser(username, password, userDAO);
        } catch (Exception e) {
            message =  "Error en Usuario o Contraseña";
            return "/index.xhtml";
        }
        
        
        switch (user.getRole()) {
            case User.ADMIN:
                return "faces/pages/admin/adminIndex.xhtml";
            case User.TRAINER:
                return "faces/pages/trainer/trainerIndex.xhtml";
            case User.USER:
                return "faces/pages/user/userIndex.xhtml";
        }
        return "";
    }
    
    public String logout() {
        UserRegister userManager = new UserRegister();
        userManager.logoutUser();
        
        return "/index.xhtml";
    }
}
