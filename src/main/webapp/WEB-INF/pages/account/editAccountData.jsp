<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 01.01.2025
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>editAccountData</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/account/editAccountDataStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
    </head>
    <body>
    <div class="container">
        <%@ include file="../../segments/sidebar.jspf" %>

        <main class="content">

            <div class="account-details">
                <div class="account-details2">

                    <h1 class="details-text">Account Details</h1>


                    <div class="info>">
                        <p class="text">Email: ${requestScope.email}</p>

                        <div class="change-password-section">
                            <p class="text">Password: ${requestScope.password}</p>
                            <a href="changePassword" class="change-password-button">
                                Change Password
                            </a>
                        </div>

                        <form action="editAccountData" method="post">
                            <div class="form-group">
                                <label for="name" class="form-text">Name:</label>
                                <input type="text" id="name" name="name" value="${requestScope.name}" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label for="surname" class="form-text">Surname:</label>
                                <input type="text" id="surname" name="surname" value="${requestScope.surname}" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label for="phoneNumber" class="form-text">Phone Number:</label>
                                <input type="tel" id="phoneNumber" name="phoneNumber" value="${requestScope.phoneNumber}" class="form-control" required>
                            </div>

                            <div class="address-details">
                                <div class="form-group">
                                    <label for="address" class="form-text">Address:</label>
                                    <input type="text" id="address" name="address" value="${requestScope.address}" class="form-control" required>
                                </div>

                                <div class="form-group">
                                    <label for="town" class="form-text">Town:</label>
                                    <input type="text" id="town" name="town" value="${requestScope.town}" class="form-control" required>
                                </div>

                                <div class="form-group">
                                    <label for="postcode" class="form-text">Postcode:</label>
                                    <input type="text" id="postcode" name="postcode" value="${requestScope.postcode}" class="form-control" required>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
