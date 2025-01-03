package com.werka.shopwebapplication.client.home;

import com.werka.shopwebapplication.domain.api.BookBasicInfo;
import com.werka.shopwebapplication.domain.api.services.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class HomeController extends HttpServlet {

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        List<BookBasicInfo> recommendedBooks = bookService.getRecommendedBooks();
        request.setAttribute("recommendedBooks", recommendedBooks);

        List<BookBasicInfo> bestRatedBooks = bookService.getBestRatedBooks(3);
        request.setAttribute("bestRatedBooks", bestRatedBooks);

        request.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(request, resp);
    }


}
