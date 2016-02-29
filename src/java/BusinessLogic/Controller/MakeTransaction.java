/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.AccountDAO;
import DataAccess.DAO.TransactionDAO;
import DataAccess.Entity.Account;
import DataAccess.Entity.Transaction;
import java.util.Date;

/**
 *
 * @author ArqSoft
 */
public class MakeTransaction {

    public ROB make(String password, Long accountNumber, Long accountNumberReceiver, int value){        
        
        AccountDAO accountDAO = new AccountDAO();
        Account accountBuyer = accountDAO.searchByAccountNumber(accountNumber);
        Account accountSecurity = accountDAO.searchByAccountNumber(accountNumberReceiver);
        
        ROB rob = new ROB();

        if (accountBuyer != null) {
            if (accountBuyer.getPassword().equals(password)) {
                if (accountBuyer.getBalance() > value) {
                    accountBuyer.setBalance(accountBuyer.getBalance()- value);
                    accountSecurity.setBalance(accountSecurity.getBalance()+ value);

                    Transaction transaction = new Transaction();
                    transaction.setAccountaccountNumber(accountBuyer);
                    transaction.setDate(new Date());
                    transaction.setAmount(value);

                    TransactionDAO transactionDAO = new TransactionDAO();
                    Transaction newTransaction = transactionDAO.persist(transaction);

                    accountDAO.edit(accountBuyer);
                    accountDAO.edit(accountSecurity);

                    rob.setSuccess(true);
                    rob.setErr_message("Transacción Satisfactoria");
                    rob.setData(newTransaction.getTransactionNumber());
                    
                    return rob;

                } else {     
                    rob.setSuccess(false);
                    rob.setErr_message("Saldo insuficiente, su saldo es $" + accountBuyer.getBalance() + ".");
                    return rob;
                }
            } else {
                rob.setSuccess(false);
                rob.setErr_message("Contraseña Incorrecta");
                return rob;
            }
        } else {      
            rob.setSuccess(false);
            rob.setErr_message("Cuenta Inexistente");
            return rob;      
        }
    }   
}
