/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.UserManagement;

/**
 *
 * @author arqsoft
 *
 *
 */
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author root
 */
public class LoginLDAP {

    String ldapHost = "127.0.0.1";
    String dn = "cn=admin,dc=arqsoft,dc=com";
    String password = "ArqSoft2016i";

    int ldapPort = LDAPConnection.DEFAULT_PORT;
    int ldapVersion = LDAPConnection.LDAP_V3;

    private LDAPConnection lc = new LDAPConnection();

    char base64Table[] = new char[64];

    private void startBase64Table() {
        int j = 0;
        for (int i = 0; i < 26; i++) {
            base64Table[j++] = (char) ('A' + i);
        }
        for (int i = 0; i < 26; i++) {
            base64Table[j++] = (char) ('a' + i);
        }
        for (int i = 0; i < 10; i++) {
            base64Table[j++] = (char) ('0' + i);
        }
        base64Table[j++] = '+';
        base64Table[j++] = '/';
    }

    public String login(String nombreUsuario, String contrasena) {

        System.out.println("DATOS ---> " + nombreUsuario + " - " + contrasena);

        if (conectar()) {
            if (validarContrasena(nombreUsuario, contrasena)) {
                return "Login exitoso";
            } else {
                return "El usuario y la contraseña no corresponden";
            }
        } else {
            return "Conexion al Servidor LDAP fallida";
        }

    }

    public void desconectar() throws LDAPException {
        if (lc.isConnected()) {
            lc.disconnect();
        }
    }

    public Boolean conectar() {
        if (lc.isConnected()) {
            return true;
        } else {
            try {
                lc.connect(ldapHost, ldapPort);
                System.out.println("====Conectado al Servidor LDAP====");
                lc.bind(ldapVersion, dn, password.getBytes("UTF8"));
                System.out.println("====Autenticado en el servidor====");
                return true;
            } catch (Exception ex) {
                System.out.println("====ERROR al conectarse al Servidor LDAP====");
                ex.printStackTrace();
                return false;
            }
        }
    }

    public Boolean validarContrasena(String nombreUsuario, String contrasena) {
        if (!lc.isConnected()) {
            if (!conectar()) {
                return false;
            }
        }
        String dn = "cn=" + nombreUsuario + ",ou=Capacitaciones,dc=arqsoft,dc=com";
        try {
            lc.bind(dn, contrasena);
            System.out.println("====Contrasena Validada====");
            return true;
        } catch (Exception ex) {
            System.out.println("====ERROR al validar la contrasena====");
            return false;
        }
    }

    public int searchRole(String user) throws LDAPException {
        if (!lc.isConnected()) {
            if (!conectar()) {
                return 1;
            }
        }
        int role = 1;
        LDAPSearchResults sr = lc.search("ou=Capacitaciones,dc=arqsoft,dc=com", LDAPConnection.SCOPE_ONE, "(cn=" + user + ")", new String[]{"gidNumber"}, false);
        while (sr.hasMore()) {
            LDAPEntry nextEntry = null;
            nextEntry = sr.next();
            if (nextEntry.getAttribute("gidNumber").getStringValue().equals("701")) {
                role = 3;
            }
            if (nextEntry.getAttribute("gidNumber").getStringValue().equals("703")) {
                role = 2;
            }
        }
        desconectar();
        return role;
    }

