package com.bank.controller;

import com.bank.dao.AccountDao;
import com.bank.entity.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/bank")
public class BankServlet extends HttpServlet {

    private AccountDao accountDAO;

    @Override
    public void init() throws ServletException {

        this.accountDAO = new AccountDao();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        switch (action) {
            case "login":
                handleLogin(request, response);
                break;

            case "deposit":
                handleDeposit(request, response);
                break;

            case "withdraw":
                handleWithdraw(request, response);
                break;

            case "transfer":
                handleTransfer(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            handleLogout(request, response);
        } else {

            response.sendRedirect("dashboard.jsp");
        }
    }



    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accNumStr = request.getParameter("accountNumber");
        String pinStr = request.getParameter("pin");

        try {
            int accountNumber = Integer.parseInt(accNumStr);
            int pin = Integer.parseInt(pinStr);


            Account account = accountDAO.validateLogin(accountNumber, pin);

            if (account != null) {

                HttpSession session = request.getSession();
                session.setAttribute("userAccount", account);
                response.sendRedirect("dashboard.jsp");
            } else {

                request.setAttribute("errorMessage", "Invalid Account Number or PIN!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Please enter valid numeric values!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void handleDeposit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userAccount") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Account currentAccount = (Account) session.getAttribute("userAccount");
        double amount = Double.parseDouble(request.getParameter("amount"));


        boolean success = accountDAO.updateBalance(currentAccount.getAccountNumber(), amount, "DEPOSIT");

        if (success) {

            Account updatedAccount = accountDAO.getAccount(currentAccount.getAccountNumber());
            session.setAttribute("userAccount", updatedAccount);
            session.setAttribute("successMessage", "Amount Deposited Successfully!");
        } else {
            session.setAttribute("errorMessage", "Deposit Failed! Try again.");
        }
        response.sendRedirect("dashboard.jsp");
    }

    private void handleWithdraw(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userAccount") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Account currentAccount = (Account) session.getAttribute("userAccount");
        double amount = Double.parseDouble(request.getParameter("amount"));

        if (currentAccount.getBalance() < amount) {
            session.setAttribute("errorMessage", "Insufficient Balance for Withdrawal!");
            response.sendRedirect("dashboard.jsp");
            return;
        }

        boolean success = accountDAO.updateBalance(currentAccount.getAccountNumber(), amount, "WITHDRAW");

        if (success) {
            Account updatedAccount = accountDAO.getAccount(currentAccount.getAccountNumber());
            session.setAttribute("userAccount", updatedAccount);
            session.setAttribute("successMessage", "Amount Withdrawn Successfully!");
        } else {
            session.setAttribute("errorMessage", "Withdrawal Failed!");
        }
        response.sendRedirect("dashboard.jsp");
    }

    private void handleTransfer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userAccount") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Account currentAccount = (Account) session.getAttribute("userAccount");
        int targetAccountNumber = Integer.parseInt(request.getParameter("targetAccount"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        if (currentAccount.getBalance() < amount) {
            session.setAttribute("errorMessage", "Insufficient Balance for Transfer!");
            response.sendRedirect("dashboard.jsp");
            return;
        }


        boolean success = accountDAO.transferMoney(currentAccount.getAccountNumber(), targetAccountNumber, amount);

        if (success) {
            Account updatedAccount = accountDAO.getAccount(currentAccount.getAccountNumber());
            session.setAttribute("userAccount", updatedAccount);
            session.setAttribute("successMessage", "Money Transferred Successfully!");
        } else {
            session.setAttribute("errorMessage", "Transaction Failed! Money Rollbacked.");
        }
        response.sendRedirect("dashboard.jsp");
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}