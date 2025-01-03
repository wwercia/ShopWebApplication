<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 09.08.2024
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Account</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/account/accountStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="../../segments/sidebar.jspf" %>
            <main class="content">

                <div class="account-details">
                    <div class="account-details2">

                        <div class="upper-section">
                            <h1 class="details-text">Account Details</h1>
                            <form action="editAccountData" method="get">
                                <button type="submit" class="edit-data-button">Edit Data</button>
                            </form>
                        </div>

                        <div class="info">
                            <p class="text">Email: ${requestScope.email}</p>
                            <div class="change-password-section">
                                <p class="text">Password: ${requestScope.password}</p>
                                <a href="changePassword" class="change-password-button">
                                    Change Password
                                </a>
                            </div>
                            <p class="text">Name: ${requestScope.name}</p>
                            <p class="text">Surname: ${requestScope.surname}</p>
                            <p class="text">Phone Number: ${requestScope.phoneNumber}</p>
                            <p class="text">Address: ${requestScope.address}</p>
                            <p class="text">Town: ${requestScope.town}</p>
                            <p class="text">Postcode: ${requestScope.postcode}</p>
                        </div>

                    </div>

                </div>
            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
