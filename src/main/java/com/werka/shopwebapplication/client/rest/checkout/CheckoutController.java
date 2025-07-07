package com.werka.shopwebapplication.client.rest.checkout;

import com.werka.shopwebapplication.domain.api.*;
import com.werka.shopwebapplication.domain.api.orders.OrderFullInfo;
import com.werka.shopwebapplication.domain.api.services.BookService;
import com.werka.shopwebapplication.domain.api.services.ClientService;
import com.werka.shopwebapplication.domain.api.services.OrderService;
import com.werka.shopwebapplication.domain.client.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private final ClientService clientService = new ClientService();
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Client currentClient = (Client) request.getSession().getAttribute("client");

        //if basket is empty redirect
        if(bookService.getOrderTotal(currentClient.getId()) == 0) {
            response.sendRedirect(request.getContextPath() + "/basket");
            return;
        }

        Optional<ClientAddressDataInfo> info = clientService.getClientAddressData(currentClient.getId());
        OrderFullInfo orderInfo = orderService.getOrderInfo(currentClient.getId());

        request.setAttribute("addressData", info.get());
        request.setAttribute("deliveryMethod", orderInfo.getDeliveryMethod().getDisplayName());
        request.setAttribute("books", orderInfo.getBooks());
        request.setAttribute("orderTotal", bookService.getOrderTotal(currentClient.getId()));
        request.setAttribute("deliveryCost", orderInfo.getDeliveryMethod().getPrice());

        request.getRequestDispatcher("/WEB-INF/pages/checkout/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client currentClient = (Client) request.getSession().getAttribute("client");

        orderService.saveOrder(currentClient.getId());
        orderService.removeBooksFromBasket(currentClient.getId());

        request.getRequestDispatcher("/WEB-INF/pages/checkout/orderCompleted.jsp").forward(request, response);
    }
}
