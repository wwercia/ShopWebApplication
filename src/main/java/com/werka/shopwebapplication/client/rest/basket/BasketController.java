package com.werka.shopwebapplication.client.rest.basket;

import com.werka.shopwebapplication.config.DataHelper;
import com.werka.shopwebapplication.domain.api.BasicBasketBookInfo;
import com.werka.shopwebapplication.domain.api.services.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/basket")
public class BasketController extends HttpServlet {

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setBooksAttributes(req);
        req.setAttribute("orderTotal", bookService.getOrderTotal());
        req.getRequestDispatcher("/WEB-INF/pages/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("bookTitle");
        bookService.saveBook(title);

        // Pobierz URL poprzedniej strony
        String referer = req.getHeader("Referer");

        // Przekieruj użytkownika na poprzednią stronę
        if (referer != null) {
            resp.sendRedirect(referer);
        } else {
            // Jeśli brak referera, przekieruj na domyślną stronę
            resp.sendRedirect("/WEB-INF/pages/products.jsp");
        }

        //setBooksAttributes(req);
        //req.setAttribute("orderTotal", bookService.getOrderTotal());
        //req.getRequestDispatcher("/WEB-INF/pages/basket.jsp").forward(req, resp);
    }

    private List<BasicBasketBookInfo> getBooksFromBasket() {
        return bookService.getBooksInBasket(DataHelper.getClientId());
    }

    private void setBooksAttributes(HttpServletRequest req) {
        List<BasicBasketBookInfo> books =  getBooksFromBasket();
        if (!books.isEmpty()) {
            req.setAttribute("areBooksInBasket", "true");
            req.setAttribute("resultBooks", getBooksFromBasket());
        } else {
            req.setAttribute("areBooksInBasket", "false");
        }
    }

}
