package com.werka.shopwebapplication.client.rest;

import com.werka.shopwebapplication.domain.api.BookBasicInfo;
import com.werka.shopwebapplication.domain.api.BookFullInfo;
import com.werka.shopwebapplication.domain.api.services.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookController extends HttpServlet {

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookTitle = request.getParameter("bookTitle");
        BookFullInfo book = bookService.findBookByTitle(bookTitle).orElseThrow();

        if(!book.getSeries().isEmpty()) {
            List<BookBasicInfo> booksInSeries = bookService.findBooksInSeries(book);
            if(!booksInSeries.isEmpty()){
                request.setAttribute("areBooksInSeries", "true");
                request.setAttribute("booksInSeries", booksInSeries);
            }else {
                request.setAttribute("areBooksInSeries", "false");
            }
        }else {
            request.setAttribute("areBooksInSeries", "false");
        }

        request.setAttribute("bookInfo", book);
        request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
    }

}
