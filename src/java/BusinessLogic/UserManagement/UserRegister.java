/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.UserManagement;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import javax.ejb.EJB;

/**
 *
 * @author ArqSoft
 */
public class UserRegister {
    
    public User createUser(String firstname, String lastname, String username, String password, int Id){
        
        User user = new User();
        user.setRole(User.USER);
        user.setId(Id);
        user.setPassword(password);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        
        return user;

    }
    
    public User loginUser(String username, String password, UserDAO userDAO) {
        
        User user = userDAO.searchByUsername(username);
        if(user.getPassword().equals(password)) {
            PrivilegeVerifier login = new PrivilegeVerifier();
            login.login(user);
            return user;
        } else {
            return null;
        }
    }
    
}
