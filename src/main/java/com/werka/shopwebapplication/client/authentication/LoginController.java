package com.werka.shopwebapplication.client.authentication;


import com.werka.shopwebapplication.domain.api.services.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/log")
public class LoginController extends HttpServlet {

    private final ClientService clientService = new ClientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (clientService.isLoginDataCorrect(email, password)) {
            resp.sendRedirect("main");
        }else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, resp);
        }

    }
}
