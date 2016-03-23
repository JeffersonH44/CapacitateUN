/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Client;

import BusinessLogic.UserManagement.UserRegister;
import DataAccess.DAO.UserDAO.UserDAO;
import DataAccess.Entity.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean encargado de la creación del usuario.
 * @author Jefferson
 */
@ManagedBean(name = "createUserBean", eager = true)
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
    
    /**
     * Método que recibe los argumentos de la vista (createUser.xhtml) y crea un 
     * nuevo usuario.
     * @return La página inicial.
     */
    public String createUser() {
        User user;
        boolean saved= false;
        try {
            UserRegister hu = new UserRegister();
            user = hu.createUser(firstname, lastname, username, password, id);
            saved = userDAO.persist(user);
        } catch (EJBException e) {
            return "createUser.xhtml";
        }
        
        
        if(saved) {
            message = "El usuario se ha creado correctamente";
        } else {
            message = "El usuario no se ha podido crear";
        }
        
        return "index.xhtml";
    }
}
