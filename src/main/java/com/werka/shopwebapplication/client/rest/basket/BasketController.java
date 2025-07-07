package com.werka.shopwebapplication.client.rest.basket;

import com.werka.shopwebapplication.domain.api.BasicBasketBookInfo;
import com.werka.shopwebapplication.domain.api.services.BookService;
import com.werka.shopwebapplication.domain.client.Client;
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
        Client currentClient = (Client) req.getSession().getAttribute("client");
        setBooksAttributes(req, currentClient);
        req.setAttribute("orderTotal", bookService.getOrderTotal(currentClient.getId()));
        req.getRequestDispatcher("/WEB-INF/pages/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client currentClient = (Client) req.getSession().getAttribute("client");
        String title = req.getParameter("bookTitle");
        bookService.saveBook(title, currentClient.getId());

        // get url of previous page
        String referer = req.getHeader("Referer");

        // redirect client to the previous page
        if (referer != null) {
            resp.sendRedirect(referer);
        } else {
            // if there is no referer redirect to default page
            resp.sendRedirect("/WEB-INF/pages/products.jsp");
        }
    }

    private List<BasicBasketBookInfo> getBooksFromBasket(int clientId) {
        return bookService.getBooksInBasket(clientId);
    }

    private void setBooksAttributes(HttpServletRequest req, Client currentClient) {
        List<BasicBasketBookInfo> books =  getBooksFromBasket(currentClient.getId());
        if (!books.isEmpty()) {
            req.setAttribute("areBooksInBasket", "true");
            req.setAttribute("resultBooks", getBooksFromBasket(currentClient.getId()));
        } else {
            req.setAttribute("areBooksInBasket", "false");
        }
    }

}
