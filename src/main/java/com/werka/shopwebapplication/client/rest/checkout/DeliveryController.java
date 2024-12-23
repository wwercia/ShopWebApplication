package com.werka.shopwebapplication.client.rest.checkout;

import com.werka.shopwebapplication.domain.api.services.BookService;
import com.werka.shopwebapplication.domain.api.services.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/processDelivery")
public class DeliveryController extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/basket");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(bookService.getOrderTotal() == 0) {
            response.sendRedirect(request.getContextPath() + "/basket");
            return;
        }

        String deliveryName = request.getParameter("delivery");
        orderService.saveDeliveryMethod(deliveryName.toUpperCase());

        response.sendRedirect(request.getContextPath() + "/checkout");
    }

}
