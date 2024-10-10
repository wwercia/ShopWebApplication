<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 09.08.2024
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Products</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/productsStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/bookElementStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/productElementsStyles.css">
    </head>
    <body>
        <div class="container">
            <%@ include file="../segments/sidebar.jspf" %>

            <main class="content">

                <div class="top-box">
                    <a href="main" class="logo">BookShop</a>
                    <form action="products" method="post" class="search-form">
                        <input class="searchedTextField" type="text" id="searchedText" name="searchedText" placeholder="What are you looking for?" autocomplete="off">
                        <button type="submit" hidden="hidden"></button>
                    </form>
                </div>

                <div class="bottom-box">

                    <div class="categories-box">
                        <h2>Categories</h2>
                        <ul class="categories-list">
                            <c:forEach var="cat" items="${requestScope.categories}">
                                <li class="book-category">
                                    <a class="category-link" href="products?category=${cat.name}&sortBy=${param.sortBy != null ? param.sortBy : 'best-selling'}"><c:out value="${cat.name}"/></a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="results-box">

                        <div class="top-result-box">
                            <h2 class="books-cat">Books: <c:out value="${requestScope.category}"/></h2>

                            <form class="sortForm" action="products" method="get">
                                <!-- Ukryte pole do przekazywania aktualnie wybranej kategorii -->
                                <input type="hidden" name="category" value="${param.category}">

                                <label for="sortBy">Sort by:</label>
                                <select id="sortBy" name="sortBy" onchange="this.form.submit()">
                                    <option value="best-selling" ${param.sortBy == null || param.sortBy == '' || param.sortBy == 'best-selling' ? 'selected' : ''}>Best-selling titles</option>
                                    <option value="price-low-high" ${param.sortBy == 'price-low-high' ? 'selected' : ''}>Price, low to high</option>
                                    <option value="price-high-low" ${param.sortBy == 'price-high-low' ? 'selected' : ''}>Price, high to low</option>
                                    <option value="title-az" ${param.sortBy == 'title-az' ? 'selected' : ''}>Title, A-Z</option>
                                    <option value="title-za" ${param.sortBy == 'title-za' ? 'selected' : ''}>Title, Z-A</option>
                                </select>
                            </form>
                        </div>
                        <h2 class="results-size">Results: <c:out value="${requestScope.resultsSize}" /> </h2>
                        <hr>

                        <div class="books-container">
                            <c:forEach var="book" items="${requestScope.resultBooks}">
                                <%@ include file="../segments/bookElement.jspf"%>
                            </c:forEach>
                        </div>

                    </div>
                </div>

            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
