package com.werka.shopwebapplication.client.authentication;


import com.werka.shopwebapplication.domain.client.ClientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/log")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    private final ClientDAO clientDAO = new ClientDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {

            boolean isFound = clientDAO.isFoundClientByMailAndPass(email, password);
            if (isFound) {
                resp.sendRedirect("main");
            }else {
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, resp);
            }
        }else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, resp);
        }
    }
}
