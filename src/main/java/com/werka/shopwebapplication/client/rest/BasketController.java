package com.werka.shopwebapplication.client.rest;

import com.werka.shopwebapplication.domain.api.BookBasicInfo;
import com.werka.shopwebapplication.domain.api.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/basket")
public class BasketController extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("bookTitle");
        System.out.println(title);

        bookService.saveBook(title);


        req.getRequestDispatcher("/WEB-INF/pages/basket.jsp").forward(req, resp);
    }

}
