package com.werka.shopwebapplication.client.rest;

import com.werka.shopwebapplication.domain.api.BookFullInfo;
import com.werka.shopwebapplication.domain.api.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/book")
public class BookController extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookTitle = request.getParameter("bookTitle");
        BookFullInfo book = bookService.findBookByTitle(bookTitle).orElseThrow();

        request.setAttribute("bookInfo", book);
        request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
    }
}
