/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.UserManagement;

import DataAccess.DAO.UserDAO.UserDAO;
import DataAccess.Entity.User;
import javax.ejb.EJB;

/**
 * Clase encargada del manejo de usuario de la aplicación
 * @author ArqSoft
 */
public class UserRegister {
    
    /**
     * Crea un nuevo usuario a partir de los nombres, apellidos, nombre de usuario, contraseña y el identificador.
     * @param firstname - Nombres.
     * @param lastname - Apellidos.
     * @param username - Nombre de usuario.
     * @param password - Contraseña.
     * @param Id - Cédula o identificación.
     * @return El nuevo usuario creado.
     */
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
    
    /**
     * Realiza el login del usuario a partir de un nombre de usuario y contraseña
     * @param username - Nombre de usuario.
     * @param password - Contraseña.
     * @param userDAO - Para comprobar en la base de datos.
     * @return El usuario registrado o null en caso de no encontrarlo.
     */
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
    
    /**
     * realiza el logout del usuario de la sesión actual.
     * @return Verdadero en caso de que se haya realizado con éxito.
     */
    public boolean logoutUser() {
        PrivilegeVerifier login = new PrivilegeVerifier();
        login.logout();
        return true;
    }
    
}
