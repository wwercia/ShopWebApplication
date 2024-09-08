package com.werka.shopwebapplication.client.authentication;

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

    private final ClientDAO clientDAO = new ClientDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()
                && name != null && !name.isEmpty() && surname != null && !surname.isEmpty()) {

            boolean isMailFree = clientDAO.isMailFree(email);
            if (isMailFree) {
                Client client = new Client(-1, name, surname, email, password);
                clientDAO.save(client);
                resp.sendRedirect("main");
            } else {
                request.getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, resp);
            }
        }else {
            request.getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, resp);
        }
    }
}
