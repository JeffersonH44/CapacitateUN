/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;

/**
 *
 * @author ArqSoft
 */
public class HandleUser {
    
    public String createUser(String firstname, String lastname, String username, int role, String password, int Id){
        
        User user = new User();
        user.setRole(role);
        user.setId(Id);
        user.setPassword(password);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        
        
        UserDAO userDAO = new UserDAO();
        boolean saved = userDAO.persist(user);
        if (saved) {
            return "el usuario con ID: " + user.getId()+ " ha sido guardado con exito .";
        } else {
            return "fallo en la creación de usuario";
        }        
    }
    
}