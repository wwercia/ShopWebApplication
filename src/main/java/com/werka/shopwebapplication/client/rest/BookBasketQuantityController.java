package com.werka.shopwebapplication.client.rest;

import com.werka.shopwebapplication.domain.api.BookService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/quantity")
public class BookBasketQuantityController extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String bookTitle = request.getParameter("bookTitle");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        bookService.updateBookQuantity(bookTitle, quantity);


        response.sendRedirect(request.getContextPath() + "/basket");
    }

}
