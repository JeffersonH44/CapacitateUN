/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.UserManagement;

import DataAccess.Entity.User;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 * Clase encargada del manejo del usuario de la sesión.
 * @author Jefferson
 */
public class PrivilegeVerifier {
    
    /**
     * Hace el login del usuario
     * @param user
     * @throws IllegalArgumentException - En caso de que el usuario sea nulo.
     */
    void login(User user) throws IllegalArgumentException {
        if(user == null) throw new IllegalArgumentException("El usuario no puede ser nulo.");
        
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        sessionMap.put("USER_LOGGED", user);
    }
    
    /**
     * Realiza el logout del usuario actual.
     */
    void logout() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        sessionMap.remove("USER_LOGGED");
    }
    
    /**
     * Obtiene el usuario que se encuenta en la sesión actual.
     * @return Usuario de la sesión.
     */
    public User getUserLogged() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        User user = (User)sessionMap.get("USER_LOGGED");
        // TODO: retrieve from database
        return user;
    }
    
}
