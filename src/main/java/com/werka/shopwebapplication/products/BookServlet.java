package com.werka.shopwebapplication.products;

import com.werka.shopwebapplication.products.dao.Book;
import com.werka.shopwebapplication.products.dao.BookDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private BookDao bookDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(bookDao == null) {
            bookDao = new BookDao();
        }

        String bookTitle = request.getParameter("bookTitle");
        //Book book = bookDao.findBookById(Integer.parseInt(bookId));
        Book book = bookDao.findBookByTitle(bookTitle);

        System.out.println(book);

        request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
    }
}
