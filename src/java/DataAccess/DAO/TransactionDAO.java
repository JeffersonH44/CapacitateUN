/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Account;
import DataAccess.Entity.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ArqSoft
 */
public class TransactionDAO {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");    

    public Transaction persist(Transaction transaction) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(transaction);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return transaction;     
        }
    }

    public Transaction searchByTransactionNumber(Long transactionNumber) {
        EntityManager em = emf.createEntityManager();
        Transaction t = null;
        try {
            t = em.find(Transaction.class
                    , transactionNumber);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return t;
        }
    }
    
}
