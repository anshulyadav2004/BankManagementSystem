package com.bank.dao;

import com.bank.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
public class AccountDao{

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPu");


    public Account getAccount(int accountNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Account.class, accountNumber);
        } finally {
            em.close();
        }
    }


    public Account validateLogin(int accountNumber, int pin) {
        Account account = getAccount(accountNumber);
        if (account != null && account.getPin() == pin) {
            return account;
        }
        return null;
    }


    public boolean updateBalance(int accountNumber, double amount, String type) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Account account = em.find(Account.class, accountNumber);

            if ("DEPOSIT".equals(type)) {
                account.setBalance(account.getBalance() + amount);
            } else if ("WITHDRAW".equals(type)) {
                account.setBalance(account.getBalance() - amount);
            }

            em.merge(account);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }


    public boolean transferMoney(int fromAccNum, int toAccNum, double amount) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Account fromAccount = em.find(Account.class, fromAccNum);
            Account toAccount = em.find(Account.class, toAccNum);

            if (toAccount == null) {
                throw new Exception("Target account does not exist!");
            }


            fromAccount.setBalance(fromAccount.getBalance() - amount);
            em.merge(fromAccount);


            toAccount.setBalance(toAccount.getBalance() + amount);
            em.merge(toAccount);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
                System.out.println("Transaction Rollbacked successfully due to error!");
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}