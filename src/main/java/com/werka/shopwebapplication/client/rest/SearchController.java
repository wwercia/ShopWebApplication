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

@WebServlet("/search")
public class SearchController extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookTitle = request.getParameter("searchedText");
        List<BookFullInfo> books = bookService.findSearchedBookByTitle(bookTitle);

        if(books.isEmpty()) {
            request.setAttribute("title", bookTitle);
            request.setAttribute("isBookFound", "false");
        } else {
            if(books.size() > 1){
                request.setAttribute("isBookFound", "true");
                request.setAttribute("isMoreThanOneBookFound", "true");
                request.setAttribute("booksFound", books);
                request.setAttribute("text", bookTitle);
            }else {
                request.setAttribute("isMoreThanOneBookFound", "false");
                request.setAttribute("isBookFound", "true");
                List<BookBasicInfo> booksInSeries = bookService.findBooksInSeries(books.get(0));
                if(!booksInSeries.isEmpty()){
                    request.setAttribute("areBooksInSeries", "true");
                    request.setAttribute("booksInSeries", booksInSeries);
                }else {
                    request.setAttribute("areBooksInSeries", "false");
                }
                request.setAttribute("bookInfo", books.get(0));
            }
        }
        request.getRequestDispatcher("/WEB-INF/pages/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/search.jsp").forward(request, response);
    }
}
