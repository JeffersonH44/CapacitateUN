/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.DAO.UserRegisterDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import DataAccess.Entity.UserRegister;

/**
 *
 * @author ArqSoft
 */
public class HandleUserRegister {
    
    public String createUser(String firstname, String lastname, String username, String password, int Id){
        
        User user = new User();
        user.setRole(User.USER);
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
    
    public Object[] searchAll() {
        UserRegisterDAO userDAO = new UserRegisterDAO();
        
       Object[] resultList = userDAO.searchAll();
        return resultList;
    }
    
    public Object[] searchIdCourses(Courses idCourses ) {
        
        UserRegisterDAO userRegisterDAO = new UserRegisterDAO();
        
        Object[] resultList = userRegisterDAO.searchIdCourses(idCourses);
        return resultList;
    }
    
    
    
}