    public boolean registrar(String id, String name, String lastName, String password, String username, String role) throws LDAPException {
        
        switch(role) {
            case "Usuario":
                role = "702";
                break;
            case "Capacitador":
                role = "703";
                break;
            case "Administrador":
                role = "701";
                break;
        }
        try {    
            if (conectar()) {
                LDAPAttribute attribute = null;
                LDAPAttributeSet attributeSet = new LDAPAttributeSet();
                attributeSet.add(new LDAPAttribute("objectclass", new String[]{"inetOrgPerson", "posixAccount", "top"}));
                attributeSet.add(new LDAPAttribute("cn", name + " " + lastName));
                attributeSet.add(new LDAPAttribute("givenname", name));
                attributeSet.add(new LDAPAttribute("uidNumber", id));
                attributeSet.add(new LDAPAttribute("uid", name + " " + lastName));
                attributeSet.add(new LDAPAttribute("gidNumber", role));
                attributeSet.add(new LDAPAttribute("homeDirectory", "/home/users/" + name.toLowerCase().charAt(0) + lastName.toLowerCase()));
                attributeSet.add(new LDAPAttribute("sn", lastName));
                attributeSet.add(new LDAPAttribute("userPassword", "{MD5}" + hexToBase64(MD5(password))));
                String dn = "cn=" + username + ",ou=Capacitaciones,dc=arqsoft,dc=com";
                LDAPEntry newEntry = new LDAPEntry(dn, attributeSet);
                lc.add(newEntry);
                System.out.println("\nAdded object: " + dn + " successfully.");
                desconectar();
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            desconectar();
            ex.printStackTrace();
            return false;
        }
    }

    public void changePassword(String username, String password) {

        if (!lc.isConnected()) {
            if (!conectar()) {
                System.out.println("Error en la conexion");
            }
        }
        try {
            LDAPAttribute atrubuto;
            atrubuto = new LDAPAttribute("userPassword", "{MD5}" + hexToBase64(MD5(password)));
            String dn = "cn=" + username + ",ou=Capacitaciones,dc=arqsoft,dc=com";
            lc.modify(dn, new LDAPModification(LDAPModification.REPLACE, atrubuto));
            System.out.println("Atributo Modificado OK...");
        } catch (LDAPException | NoSuchAlgorithmException ex) {
            System.err.println("Error: LdapError");
            ex.printStackTrace();
        }
    }
    
    public void changeRole(String username, String role) {
        if (!lc.isConnected()) {
            if (!conectar()) {
                System.out.println("Error en la conexion");
            }
        }
        switch(role) {
            case "Usuario":
                role = "702";
                break;
            case "Capacitador":
                role = "703";
                break;
            case "Administrador":
                role = "701";
                break;
        }
        try {
            LDAPAttribute atrubuto;
            atrubuto = new LDAPAttribute("gidNumber", role);
            String dn = "cn=" + username + ",ou=Capacitaciones,dc=arqsoft,dc=com";
            lc.modify(dn, new LDAPModification(LDAPModification.REPLACE, atrubuto));
            System.out.println("Atributo Modificado OK...");
        } catch (LDAPException ex) {
            System.err.println("Error: LdapError");
            ex.printStackTrace();
        }
    }

    public void DeleteUser(String username) {
        if (!lc.isConnected()) {
            if (!conectar()) {
                System.out.println("Error en la conexion");
            }
        }
        String dn = "cn=" + username + ",ou=Capacitaciones,dc=arqsoft,dc=com";
        try {
            lc.delete(dn);
            System.out.println("\nEntry: " + dn + " Fue Eliminado Correctamente...");
        } catch (LDAPException e) {
            if (e.getResultCode() == LDAPException.NO_SUCH_OBJECT) {
                System.err.println("Error: NO existe ese usuario...");
            } else {
                System.err.println("Error: " + e.toString());
            }
        }
    }

    private String MD5(String s) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    private String hexToBase64(String s) {
        startBase64Table();
        s = s.toUpperCase();
        String bitS = "";
        int aux;
        String auxS;
        for (int i = 0; i < s.length(); i++) {
            aux = Integer.parseInt(s.substring(i, i + 1), 16);
            auxS = Integer.toBinaryString(aux);
            while (auxS.length() < 4) {
                auxS = "0" + auxS;
            }
            bitS += auxS;
        }
        int sigE = 0;
        while (bitS.length() % 6 != 0) {
            bitS += "00";
            sigE++;
        }
        String encoded = "";
        for (int i = 0; i < bitS.length(); i += 6) {
            encoded += base64Table[Integer.parseInt(bitS.substring(i, i + 6), 2)];
        }
        for (int i = 0; i < sigE; i++) {
            encoded += "=";
        }
        return encoded;
    }

}
