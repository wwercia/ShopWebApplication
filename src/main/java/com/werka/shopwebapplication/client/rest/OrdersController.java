package com.werka.shopwebapplication.client.rest;

import com.werka.shopwebapplication.domain.api.orders.OrderBookInfo;
import com.werka.shopwebapplication.domain.api.orders.ordersPage.SingleOrderInfo;
import com.werka.shopwebapplication.domain.api.services.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/orders")
public class OrdersController extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        List<SingleOrderInfo> result = orderService.getOrders();
        Collections.reverse(result);

        if(result.isEmpty()){
            request.setAttribute("isResult", "false");
        }else {
            request.setAttribute("isResult", "true");
            request.setAttribute("result", result);
        }

        request.getRequestDispatcher("/WEB-INF/pages/orders.jsp").forward(request, resp);
    }
}
