package com.werka.shopwebapplication.client.home;

import com.werka.shopwebapplication.domain.book.Book;
import com.werka.shopwebapplication.domain.book.BookDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class HomeController extends HttpServlet {

    private BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> recommendedBooks = bookDao.getRecommendedBooks();
        request.setAttribute("recommendedBooks", recommendedBooks);

        List<Book> bestRatedBooks = bookDao.getBestRatedBooks(4);
        request.setAttribute("bestRatedBooks", bestRatedBooks);

        request.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(request, resp);
    }



}
