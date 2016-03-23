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
 * Bean encargado de la gestión de los recursos según el usuario que ha ingresado
 * a la aplicación
 * @author Jefferson
 */
@ManagedBean
@SessionScoped
public class LoggedBean implements Serializable {
    PrivilegeVerifier login = new PrivilegeVerifier();
    
    /**
     * Obtiene el rol del usuario registrado.
     * @return Rol del usuario registrado
     * @throws IllegalStateException - En caso de no encontrar el usuario.
     */
    public int getLoggedUserRole() throws IllegalStateException {
        User user = login.getUserLogged();
        
        if(user == null) throw new IllegalStateException("No se encontró el usuario registrado.");
        
        return user.getRole();
    }
    
    /**
     * Retorna los nombres del usuario registrado en la sesión.
     * @return Nombres del usuario
     * @throws IllegalStateException - En caso de no encontrar el usuario.
     */
    public String getLoggedUserNames() throws IllegalStateException {
        User user = login.getUserLogged();
        if(user == null) throw new IllegalStateException("No se encontró el usuario registrado.");
        
        return user.getFirstname() + " " + user.getLastname();
    }
    
    /**
     * Retorna el identificador del usuario de la sesión
     * @return Identificador.
     * @throws IllegalStateException - En caso de no encontrar el usuario.
     */
    public int getLoggedUserID() throws IllegalStateException {
        User user = login.getUserLogged();
        
        if(user == null) throw new IllegalStateException("No se encontró el usuario registrado.");
        return user.getId();
    }
    
    /**
     * Devuelve la página principal de acuerdo al rol del usuario
     * @return Página principal.
     * @throws IllegalStateException - En caso de no encontrar el usuario.
     */
    public String getMainPageByLoggedUser() throws IllegalStateException {
        User user = login.getUserLogged();
        
        if(user == null) throw new IllegalStateException("No se encontró el usuario registrado.");
        
        switch (user.getRole()) {
            case User.ADMIN:
                return "http://localhost:8080/CapacitateUN/faces/pages/admin/adminIndex.xhtml";
            case User.TRAINER:
                return "http://localhost:8080/CapacitateUN/faces/pages/trainer/trainerIndex.xhtml";
            default:
                return "http://localhost:8080/CapacitateUN/faces/pages/user/userIndex.xhtml";
        }
    }
    
    /**
     * Obtiene el template para el usuario de la sesión.
     * @return Template base.
     * @throws IllegalStateException - En caso de no encontrar el usuario. 
     */
    public String getTemplateByLoggedUser() throws IllegalStateException {
        User user = login.getUserLogged();
        
        if(user == null) throw new IllegalStateException("No se encontró el usuario registrado.");
        
        switch (user.getRole()) {
            case User.ADMIN:
                return "admin/admin.xhtml";
            case User.TRAINER:
                return "trainer/trainer.xhtml";
            default:
                return "user/user.xhtml";
        }
    }
}
