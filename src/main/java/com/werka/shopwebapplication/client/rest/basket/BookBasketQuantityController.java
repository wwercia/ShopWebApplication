package com.werka.shopwebapplication.client.rest.basket;

import com.werka.shopwebapplication.domain.api.services.BookService;
import com.werka.shopwebapplication.domain.client.Client;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/quantity")
public class BookBasketQuantityController extends HttpServlet {

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client currentClient = (Client) request.getSession().getAttribute("client");

        String bookTitle = request.getParameter("bookTitle");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        bookService.updateBookQuantity(bookTitle, quantity, currentClient.getId());


        response.sendRedirect(request.getContextPath() + "/basket");
    }

}
