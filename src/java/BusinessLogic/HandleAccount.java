/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataAccess.DAO.AccountDAO;
import DataAccess.DAO.TransactionDAO;
import DataAccess.Entity.Account;
import DataAccess.Entity.Transaction;
import java.math.BigInteger;

/**
 *
 * @author ArqSoft
 */
public class HandleAccount {
    
    public String createAccount(String name, String passwordAccount, BigInteger document){
        
        Account account = new Account();
        
        account.setDocument(document.longValue());
        account.setPassword(passwordAccount);
        account.setName(name);
        account.setBalance(0);
        
        AccountDAO accountDAO = new AccountDAO();
        Account accountE = accountDAO.persist(account);
        if (accountE != null) {
            return "la cuenta ha sido creada, su número de cuenta es " + account.getAccountNumber() + ".";
        } else {
            return "la cuenta no pudo ser creada.";
        }        
    }
    
    public String consign(Long accountNUmber, int consignValue){
        
        AccountDAO accountDAO = new AccountDAO();
        if (accountDAO.editBalance(accountNUmber, consignValue)) {
            return "la consignación ha sido exitosa.";
        } else {
            return "no se pudo realizar la consignación.";            
        }
    }
    
    public String extractBalance(Long accountNumber){
        
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.searchByAccountNumber(accountNumber);
        
        if (account != null) {
            return "su saldo es $" + account.getBalance() + ".";
        } else {
            return "no se pudo conocer el saldo.";
        }        
    }

    public String extractTransaction(Long transactionNumber) {
        
        TransactionDAO transactionDAO = new TransactionDAO();
        Transaction transaction = transactionDAO.searchByTransactionNumber(transactionNumber);
        if (transaction != null) {
            return "la transacción número " + transactionNumber + " fue realizada el " + transaction.getDate() + ", por un valor de $" + transaction.getAmount() + " y ejecutada por el usuario " + transaction.getAccountaccountNumber().getName() + ".";
        } else {
            return "la transacción número " + transactionNumber + " no existe.";
        }
    }
    
}
