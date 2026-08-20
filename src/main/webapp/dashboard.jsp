<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bank.entity.Account" %>
<%

    Account acc = (Account) session.getAttribute("userAccount");
    if (acc == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apna Bank - Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 20px; }
        .container { max-width: 800px; background: white; padding: 20px; margin: auto; border-radius: 8px; box-shadow: 0 0 10px #ccc; }
        .header { display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
        .balance-card { background: #e9ecef; padding: 15px; border-radius: 5px; margin: 20px 0; font-size: 18px; }
        .services { display: flex; justify-content: space-between; gap: 15px; }
        .card { flex: 1; background: #fff; border: 1px solid #ddd; padding: 15px; border-radius: 5px; box-shadow: 0 2px 4px #eee; }
        input { width: 90%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; }
        button { background: #28a745; color: white; border: none; padding: 8px 15px; border-radius: 4px; cursor: pointer; width: 100%; }
        button.btn-logout { background: #dc3545; width: auto; }
        .msg-success { color: green; font-weight: bold; }
        .msg-error { color: red; font-weight: bold; }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <h2>Welcome, <%= acc.getCustomerName() %>!</h2>
        <a href="bank?action=logout"><button class="btn-logout">Logout</button></a>
    </div>

    <% if(session.getAttribute("successMessage") != null) { %>
    <p class="msg-success"><%= session.getAttribute("successMessage") %></p>
    <% session.removeAttribute("successMessage"); %>
    <% } %>
    <% if(session.getAttribute("errorMessage") != null) { %>
    <p class="msg-error"><%= session.getAttribute("errorMessage") %></p>
    <% session.removeAttribute("errorMessage"); %>
    <% } %>

    <div class="balance-card">
        <strong>Account Number:</strong> <%= acc.getAccountNumber() %><br>
        <strong>Current Balance:</strong> ₹<%= acc.getBalance() %>
    </div>

    <div class="services">
        <div class="card">
            <h3>Deposit Money</h3>
            <form action="bank" method="POST">
                <input type="hidden" name="action" value="deposit">
                <input type="number" name="amount" placeholder="Enter Amount" min="1" required>
                <button type="submit">Deposit</button>
            </form>
        </div>

        <div class="card">
            <h3>Withdraw Money</h3>
            <form action="bank" method="POST">
                <input type="hidden" name="action" value="withdraw">
                <input type="number" name="amount" placeholder="Enter Amount" min="1" required>
                <button type="submit" style="background:#ffc107; color:black;">Withdraw</button>
            </form>
        </div>

        <div class="card">
            <h3>Transfer Money</h3>
            <form action="bank" method="POST">
                <input type="hidden" name="action" value="transfer">
                <input type="number" name="targetAccount" placeholder="Target Account No" required>
                <input type="number" name="amount" placeholder="Enter Amount" min="1" required>
                <button type="submit" style="background:#007bff;">Transfer</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>