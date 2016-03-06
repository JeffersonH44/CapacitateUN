/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Account;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ArqSoft
 */
public class AccountDAO {
    
    
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    public Account persist(Account account) {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(account);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return account;  
        }
    }
    
    public Account searchByAccountNumber(Long accountNumber) {
        
        EntityManager em = emf.createEntityManager();
        Account account = null;
        
        try {
            account = em.find(Account.class
                    , accountNumber);
        } catch (Exception e){
        } finally {
            em.close();
            return account;
        }
    }
    
    public Account searchByDocument(BigInteger document) {
        
        EntityManager em = emf.createEntityManager();
        Account account = null;
        Query q = em.createNamedQuery("Cuenta.findByCedula");
        q.setParameter(1, document);
        
        try {
            account = (Account) q.getSingleResult();
        } catch (Exception e) {
        } finally {
            em.close();
            return account;
        }
    }
    
    public void edit(Account account){
        Account acountNew;
        EntityManager em = emf.createEntityManager();  
        em.getTransaction().begin();
        try {
           acountNew = em.merge(em.find(Account.class, account.getAccountNumber())); 
            acountNew.setBalance(account.getBalance());
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean editBalance(Long accountNumber, int consigmentValue) {
        Account accountNew;
        EntityManager em = emf.createEntityManager();  
        em.getTransaction().begin();
        boolean success = true;
        
        try {
            accountNew = em.merge(em.find(Account.class, accountNumber)); 
            accountNew.setBalance(accountNew.getBalance()+ consigmentValue);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            success = false;
        } finally {
            em.close();
            return success;
        }
    }    
}
