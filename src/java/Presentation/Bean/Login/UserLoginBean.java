/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Login;

import BusinessLogic.UserManagement.LoginLDAP;
import BusinessLogic.UserManagement.UserRegister;
import DataAccess.DAO.UserDAO.UserDAO;
import DataAccess.Entity.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean encargado de hacer el login de usuario y gestión de elementos globales
 * al usuario.
 *
 * @author Jefferson
 */
@SessionScoped
@ManagedBean(name = "userLoginBean", eager = true)
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

    /**
     * Función encargada de hacer el login de usuario.
     *
     * @return La página inicial de acuerdo al usuario.
     */
    public String login() {
        UserRegister userManager = new UserRegister();
        LoginLDAP login = new LoginLDAP();
        try {
            message = login.login(username, password);
            if (message == "Login exitoso") {
                user = userManager.loginUser(username, userDAO);
            }
        } catch (Exception e) {
            message = "Error en Usuario o Contraseña";
            e.printStackTrace();
            return "/index.xhtml";
        }
        try {
            switch (login.searchRole(username)) {
                case User.ADMIN:
                    return "faces/pages/admin/adminIndex.xhtml";
                case User.TRAINER:
                    return "faces/pages/trainer/trainerIndex.xhtml";
                case User.USER:
                    return "faces/pages/user/userIndex.xhtml";
            }
        } catch (Exception e) {
            System.out.println("ERROR: ROLE Ldap");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Función encargada de hacer el logout del usuario.
     *
     * @return La página inicial.
     */
    public String logout() {
        UserRegister userManager = new UserRegister();
        userManager.logoutUser();

        return "/index.xhtml";
    }

    /**
     * Redirecciona a la página de editar las parámetros del usuario.
     *
     * @return Página de edición de usuario.
     */
    public String editUser() {
        return "faces/pages/editUser.xhtml";
    }

    /**
     * Función encargada de actualizar el usuario con los nuevos atributos.
     *
     * @return Página príncipal de acuerdo al rol de usuario.
     */
    public String updateUser() {
        userDAO.update(user);
        LoginLDAP ldap = new LoginLDAP();
        ldap.changePassword(user.getUsername(), user.getPassword());
        this.logout();

        this.password = user.getPassword();
        this.login();
        return this.getIndexPageByUser();
    }

    /**
     * Obtiene la página template de acuerdo al usuario registrado.
     *
     * @return Dirección de la página template.
     */
    public String getTemplatePageByUser() {
        if (user == null) {
            throw new IllegalStateException("El usuario no puede estar nulo para obtener la página");
        }

        switch (user.getRole()) {
            case User.ADMIN:
                return "/pages/admin/admin.xhtml";
            case User.TRAINER:
                return "/pages/trainer/trainer.xhtml";
            default:
                return "/pages/user/user.xhtml";
        }
    }

    /**
     * Obtiene la página principal del usuario de acuerdo a su rol.
     *
     * @return Página principal.
     */
    private String getIndexPageByUser() {
        if (user == null) {
            throw new IllegalStateException("El usuario no puede estar nulo para obtener la página");
        }

        switch (user.getRole()) {
            case User.ADMIN:
                return "admin/adminIndex.xhtml";
            case User.TRAINER:
                return "trainer/trainerIndex.xhtml";
            default:
                return "user/userIndex.xhtml";
        }
    }
}
