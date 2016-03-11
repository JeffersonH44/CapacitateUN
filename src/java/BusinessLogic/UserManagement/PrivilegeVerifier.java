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
 *
 * @author Jefferson
 */
public class PrivilegeVerifier {
    
    void login(User user) {
        if(user == null) throw new IllegalArgumentException("Invalid user");
        
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        sessionMap.put("USER_LOGGED", user);
    }
    
    void logout() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        sessionMap.remove("USER_LOGGED");
    }
    
    public User getUserLogged() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        User user = (User)sessionMap.get("USER_LOGGED");
        // TODO: retrieve from database
        return user;
    }
    
}
