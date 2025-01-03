<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 01.01.2025
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>change password</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/account/changePasswordStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
    </head>
    <body>

        <div class="container">
            <%@ include file="../../segments/sidebar.jspf" %>

            <main class="content">

                <div class="data">
                    <div class="data2">
                        <h1 class="text">Change Password</h1>

                        <form action="changePassword" method="post">
                            <div>
                                <label for="currentPassword">Your current password: </label>
                                <input type="password" id="currentPassword" name="currentPassword" required>
                            </div>
                            <div>
                                <label for="newPassword">Enter new password: </label>
                                <input type="password" id="newPassword" name="newPassword" required>
                            </div>
                            <div>
                                <label for="confirmNewPassword" >Confirm new password: </label>
                                <input type="password" id="confirmNewPassword" name="confirmNewPassword" required>
                            </div>
                            <p class="wrongData">${requestScope.wrongData}</p>
                            <div>
                                <button class="save-changes-button" type="submit">Save Changes</button>
                            </div>
                        </form>

                    </div>

                </div>
            </main>
        </div>

        <script src="${pageContext.request.contextPath}/script.js"></script>

    </body>
</html>
