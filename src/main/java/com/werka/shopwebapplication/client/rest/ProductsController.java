package com.werka.shopwebapplication.client.rest;

import com.werka.shopwebapplication.domain.api.BookBasicInfo;
import com.werka.shopwebapplication.domain.api.BookService;
import com.werka.shopwebapplication.domain.api.CategoryName;
import com.werka.shopwebapplication.domain.api.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductsController extends HttpServlet {

    private final CategoryService categoryService = new CategoryService();
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String category = request.getParameter("category");
        String sortBy = request.getParameter("sortBy");

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "best-selling";
        }
        if(category == null || category.isEmpty()) {
            category = "all";
        }

        List<BookBasicInfo> resultBooks = bookService.findSortedBooksByCategory(category, sortBy);
        List<CategoryName> categories = categoryService.findAllCategoryNames();

        request.setAttribute("resultBooks", resultBooks);
        request.setAttribute("resultsSize", resultBooks.size());
        request.setAttribute("categories", categories);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //obsługuje wyszukiwanie poszczególnych wyników przez użytkownika (pasek wyszukiwania)

    }

}
