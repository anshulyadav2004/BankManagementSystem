<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apna Bank - Login</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; padding-top: 100px; }
        .login-box { background: white; padding: 30px; display: inline-block; border-radius: 8px; box-shadow: 0px 0px 10px #ccc; }
        input { display: block; width: 250px; margin: 10px auto; padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
        button { background: #007bff; color: white; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; width: 100%; }
        button:hover { background: #0056b3; }
        .error { color: red; margin-bottom: 10px; }
    </style>
</head>
<body>

<div class="login-box">
    <h2>Apna Bank Login</h2>

    <% if(request.getAttribute("errorMessage") != null) { %>
    <div class="error"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <form action="bank" method="POST">
        <input type="hidden" name="action" value="login">

        <input type="text" name="accountNumber" placeholder="Enter Account Number" required>
        <input type="password" name="pin" placeholder="Enter 4-Digit PIN" required maxlength="4">

        <button type="submit">Login</button>
    </form>
</div>

</body>
</html>