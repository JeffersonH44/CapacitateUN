/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import java.math.BigInteger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import BusinessLogic.Controller.HandleAccount;
import BusinessLogic.Controller.HandleUser;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ArqSoft
 */
@ManagedBean
@SessionScoped
public class CreateAccountBean {
    
     private String name;
     private BigInteger document;
     private String passwordAccount;
     private String message;

    public CreateAccountBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getDocument() {
        return document;
    }

    public void setDocument(BigInteger document) {
        this.document = document;
    }


    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void createAccount(){   
        HandleAccount createAccount = new HandleAccount();
        message = createAccount.createAccount(name, passwordAccount, document);
    }
    
}
