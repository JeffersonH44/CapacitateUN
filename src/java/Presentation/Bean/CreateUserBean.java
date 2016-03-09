/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.HandleUser;
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
@ManagedBean

@SessionScoped
public class CreateUserBean implements Serializable {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String message;
    private int id;
    @EJB
    private UserDAO userDAO;

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public String createUser() {
        User user;
        HandleUser hu = new HandleUser();
        user = hu.createUser(firstname, lastname, username, password, id);
        boolean saved = userDAO.persist(user);
        if(saved) {
            message = "El usuario se ha creado correctamente";
        } else {
            message = "El usuario no se ha podido crear";
        }
        
        return "index.xhtml";
    }
    /*
    public String createUser() {
        HandleUser hu = new HandleUser();
        message = hu.createUser(firstname, lastname, username, password, id);
        return "index.xhtml";
    }*/
}
