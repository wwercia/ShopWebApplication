package com.werka.shopwebapplication.client.authentication;

import com.werka.shopwebapplication.domain.api.ClientService;
import com.werka.shopwebapplication.domain.client.Client;
import com.werka.shopwebapplication.domain.client.ClientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, resp);
    }

    private ClientService clientService = new ClientService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isCorrectData = clientService.isRegistrationDataCorrect(name, surname, email, password);

        if (isCorrectData) {
            clientService.saveClient(name, surname, email, password);
            resp.sendRedirect("main");
        }else {
            request.getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, resp);
        }
    }
}
